# Opgave 2: SOAPHandler

Schrijf een handler die ervoor zorgt dat de "oude" clientapplicatie toch werkt met de nieuwe serverapplicatie. Er zijn twee mogelijke opties: een SOAPHandler en een LogicalHandler. In dit labo implementeer je beide opties. 

Meer info over handlers vind je in dit [artikel][arti]. De volgende [pagina][pagi] legt uit hoe je handlers kan toevoegen in Netbeans.

## Opgave

Voeg in Netbeans een SOAPHandler toe en configureer de webservice zodat de handler de berichten kan parsen. Welke bestanden worden er aangemaakt/aangepast? Haal in de handler de inhoud van het SOAP-bericht op en bekijk met de debugger wat het type is van het object dat de inhoud voorstelt. Zoek in de API op welke interfaces deze klasse implementeert en gebruik die om het SOAP-bericht van de client aan te passen zodat de nieuwe service dit bericht toch kan afhandelen. Test uit via de webinterface en met jouw clientapplicatie.

## Oplossing

We laden de EquationService en de TestEquationService projecten in Netbeans in. We veranderen ook de namen van de parameters in onze web methode van de EquationService klasse.

```java
@WebMethod(operationName = "solveQuadratic")
public double[] solveQuadratic(@WebParam(name = "c0") int a, @WebParam(
        name = "c1") int b, @WebParam(name = "c2") int c)
```

Als we nadien de main methode van TestEquationService uitvoeren krijgen we een exceptie. Deze gaan we verhelpen met een handler, hiervoor voegen we een message handler toe aan de EquationService.

```
Rechtermuis op EquationService > New > Other > Web Services > Message Handler
```

Hierbij kiezen we voor de naam EquationMessageHandler en het package ws.

De message handler wordt voor ons gegenereerd, maar moeten we nog aanpassen, meer specifiek de handleMessage methode. We schrijven de volgende code.

```java
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
```

Nadien moeten we de web service nog gebruik laten maken van de handler. Dat doen we door deze in de web service te configureren.

```
Ga naar EquationService > Web Services > EquationService
Rechtermuis op EquationService > Configure Handlers

Dan voegen we via Add de handler toe
We zien dat de handler als type Protocol heeft
```

Als we nadien onze web service nog eens deployen en daarna het test programma laten lopen, dan zien we dat dit wel terug lukt.

[arti]: https://jax-ws.java.net/articles/handlers_introduction.html
[pagi]: http://gcmuganda.faculty.noctrl.edu/classes/Spring12/615/Lab4/CSC615Spring12MessageHandlersLab.html
