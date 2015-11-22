# Opgave 3: BadmintonClientConsole

## Opgave

Schrijf een console-applicatie die als client gebruik maakt van WCF om allerlei informatie over de badmintonclubs op te vragen aan de server en op het scherm te tonen. De functionaliteit van dit programma is:

* Toon alle badmintonclubs (naam en identificatie) en hun tornooien (naam).
* Vraag een id van een badmintonclub en toon de leden van deze club (naam).

Om de proxy en de bijhorende configuratie te genereren voeg je de WCF-service toe aan de hand van zijn WSDL. (Service Reference)

## Oplossing

In onze solution gaan we een nieuw project toevoegen, namelijk een Console Application

```
Rechtermuis op solution > Add > New Project > Visual C# > Windows 
	> Console Application

Noem het project BadmintonClientConsole
```

We voegen dan een service reference toe

```
Rechtermuis op project > Add Service Reference
Via Discover kunnen we de juiste service vinden

We kiezen voor Service1.svc
Als naam geven we BadmintonServiceReference in
```

Dan voegen we de using toe in Program.cs

```C#
using BadmintonClientConsole.BadmintonServiceReference;
```

En in de Main methode schrijven we:

```C#
// create a new web service client
Service1Client service1Client = new Service1Client();

// write the clubs to the screen
BadmintonClub[] badmintonClubArray = service1Client.getBadmintonClubs();
foreach (BadmintonClub badmintonClub in badmintonClubArray)
{
    // show the id and name of the club
    Console.WriteLine("ID: " + badmintonClub.Id + "\t Name: " + badmintonClub.Name);

    // if there are tournaments, show them
    if (badmintonClub.Tournaments != null &&
        badmintonClub.Tournaments.Length > 0)
    {
        Console.WriteLine("Tournaments:");
        foreach (BadmintonTournament badmintonTournament 
        	in badmintonClub.Tournaments)
        {
            Console.WriteLine("\t " + badmintonTournament.Name);
        }
    }
    Console.WriteLine("");
}

// get the id of a club from the user
Console.WriteLine("Enter an id of a club: ");
string idString = Console.ReadLine();
int id = int.Parse(idString);
Console.WriteLine("Members: ");

// get the members and write them to the screen
BadmintonClubMember[] badmintonClubMemberArray 
	= service1Client.getBadmintonClubMembers(id);
foreach (BadmintonClubMember badmintonClubMember in badmintonClubMemberArray)
{
    Console.WriteLine("\t " + badmintonClubMember.Name);
}

// close the web service client
service1Client.Close();
```

