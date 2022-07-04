package org.unlsycn;

import okhttp3.*;

import java.io.IOException;

/**
 * Connector to Anki
 */
public class AnkiConnector
{
    private static final String ANKI_CONNECT_KEY = "Anki"; //#gitignore
    private static final String ANKI_DECK_NAME = "OmegaT";
    private static final String ANKI_MODEL_NAME = "OmegaT Word";
    private final String word, context, translation, note, title, date, contextCloze;
    private final OkHttpClient client = new OkHttpClient();
    
    public AnkiConnector(String word, String context, String translation, String note, String title, String date)
    {
        this.word = word.trim();// trim word in that it's the regex in context.replaceALL()
        this.context = context;
        this.translation = translation;
        this.note = note;
        this.title = title;
        this.date = date;
        this.contextCloze = context.replaceAll(word, "{{c1::" + word + "}}");
    }
    
    public String Post()
    {
        try (Response response = client.newCall(constructRequest()).execute())
        {
            if (!response.isSuccessful())
            {
                throw new IOException("Unexpected code " + response);
            }
            return response.body().string();
        } catch (IOException e)
        {
            return e.getMessage();
        }
    }
    
    private Request constructRequest()
    {
        // AnkiConnect only support JSON as request body
        String JSONBody = "{\"action\":\"addNote\",\"version\":6,\"key\":\"" + ANKI_CONNECT_KEY + "\",\"params\":{\"note\":{\"deckName\":\"" + ANKI_DECK_NAME + "\",\"modelName\":\" " + ANKI_MODEL_NAME + "\",\"options\":{\"allowDuplicate\":false,\"duplicateScope\":\"deck\"},\"tags\":[],\"fields\":{\"Date\":\"" + date + "\",\"Text\":\"" + word + "\",\"Translation\":\"" + translation + "\",\"Context\":\"" + context + "\",\"ContextCloze\":\"" + contextCloze + "\",\"Note\":\"" + note + "\",\"Title\":\"" + title + "\"}}}}";
        RequestBody body = RequestBody.create(MediaType.parse("application/json"), JSONBody);
        return new Request.Builder().url("http://127.0.0.1:8765").post(body).build();
    }
}
