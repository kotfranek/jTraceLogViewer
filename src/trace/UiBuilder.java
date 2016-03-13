/*
 * Copyright (c) 2016, kret
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * * Redistributions of source code must retain the above copyright notice, this
 *   list of conditions and the following disclaimer.
 * * Redistributions in binary form must reproduce the above copyright notice,
 *   this list of conditions and the following disclaimer in the documentation
 *   and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */
package trace;

import kret.ui.AbstractUiBuilder;
import trace.ui.Actions;
import trace.ui.contexts.MainContext;
import trace.ui.frames.FrameMainWindow;

/**
 *
 * @author kret
 */
public class UiBuilder extends AbstractUiBuilder {

    public UiBuilder( final String uiLookAndFeelName ) 
    {
        super( uiLookAndFeelName );
    }
    
    
    @Override protected void createUi()
    {     
        m_frameMain = new FrameMainWindow();
        buildActions();
        
        m_frameMainContext = new MainContext( getActionManager(), m_frameMain );
        m_frameMainContext.setTitle( "TraceViewer" );
        setRootFrame( m_frameMainContext );        
    }


    private void buildActions() 
    {
        getActionManager().addAction("Open...", Actions.APP_OPEN_FILE, "Open log file");
        getActionManager().addAction("Connect...", Actions.APP_CONNECT, "Connect to Trace Server");
        getActionManager().addAction("Settings...", Actions.DLG_SETTINGS_OPEN, "Open the Settings Dialog");
        getActionManager().addAction("About...", Actions.DLG_ABOUT_OPEN, "Shows The About Window");
        getActionManager().addAction("Close", Actions.DLG_ABOUT_CLOSE, "Close the About Window");
        getActionManager().addAction("Exit", Actions.APP_EXIT, "Exit Program");
        getActionManager().addAction("Disconnect", Actions.APP_DISCONNECT, "Close the Connection");
        getActionManager().addAction("Close", Actions.VIEWER_CLOSE, "Close the Viewer");
    }
    
    private FrameMainWindow m_frameMain;
    private MainContext m_frameMainContext;
}

