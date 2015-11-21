# Lab 2

## Voorbereiding

Herbekijk de volgende onderwerpen uit de bachelor:
* [JAXP][jaxp]
* [XSLT][xslt]

en lees een [artikel over handlers][arti] bij webservices.

## Opgave 1: Aanpassen EquationService

Test de opdracht solveQuadratic van de webservice EquationService uit het vorige labo uit via de testpagina (zie ook Getting Started with JAX-WS Web Services). Dit kan enkel als je Glassfish als JE22-server gebruikt. Bekijk de SOAP-berichten die de service ontvangt en terugstuurt als antwoord. Test vervolgens uit met de clientapplicatie uit het vorige labo.

Pas nu de naam van de parameters in de annotaties @WebParam aan bv. c0, c1 en c2 in plaats van a, b en c. Test de opdracht solveQuadratic nu opnieuw uit en bekijk hoe de SOAP-berichten veranderd zijn. Testen met de clientapplicatie lukt nu niet meer. Waarom?

## Handlers

Schrijf een handler die ervoor zorgt dat de "oude" clientapplicatie toch werkt met de nieuwe serverapplicatie. Er zijn twee mogelijke opties: een SOAPHandler en een LogicalHandler. In dit labo implementeer je beide opties. 

Meer info over handlers vind je in dit [artikel][arti]. De volgende [pagina][pagi] legt uit hoe je handlers kan toevoegen in Netbeans.

### Opgave 2: SOAPHandler

Voeg in Netbeans een SOAPHandler toe en configureer de webservice zodat de handler de berichten kan parsen. Welke bestanden worden er aangemaakt/aangepast? Haal in de handler de inhoud van het SOAP-bericht op en bekijk met de debugger wat het type is van het object dat de inhoud voorstelt. Zoek in de API op welke interfaces deze klasse implementeert en gebruik die om het SOAP-bericht van de client aan te passen zodat de nieuwe service dit bericht toch kan afhandelen. Test uit via de webinterface en met jouw clientapplicatie.

### Opgave 3: LogicalHandler

Een alternatief voor de SOAPHandler is een LogicalHandler. Plaats in het juiste configuratiebestand de SOAPHandler in commentaar en voeg een LogicalHandler toe. Wat is nu het type van het object dat de inhoud van het SOAP-bericht voorstelt? Gebruik XSLT en de XSLT API in java om het SOAP-bericht om te vormen voor "oude" clients. Test uit.

[jaxp]: http://www.oracle.com/technetwork/java/intro-140052.html
[xslt]: http://www.cheat-sheets.org/saved-copy/XSLT_1quickref-v2.pdf
[arti]: https://jax-ws.java.net/articles/handlers_introduction.html
[pagi]: http://gcmuganda.faculty.noctrl.edu/classes/Spring12/615/Lab4/CSC615Spring12MessageHandlersLab.html
