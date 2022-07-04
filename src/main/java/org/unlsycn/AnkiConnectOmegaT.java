package org.unlsycn;

import org.omegat.core.Core;
import org.omegat.core.CoreEvents;
import org.omegat.core.events.IApplicationEventListener;

public class AnkiConnectOmegaT
{
    private static final int POPUP_PRIORITY = 65535;// 65535 means the item will be placed at the bottom of the popup menu
    
    /**
     * OmegaT will call this method when loading
     */
    public static void loadPlugins()
    {
        // register listeners
        CoreEvents.registerApplicationEventListener(new IApplicationEventListener()
        {
            @Override
            public void onApplicationStartup()
            {
                Core.getEditor().registerPopupMenuConstructors(POPUP_PRIORITY, new AnkiConnectPopup());
            }
            
            @Override
            public void onApplicationShutdown()
            {
            }
        });
    }
    
    public static void unloadPlugins()
    {
    }
}
