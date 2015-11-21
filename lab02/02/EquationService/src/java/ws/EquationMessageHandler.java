/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import com.sun.istack.internal.logging.Logger;
import java.util.Collections;
import java.util.Set;
import java.util.logging.Level;
import javax.xml.namespace.QName;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import org.hibernate.validator.internal.util.logging.Log;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author EMerckx
 */
public class EquationMessageHandler implements SOAPHandler<SOAPMessageContext> {

    @Override
    public boolean handleMessage(SOAPMessageContext messageContext) {

        // see if the message is outbound or inbound
        Boolean outboundProperty = (Boolean) messageContext.get(
                MessageContext.MESSAGE_OUTBOUND_PROPERTY);

        // if the message is inbound
        if (!outboundProperty) {

            // get the soap message
            SOAPMessage soapMessage = messageContext.getMessage();

            try {
                // get the first child node and all the coefficients
                SOAPBody body = soapMessage.getSOAPBody();
                Node firstChildNode = body.getFirstChild();
                NodeList coefficients = firstChildNode.getChildNodes();

                // loop over the coefficient nodes and change the node name
                for (int i = 0; i < coefficients.getLength(); i++) {
                    Node coefficientNode = coefficients.item(i);
                    switch (coefficientNode.getLocalName()) {
                        case "a":
                            changeNodeName("c0", coefficientNode, firstChildNode);
                            break;
                        case "b":
                            changeNodeName("c1", coefficientNode, firstChildNode);
                            break;
                        case "c":
                            changeNodeName("c2", coefficientNode, firstChildNode);
                            break;
                    }
                }
                soapMessage.saveChanges();

            } catch (SOAPException ex) {
                Logger.getLogger(EquationMessageHandler.class).log(
                        Level.SEVERE, null, ex);
            }
        }
        return true;
    }

    private void changeNodeName(String name, Node coefficient,
            Node firstChildNode) {
        // get the owner document of the coefficient node
        Document document = coefficient.getOwnerDocument();

        // create a new element in the document
        Node newNode = document.createElement(name);

        // add the value of the coefficient node to the new node
        newNode.appendChild(document.createTextNode(coefficient.getFirstChild().
                getNodeValue()));

        // replace the old coefficient node with the new node
        firstChildNode.replaceChild(newNode, coefficient);
    }

    @Override
    public Set<QName> getHeaders() {
        return Collections.EMPTY_SET;
    }

    @Override
    public boolean handleFault(SOAPMessageContext messageContext) {
        return true;
    }

    @Override
    public void close(MessageContext context) {
    }

}
