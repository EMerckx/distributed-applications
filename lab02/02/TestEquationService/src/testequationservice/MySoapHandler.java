/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testequationservice;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.Set;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import javax.xml.namespace.QName;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;

/**
 *
 * @author EMerckx
 */
public class MySoapHandler implements SOAPHandler<SOAPMessageContext> {

    /*public boolean handleMessage(SOAPMessageContext messageContext) {
     SOAPMessage msg = messageContext.getMessage();
     return true;
     }*/
    public boolean handleMessage(SOAPMessageContext messageContext) {
        String outProperty = SOAPMessageContext.MESSAGE_OUTBOUND_PROPERTY;
        boolean outgoing = (Boolean)messageContext.get(outProperty);        
        
        SOAPMessage msg = messageContext.getMessage();
        try
        {
          if (outgoing)           
             msg.writeTo(new FileOutputStream("responseMessage.txt"));          
          else
          {
             msg.writeTo(new FileOutputStream("inputMessage.txt"));
             // add a custom property
             //messageContext.put("school", "North Central");
             //messageContext.setScope("school", MessageContext.Scope.APPLICATION);
          }
        }
        catch(IOException e)
        {
            throw new RuntimeException(e) ;
        }  
        catch(SOAPException e)
        {
            throw new RuntimeException(e);
        }
        return true;
    }

    public Set<QName> getHeaders() {
        return Collections.EMPTY_SET;
    }

    public boolean handleFault(SOAPMessageContext messageContext) {
        return true;
    }

    public void close(MessageContext context) {
    }

}
