package org.unlsycn.gui;

import org.omegat.core.Core;
import org.omegat.core.CoreEvents;
import org.omegat.core.events.IApplicationEventListener;
import org.omegat.gui.editor.IPopupMenuConstructor;
import org.omegat.gui.editor.SegmentBuilder;

import javax.swing.*;
import javax.swing.text.JTextComponent;

public class AnkiConnectPopup implements IPopupMenuConstructor{

    public static void loadPlugins(){
        CoreEvents.registerApplicationEventListener(new IApplicationEventListener(){
            @Override
            public void onApplicationStartup(){

            }
            @Override
            public void onApplicationShutdown(){}
        });
    }

    public static  void unloadPlugins(){}

    @Override
    public void addItems(JPopupMenu jPopupMenu, JTextComponent jTextComponent, int i, boolean b, boolean b1, SegmentBuilder segmentBuilder) {
        String selection = Core.getEditor().getSelectedText();
        JMenuItem ankiMenuItem = new JMenuItem();
        ankiMenuItem.setText("Send to Anki");
        jPopupMenu.add(ankiMenuItem);
        jPopupMenu.addSeparator();
    }
}
