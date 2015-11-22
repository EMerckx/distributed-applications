# Opgave 1: BadmintonServiceLibrary

## Opgave

Maak een nieuw project BadmintonServiceLibrary van het type "WCF Service Library". In dit project beschrijf en implementeer je de WCF-service. In twee volgende projecten gaan we deze service op twee manieren hosten: via IIS en via een console-applicatie. Meer informatie vind je in de MSDN-libary:

* [Define a Windows Communication Foundation Service Contract][defi]
* [Implement a Windows Communication Foundation Service Contract][impl]

De service biedt de twee volgende opdrachten aan:

* Alle badmintonclubs opvragen. Voor elke club wordt de naam, de unieke identificatie en de tornooien meegegeven. De tornooi-informatie bestaat uit naam en id.
* De leden van één badmintonclub opvragen. Voor elk lid wordt de naam en de unieke identificatie meegegeven. De club wordt gekenmerkt door zijn unieke identificatie.

Om gebruik te maken van de datalaag moet je zijn DLL toevoegen aan dit project (rechts klikken op References; Add Reference; Browse). Je kan de structuur van de datalaag bekijken in de Object Browser.

Merk op dat je ook DataContracten moet aanmaken. Test de aangemaakte service uit (rechtsklikken op het project --> Debug --> Start new instance --> dubbelklik op een methode)

## Oplossing

We openen Visual Studio als administrator. Dan maken we een nieuw project aan.

```
File > New > Project > Visual C# > WCF > WCF Service Library

Noem het project BadmintonServiceLibrary
Verander de locatie naar C:\temp\
Noem de solution BadmintonServers 
Vink de "Create directory for solution" checkbox aan
```

Dan gaan we de DLL van de datalaag toevoegen. Eerst pakken we de rar file met de datalaag uit en daarna kopiëren we de datalaag directory naar C:\temp\\...

```
In solutions explorer:
Rechtermuis op BadmintonServiceLibrary > Add Reference > Browse
```

Als we de DLL hebben toegevoegd kunnen we de structuur ervan bekijken via de Object Browser

In ons project gaan we de interface IService1 aanpassen door het ServiceContract en de DataContracts aan te passen.

DataContracts: 

```c#
[DataContract]
public class BadmintonClub
{
    private string name;
    private int id;
    private List<BadmintonTournament> tournaments;


    [DataMember]
    public string Name
    {
        get { return name; }
        set { name = value; }
    }

    [DataMember]
    public int Id
    {
        get { return id; }
        set { id = value; }
    }

    [DataMember]
    public List<BadmintonTournament> Tournaments
    {
        get { return tournaments; }
        set { tournaments = value; }
    }
}

[DataContract]
public class BadmintonTournament
{
    private string name;
    private int id;

    [DataMember]
    public string Name
    {
        get { return name; }
        set { name = value; }
    }

    [DataMember]
    public int Id
    {
        get { return id; }
        set { id = value; }
    }
}

[DataContract]
public class BadmintonClubMember
{
    private string name;
    private int id;

    [DataMember]
    public string Name
    {
        get { return name; }
        set { name = value; }
    }

    [DataMember]
    public int Id
    {
        get { return id; }
        set { id = value; }
    }
}
```

ServiceContract:

```c#
[ServiceContract]
public interface IService1
{
    [OperationContract]
    List<BadmintonClub> getBadmintonClubs();

    [OperationContract]
    List<BadmintonClubMember> getBadmintonClubMembers(int badmintonClubId);
}
```

Nu gaan we deze interface implementeren in Service1. We kunnen de signatures gemakkelijk inladen door volgende stappen.

```
Ga naar Service1 : IService1
Rechtermuis op IService1 > Implement Interface > Implement Interface
```

Nu moeten we nog de code schrijven voor getBadmintonClubs en getBadmintonClubMembers. Daarvoor gaan we gebruik maken van de datalaag.

```
using BadmintonInterface;
```

getBadmintonClubs methode:

```c#
public List<BadmintonClub> getBadmintonClubs()
{
    // get the sport clubs from the BadmintonDAODummy
    BadmintonDAODummy badmintonDaoDummy = new BadmintonDAODummy();
    SportClub[] sportClubArray;
    try
    {
        sportClubArray = badmintonDaoDummy.SportClubs;
    }
    catch (Exception ex)
    {
        return new List<BadmintonClub>();
    }

    // map the sport clubs on a list of BadmintonClub
    List<BadmintonClub> badmintonClubList = new List<BadmintonClub>();
    foreach (SportClub sportClub in sportClubArray)
    {
        // create a new BadmintonClub and map the id and name
        BadmintonClub badmintonClub = new BadmintonClub();
        badmintonClub.Id = sportClub.ID;
        badmintonClub.Name = sportClub.Naam;

        // create a new list of BadmintonTournament and map the tournaments
        List<BadmintonTournament> badmintonTournamentList
            = new List<BadmintonTournament>();
        foreach (Tornooi tornooi in sportClub.Tornooien)
        {
            // create a new BadmintonTournament and map the id and name
            BadmintonTournament badmintonTournament = new BadmintonTournament();
            badmintonTournament.Id = tornooi.ID;
            badmintonTournament.Name = tornooi.Naam;

            // add the BadmintonTournament to the list
            badmintonTournamentList.Add(badmintonTournament);
        }
        badmintonClub.Tournaments = badmintonTournamentList;

        // add the BadmintonClub to the list
        badmintonClubList.Add(badmintonClub);
    }

    // return the List of BadmintonClub
    return badmintonClubList;
}
```

getBadmintonClubMembers methode:

```c#
public List<BadmintonClubMember> getBadmintonClubMembers(int badmintonClubId)
{
    // get the members from the BadmintonDAODummy
    BadmintonDAODummy badmintonDaoDummy = new BadmintonDAODummy();
    Lid[] lidArray;
    try
    {
        lidArray = badmintonDaoDummy.GeefLeden(badmintonClubId);
    }
    catch (Exception ex)
    {
        return new List<BadmintonClubMember>();
    }

    // create a new list of BadmintonClubMember
    List<BadmintonClubMember> badmintonClubMemberList
        = new List<BadmintonClubMember>();

    // map the array to the list of BadmintonClubMember
    foreach (Lid lid in lidArray)
    {
        // create a new BadmintonClubMember and map the id and name
        BadmintonClubMember badmintonClubMember = new BadmintonClubMember();
        badmintonClubMember.Id = lid.ID;
        badmintonClubMember.Name = lid.Naam;

        // add the BadmintonClubMember to the list
        badmintonClubMemberList.Add(badmintonClubMember);
    }

    // return the list of BadmintonClubMember
    return badmintonClubMemberList;
}
```

Test de service 

```
Rechtermuis op project > Debug > Start new instance
Daar kan men de methode selecteren en op Invoke klikken
```
