# Opgave 2: BadmintonService

## Opgave

Maak een service op IIS die de bovenstaande diensten via WCF over HTTP. Je kan hiervoor een project van het type "WCF Service Application" aanmaken. Pas de standaard aangemaakte service aan:

* Verwijder de codebehind.
* Pas het svc-bestand aan zodat het verwijst naar jouw service uit het vorig project. Bv.

```
<%@ServiceHost language=c# Debug="true" 
	Service="Microsoft.ServiceModel.Samples.CalculatorService"%>
```

Informatie over de service krijg je als je "View in Browser" selecteert.

## Oplossing

In onze solution gaan we een nieuw project toevoegen, namelijk een WCF Service Application

```
Rechtermuis op solution > Add > New Project > Visual C# > WCF 
	> WCF Service Application

Noem het project BadmintonService
```

We voegen de DLL van de BadmintonServiceLibrary toe aan de references

```
Rechtermuis op project > Add Reference > Browse
```

We verwijderen de code van Service1.svc, door in de solution explorer de file uit te klappen en dan de file Service1.svc.cs te verwijderen. Daarna passen we Service1.svc aan met volgende code

```
<%@ ServiceHost Language="C#" Debug="true" 
    Service="BadmintonServiceLibrary.Service1" %>
```

We kunnen ook IService1 gewoon verwijderen, daar er hier geen gebruik van gemaakt wordt.

Om te testen:

```
Rechtermuis op project > View in Browser > Service1.svc
```
