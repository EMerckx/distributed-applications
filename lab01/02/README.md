# Opgave 2

## Opgave

Maak een NetBeans-project om de web service te testen (een gewone console-applicatie). Vraag aan de gebruiker een tekst, haal verbetersuggesties op via de webservice, en schrijf deze uit.

Tips: Laat NetBeans de Web Service Client genereren. 

![Spell checker in console application][spell]

## Oplossing

We openen eerst Netbeans. Daarna maken we een nieuw project aan.

```
File > New Project > Java > Java Application
```

Dan maken we van het project een web service client.

```
Rechtermuis op project02 > New > Web Service Client
```

Hier vullen we de WSDL URL in van de web service: http://services.aonaware.com/DictService/DictService.asmx?WSDL.
We nemen ook als package project02, en dan klikken we op finish.
Er word nu voor ons de nodige files gegenereerd, daarna kunnen we beginnen met coderen.

```java
// create a service
DictService dictService = new DictService();

// create a soap
DictServiceSoap dictServiceSoap = dictService.getDictServiceSoap();

// set the given word
String given_word = "servizes";

// get the corrections of the given word
ArrayOfDictionaryWord arr = dictServiceSoap.match(given_word, "lev");

// handle the output
System.out.println("word: servizes");
System.out.print("corrections: ");
for(int i=0; i<arr.getDictionaryWord().size(); i++){
    System.out.print(arr.getDictionaryWord().get(i).word);
}
System.out.println(" ");
```

[spell]: https://raw.githubusercontent.com/EMerckx/distributed-applications/master/lab01/res/spellcheck.png
