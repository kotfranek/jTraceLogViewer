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
package trace.ui.contexts;

import javax.swing.filechooser.FileNameExtensionFilter;
import kret.ui.AbstractFrameContext;
import kret.ui.ActionManager;
import kret.ui.Command;
import trace.ui.Actions;
import trace.ui.frames.FrameMainWindow;
import kret.ui.dialog.FileChooser;
import trace.ui.frames.DialogProperties;
import trace.ui.frames.FrameFileView;

/**
 *
 * @author kret
 */
public class MainContext extends AbstractFrameContext {
    
    public MainContext( ActionManager actionManager, FrameMainWindow frame )
    {
        super( actionManager, frame );
        m_actionManager = actionManager;
        m_frameMain = frame;
        m_dialogProperties = new DialogProperties( frame, true );
        m_propertiesContext = new PropertiesContext( actionManager, m_dialogProperties );
                registerLiseners();
        setDefaultLocation();        
        bindActions();
    }
    
    private void bindActions()
    {   
        getCommandAdapter().bindToCommand( m_frameMain.getTriggerExit(), Actions.APP_EXIT );
        getCommandAdapter().bindToCommand( m_frameMain.getTriggerOpen(), Actions.APP_OPEN_FILE );
        getCommandAdapter().bindToCommand( m_frameMain.getTriggerSettings(), Actions.DLG_SETTINGS_OPEN );
        getCommandAdapter().bindToCommand( m_frameMain.getTrigggerConnect(), Actions.APP_CONNECT );
        getCommandAdapter().bindToCommand( m_frameMain.getTriggerDisconnect(), Actions.APP_DISCONNECT );
        
        getCommandAdapter().setActionEnabledByCommand( Actions.APP_DISCONNECT, false );
    }    
    
    @Override public void onFrameClosed()
    {
        System.exit(0);
    }
    
    @Override public void onFrameOpened()
    {

    }
    
    @Override public void onFrameActivated()
    {

    }    
    
    @Override public void onCommandExecuted( final Command command )
    {
        if ( null != command )
        {
            switch ( command.getAction() )
            {                   
                case Actions.APP_OPEN_FILE:
                {
                    FileChooser openFile = new FileChooser( this, new FileNameExtensionFilter( "TraceLog Files", "log" ) );
                    openFile.open();
                    
                    FrameFileView fileView = new FrameFileView();
                    FileViewContext fileViewContext = new FileViewContext( m_actionManager, fileView );
                    
                    m_frameMain.getDesktopPane().add( fileView );
                    fileView.setVisible(true);
                }
                break;
                
                case Actions.DLG_SETTINGS_OPEN:
                {
                    m_propertiesContext.showFrame();
                }
                break;
                
                case Actions.DLG_ABOUT_OPEN:
                {
                    
                }
                break;                                      
                    
                case Actions.APP_CONNECT:
                {
                    //getCommandAdapter().bindToCommand( m_frameMain.getConnectionButton(), Actions.APP_DISCONNECT );                    
                }
                break;
                
                case Actions.APP_DISCONNECT:
                {                    
                    
                    //getCommandAdapter().bindToCommand( m_frameMain.getConnectionButton(), Actions.APP_CONNECT );

                }
                break;
                    
                case Actions.APP_EXIT:
                {
                    closeFrame();                    
                }
                break;
            }            
        } 
    }
    
    final FrameMainWindow m_frameMain;
    final DialogProperties m_dialogProperties;
    final PropertiesContext m_propertiesContext;
    final ActionManager m_actionManager;

}

