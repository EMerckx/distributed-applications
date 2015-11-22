# Labo 4 en 5: WCF

In dit labo maken we gebruik van een bestaande datalaag die gegevens aanlevert over badmintonclubs. Een aantal van de gegevens van deze datalaag worden beschikbaar gesteld voor andere applicaties via WCF in C#.

Hiervoor maken we vijf "Visual Studio"-projecten. Bewaar al je projecten LOKAAL op de computer bv. in C:\temp\\.... De "solution" <b>BadmintonServers</b> bevat drie projecten:

* <b>BadmintonServiceLibrary</b> voorziet de interface en de implementatie van de WCF-service die toegang geeft tot de objecten van de datalaag.
* <b>BadmintonService</b> publiceert de bovenstaande WCF-service via IIS
* <b>BadmintonServerConsole</b> publiceert de bovenstaande WCF-service in een console-applicatie die via WCF toegang geeft tot de objecten van de datalaag.

Daarnaast maken we ook twee "client"-projecten die de bovenstaande services uittesten.

* <b>BadmintonClientConsole</b> is een console-applicatie die via WCF gegevens opvraagt aan de WCF-service gepubliceerd via IIS.
* <b>BadmintonWebClient</b> is een webapplicatie die via WCF gegevens opvraagt aan de console-server.

## Opgave 1: BadmintonServiceLibrary

Maak een nieuw project BadmintonServiceLibrary van het type "WCF Service Library". In dit project beschrijf en implementeer je de WCF-service. In twee volgende projecten gaan we deze service op twee manieren hosten: via IIS en via een console-applicatie. Meer informatie vind je in de MSDN-libary:

* [Define a Windows Communication Foundation Service Contract][defi]
* [Implement a Windows Communication Foundation Service Contract][impl]

De service biedt de twee volgende opdrachten aan:

* Alle badmintonclubs opvragen. Voor elke club wordt de naam, de unieke identificatie en de tornooien meegegeven. De tornooi-informatie bestaat uit naam en id.
* De leden van één badmintonclub opvragen. Voor elk lid wordt de naam en de unieke identificatie meegegeven. De club wordt gekenmerkt door zijn unieke identificatie.

Om gebruik te maken van de datalaag moet je zijn DLL toevoegen aan dit project (rechts klikken op References; Add Reference; Browse). Je kan de structuur van de datalaag bekijken in de Object Browser.

Merk op dat je ook DataContracten moet aanmaken. Test de aangemaakte service uit (rechtsklikken op het project --> Debug --> Start new instance --> dubbelklik op een methode)

## Opgave 2: BadmintonService

Maak een service op IIS die de bovenstaande diensten via WCF over HTTP. Je kan hiervoor een project van het type "WCF Service Application" aanmaken. Pas de standaard aangemaakte service aan:

* Verwijder de codebehind.
* Pas het svc-bestand aan zodat het verwijst naar jouw service uit het vorig project. Bv.

```
<%@ServiceHost language=c# Debug="true" 
	Service="Microsoft.ServiceModel.Samples.CalculatorService"%>
```

Informatie over de service krijg je als je "View in Browser" selecteert.

## Opgave 3: BadmintonClientConsole

Schrijf een console-applicatie die als client gebruik maakt van WCF om allerlei informatie over de badmintonclubs op te vragen aan de server en op het scherm te tonen. De functionaliteit van dit programma is:

* Toon alle badmintonclubs (naam en identificatie) en hun tornooien (naam).
* Vraag een id van een badmintonclub en toon de leden van deze club (naam).

Om de proxy en de bijhorende configuratie te genereren voeg je de WCF-service toe aan de hand van zijn WSDL. (Service Reference)

## Opgave 4: BadmintonServerConsole

Publiceer de service nu als console-applicatie in een nieuw project BadmintonServerConsole gebruik makende van TCP. Neem alle configuratie op in een configuratiebestand (ipv in code). Een voorbeeld van de configuratie over HTTP is

![Example of configuration file][4-1]

Pas deze configuratie aan naar TCP. Bij het opstarten van de service krijg je een waarschuwing. Klik die weg.

## Opgave 5: BadmintonWebClient

Maak een website bestaande uit twee webpagina's:

* De eerste webpagina toont alle badmintonclubs in een grid
* In de tweede webpagina kan je de identificatie van een club ingeven, het formulier toont dan alle leden van die club.

De data voor de webapplicatie wordt verkregen via WCF over TCP van de server BadmintonServerConsole. Gebruik hier ook een "service reference".

![Show clubs][5-1]

![Show members][5-2]

[defi]: https://msdn.microsoft.com/en-us/library/ms731835.aspx
[impl]: https://msdn.microsoft.com/en-us/library/ms734686.aspx
[4-1]: https://raw.githubusercontent.com/EMerckx/distributed-applications/master/lab04-05/res/4-1.png
[5-1]: https://raw.githubusercontent.com/EMerckx/distributed-applications/master/lab04-05/res/5-1.png
[5-2]: https://raw.githubusercontent.com/EMerckx/distributed-applications/master/lab04-05/res/5-2.png
