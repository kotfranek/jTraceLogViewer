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

import kret.ui.AbstractInternalFrameContext;
import kret.ui.ActionManager;
import kret.ui.Command;
import trace.ui.Actions;
import trace.ui.frames.FrameFileView;

/**
 *
 * @author kret
 */
public class FileViewContext extends AbstractInternalFrameContext
{
    FileViewContext( ActionManager actionManager, FrameFileView frame )
    {
        super( actionManager, frame );
        m_frame = frame;
        m_frame.setTitle("File");
        getCommandAdapter().bindToCommand( m_frame.getTriggerClose(), Actions.VIEWER_CLOSE );                 
    }
    
    
    @Override public void onFrameClosed()
    {
        
    }
    
    @Override public void onFrameOpened()
    {

    }
    
    @Override public void onFrameActivated()
    {

    }  
    
    @Override public void onCommandExecuted( final Command command )
    {
        switch ( command.getAction() )
        {                                                                           
            case Actions.VIEWER_CLOSE:
            {
                if ( command.isSource( m_frame.getTriggerClose() ) )
                {
                    closeFrame();                    
                }
            }
            break;
        }                    
    }
    
    FrameFileView m_frame;
}




