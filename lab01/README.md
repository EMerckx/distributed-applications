# GUI met als datalaag een SOAP-webservice

## Opgave 1

Voor de datalaag van de onderstaande GUI zullen we gebruik maken van de SOAP-webservice [DictService][dict].

Bekijk de WSDL en test enkele operaties uit via de browser. Vraagjes:

* Wat is de naam van de aangeboden service?
* Welke poorten worden er gedefinieerd?
* Welke transportprotocol gebruikt de DictServiceSoap-binding?
* Welke operaties worden er in de DictServiceSoap-binding gedefinieerd?
* Welke parameters verwacht de operatie MatchInDict?
* Wat retourneert de operatie MatchInDict? 

## Opgave 2

Maak een NetBeans-project om de web service te testen (een gewone console-applicatie). Vraag aan de gebruiker een tekst, haal verbetersuggesties op via de webservice, en schrijf deze uit.

Tips: Laat NetBeans de Web Service Client genereren. 

![Spell checker in console application][spell]

## Opgave 3

Gebruik daarna deze webservice als datalaag voor een GUI-applicatie. In de applicatie krijg je een overzicht van de beschikbare woordenboeken. Verder kan je de beginletters van een woord opgeven. De applicatie zoekt dan in de beschikbare woordenboeken alle woorden die starten met deze prefix. Selecteer je een gevonden woord en klik je op "definitie", dan krijg je de bijhorende verklaring. De GUI-applicatie is reeds ontwikkeld en maakt gebruik van een dummy-implementatie van de datalaag (be.tiwi.woordenboek.impl.WoordenboekDAODummy). Vervang deze dummy-implementatie door een implementatie die de data ophaalt van de bovenstaande webservice.

De GUI-applicatie bevat de volgende bestanden te ontwikkelen: Woordenboek.java, WoordenboekDAO.java, WoordenboekDAODummy, WoordenboekFrame.java en WoordenboekFrame.form.

![GUI for spell checker][gui]

## Opgave 4

Zelf een SOAP Web Service maken: Maak een nieuw Webproject EquationService. Definieer hierin een nieuwe Web Service EquationService in package ws.

Definieer een operatie double[] solveQuadratic(double a, double b, double c) toe, die de nulpunten van de vierkantsvergelijking ax2+bx+c berekent. Het resultaat is een tabel van nul, een of twee reële getallen.
Test uit via de browser. Enkele testcases:

    x2-5x+6  --> {2,3}
    2x2-4x+2 --> {1}
    5x2+x+2  --> {}

Bekijk het SOAP-antwoord. Hoe wordt de nulpunten-array voorgesteld?

## Opgave 5

Maak een nieuw console-project TestEquationService. Definieer hierin een Web Service Client die gebruik maakt van EquationService. Roep de operatie solveQuadratic op en schrijf het resultaat uit. Zorg ook voor gepaste foutafhandeling.

Hoe wordt de nulpunten-array hier geïmplementeerd? 

[dict]: http://services.aonaware.com/DictService/DictService.asmx
[spell]: https://raw.githubusercontent.com/EMerckx/distributed-applications/master/lab01/res/spellcheck.png
[gui]: https://raw.githubusercontent.com/EMerckx/distributed-applications/master/lab01/res/woordenboekGUI.jpg
