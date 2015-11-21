/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamSource;
import javax.xml.ws.LogicalMessage;
import javax.xml.ws.handler.LogicalHandler;
import javax.xml.ws.handler.LogicalMessageContext;
import javax.xml.ws.handler.MessageContext;
import org.w3c.dom.Document;

/**
 *
 * @author EMerckx
 */
public class EquationLogicalHandler implements
        LogicalHandler<LogicalMessageContext> {

    @Override
    public boolean handleMessage(LogicalMessageContext messageContext) {

        // get the outbound property
        Boolean outboundProperty = (Boolean) messageContext.get(
                MessageContext.MESSAGE_OUTBOUND_PROPERTY);

        // if the message is inbound
        if (!outboundProperty) {

            try {
                // get the logical message and the payload of it
                LogicalMessage logicalMessage = messageContext.getMessage();
                Source source = logicalMessage.getPayload();
                
                // create a tranformer factory and a transformer
                TransformerFactory factory = TransformerFactory.newInstance();
                Transformer transformer = factory.newTransformer(
                        new StreamSource(this.getClass().getClassLoader().
                                getResourceAsStream("ws/newVersion.xsl")));

                // create a new document
                DocumentBuilderFactory builderFactory
                        = DocumentBuilderFactory.newInstance();
                DocumentBuilder builder = builderFactory.newDocumentBuilder();
                Document document = builder.newDocument();
                
                // create a DOMResult from the new document
                // and transform the payload of the message to the DOMResult
                DOMResult domResult = new DOMResult(document);
                transformer.transform(source, domResult);
                
                // set the payload of the message to the DOMResult
                logicalMessage.setPayload(new DOMSource(domResult.getNode()));
                
            } catch (TransformerConfigurationException ex) {
                Logger.getLogger(EquationLogicalHandler.class.getName()).
                        log(Level.SEVERE, null, ex);
            } catch (ParserConfigurationException |
                    TransformerException ex) {
                Logger.getLogger(EquationLogicalHandler.class.getName()).
                        log(Level.SEVERE, null, ex);
            }
        }

        return true;
    }

    @Override
    public boolean handleFault(LogicalMessageContext messageContext) {
        return true;
    }

    @Override
    public void close(MessageContext context) {
    }

}
