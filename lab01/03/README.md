# Opgave 3

## Opgave

Gebruik daarna deze webservice als datalaag voor een GUI-applicatie. In de applicatie krijg je een overzicht van de beschikbare woordenboeken. Verder kan je de beginletters van een woord opgeven. De applicatie zoekt dan in de beschikbare woordenboeken alle woorden die starten met deze prefix. Selecteer je een gevonden woord en klik je op "definitie", dan krijg je de bijhorende verklaring. De GUI-applicatie is reeds ontwikkeld en maakt gebruik van een dummy-implementatie van de datalaag (be.tiwi.woordenboek.impl.WoordenboekDAODummy). Vervang deze dummy-implementatie door een implementatie die de data ophaalt van de bovenstaande webservice.

De GUI-applicatie bevat de volgende bestanden te ontwikkelen: Woordenboek.java, WoordenboekDAO.java, WoordenboekDAODummy, WoordenboekFrame.java en WoordenboekFrame.form.

![GUI for spell checker][gui]

## Oplossing

Eerst kopiëren we de src map in onze project map. Dan maken we een nieuw project aan dat gebruik maakt van reeds bestaande code.

```
File > New Project > Java > Java Project with Existing Code
```

Hier zien we 3 packages
* be.tiwi.woordenboek.data (voor de data klassen)
* be.tiwi.woordenboek.gui (voor de gui)
* be.tiwi.woordenboek.impl (voor de logica, hierin gaan we coderen)

Hierna moeten we ook de web service client toevoegen via de WSDL. [Link][wsdl].

Dan kunnen we alle dictionaries ophalen van de webservice. Hiervoor gebruiken we de DictionaryList methode.

```java
public WoordenboekDAODummy() {
    this.dictService = new DictService();
    this.dictServiceSoap = dictService.getDictServiceSoap();

    this.woordenboeken = initWoordenboeken();
}

private List<Woordenboek> initWoordenboeken() {

    // get all the dictionaries
    ArrayOfDictionary arrayOfDictionary = dictServiceSoap.dictionaryList();
    List<Dictionary> dictionarys = arrayOfDictionary.getDictionary();

    // map the dictionaries on a list of Woordenboek
    List<Woordenboek> lijstWoordenboeken = new ArrayList<>();
    for (int i = 0; i < dictionarys.size(); i++) {
        String id = dictionarys.get(i).getId();
        String name = dictionarys.get(i).getName();
        lijstWoordenboeken.add(new Woordenboek(id, name));
    }
    return lijstWoordenboeken;
}
```

Ook zoeken we in de WSDL op hoe we aanvullingen kunnen doen. 

```
DictService > StrategyList > Invoke

<Strategy>
	<Id>prefix</Id>
	<Description>Match prefixes</Description>
</Strategy>
```

Dus via de ID "prefix" kunnen we alle woorden ophalen die beginnen met die bepaalde prefix. Daarbij gebruiken we de methode MatchInDict.
Daarna kunnen we via de DefineInDict methode de betekenis van het woord ophalen.

```java
@Override
public List<String> zoekWoorden(String prefix, String woordenboekId) {

    // get the full words of the given prefix in the given dictionary
    ArrayOfDictionaryWord arrayOfDictionaryWord
            = dictServiceSoap.matchInDict(woordenboekId, prefix, "prefix");
    List<DictionaryWord> dictionaryWords
            = arrayOfDictionaryWord.getDictionaryWord();

    // map the words on a list of String
    List<String> woorden = new ArrayList<>();
    for (int i = 0; i < dictionaryWords.size(); i++) {
        woorden.add(dictionaryWords.get(i).getWord());
    }
    return woorden;
}

@Override
public List<String> getDefinities(String woord, String woordenboekId) {

    // get the definition of the given word in the given dictionary
    WordDefinition wordDefinition
            = dictServiceSoap.defineInDict(woordenboekId, woord);
    List<Definition> definitions
            = wordDefinition.getDefinitions().getDefinition();

    // map the definitions on a list of String
    List<String> definities = new ArrayList<>();
    for (int i = 0; i < definitions.size(); i++) {
        definities.add(definitions.get(i).getWordDefinition());
    }
    return definities;
}
```





[gui]: https://raw.githubusercontent.com/EMerckx/distributed-applications/master/lab01/res/woordenboekGUI.jpg
[wsdl]: http://services.aonaware.com/DictService/DictService.asmx
