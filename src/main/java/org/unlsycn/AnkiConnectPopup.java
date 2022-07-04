package org.unlsycn;

import org.omegat.core.Core;
import org.omegat.gui.editor.IPopupMenuConstructor;
import org.omegat.gui.editor.SegmentBuilder;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;

/**
 * plugin popup menu
 */
public class AnkiConnectPopup implements IPopupMenuConstructor
{
    /**
     * add AnkiConnectItem to right-click menu when the **menu is to load**.
     *
     * @param menu     right-click popup menu
     * @param mousePos mouse position when open the menu
     */
    @Override
    public void addItems(JPopupMenu menu, JTextComponent comp, int mousePos, boolean isInActiveEntry, boolean isInActiveTranslation, SegmentBuilder segmentBuilder)
    {
        String selection = Core.getEditor().getSelectedText();
        String context = Core.getEditor().getCurrentEntry().getSrcText();
        if (selection == null)// when no text is selected
        {
            return;
        }
        if (!context.contains(selection))
        {
            return;
        }
        JMenuItem ankiMenuItem = new JMenuItem("Send to Anki");
        ankiMenuItem.addActionListener(new AnkiConnectMenuItemActionListener());
        menu.add(ankiMenuItem);
    }
    
    /**
     * listener to menu click event
     */
    private static class AnkiConnectMenuItemActionListener implements ActionListener
    {
        /**
         * Called when the **menu item is clicked**.
         *
         * @param event the event to be processed
         */
        public void actionPerformed(ActionEvent event)
        {
            final String word = Core.getEditor().getSelectedText().trim();
            final String context = Core.getEditor().getCurrentEntry().getSrcText().trim();
            final String translation = Core.getEditor().getCurrentTranslation().trim();
            String file = Core.getEditor().getCurrentFile().trim();
            final String title = file.substring(0, file.lastIndexOf("."));
            final String date = new Timestamp(System.currentTimeMillis()).toString();
            
            // create a form which can send text to Anki
            new AnkiConnectDialog(word, context, translation, title, date);
        }
    }
}
