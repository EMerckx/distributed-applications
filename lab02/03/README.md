# Opgave 3: LogicalHandler

Schrijf een handler die ervoor zorgt dat de "oude" clientapplicatie toch werkt met de nieuwe serverapplicatie. Er zijn twee mogelijke opties: een SOAPHandler en een LogicalHandler. In dit labo implementeer je beide opties. 

Meer info over handlers vind je in dit [artikel][arti]. De volgende [pagina][pagi] legt uit hoe je handlers kan toevoegen in Netbeans.

## Opgave

Een alternatief voor de SOAPHandler is een LogicalHandler. Plaats in het juiste configuratiebestand de SOAPHandler in commentaar en voeg een LogicalHandler toe. Wat is nu het type van het object dat de inhoud van het SOAP-bericht voorstelt? Gebruik XSLT en de XSLT API in java om het SOAP-bericht om te vormen voor "oude" clients. Test uit.

## Oplossing

We laden de EquationService en de TestEquationService projecten van vorige oefening in Netbeans in. Nu moeten we nog het configuratie bestand aanpassen zodat men geen gebruik meer maakt van de SOAPHandler.

```
EquationService > Source Packages > ws > EquationService_handler.xml
```

Hierin zetten we de handler in commentaar

```
<!--<handler>
	<handler-name>ws.EquationMessageHandler</handler-name>
	<handler-class>ws.EquationMessageHandler</handler-class>
</handler>-->
```

Dan voegen we een nieuwe handler toe, meer bepaald een LogicalHandler

```
Rechtermuis op EquationService > New > Other > Web Services > Logical Handler
```

Hierin schrijven we volgende code.

```java
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
```

Nadien moeten we de web service nog gebruik laten maken van de handler. Dat doen we door deze in de web service te configureren.

```
Ga naar EquationService > Web Services > EquationService
Rechtermuis op EquationService > Configure Handlers

Dan voegen we via Add de handler toe
We zien dat de handler als type Logical heeft
```

Als we nadien onze web service nog eens deployen en daarna het test programma laten lopen, dan zien we dat dit wel terug lukt.











[arti]: https://jax-ws.java.net/articles/handlers_introduction.html
[pagi]: http://gcmuganda.faculty.noctrl.edu/classes/Spring12/615/Lab4/CSC615Spring12MessageHandlersLab.html