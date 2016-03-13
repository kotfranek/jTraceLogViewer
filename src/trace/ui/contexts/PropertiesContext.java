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

import kret.io.conf.Configuration;
import kret.io.conf.Property;
import kret.ui.AbstractDialogContext;
import kret.ui.ActionManager;
import kret.ui.Command;

import trace.ui.Actions;
import trace.ui.frames.DialogProperties;

/**
 *
 * @author Anca
 */
public class PropertiesContext extends AbstractDialogContext {
    
    public PropertiesContext( ActionManager actionManager, DialogProperties frame )
    {
        super( actionManager, frame );
        m_dialogProperties = frame;
        m_config = new Configuration( ".config" );

        bindActions();
                registerLiseners();
    }
    
    private void bindActions()
    {           
        getCommandAdapter().bindToCommand(m_dialogProperties.getCancelTrigger(), Actions.DLG_SETTINGS_CANCEL );        
        getCommandAdapter().bindToCommand(m_dialogProperties.getOkTrigger(), Actions.DLG_SETTINGS_SAVE );        
    }    
    
    @Override public void onFrameClosed()
    {

    }
    
    @Override public void onFrameOpened()
    {
        m_config.addProperty( new Property( "SRV_IP", "127.0.0.1", m_dialogProperties.getServerIp() ) );
        m_config.addProperty( new Property( "SRV_PORT", "55555", m_dialogProperties.getServerPort() ) );
        m_config.load();
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
                case Actions.DLG_SETTINGS_SAVE:
                {
                    m_config.store();
                    closeFrame();
                }
                break;
                
                case Actions.DLG_SETTINGS_CANCEL:
                {
                    closeFrame();
                }
                break;
            }            
        } 
    }
    
    private final DialogProperties m_dialogProperties;
    private final Configuration m_config;
}

