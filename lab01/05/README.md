# Opgave 5

## Opgave

Maak een nieuw console-project TestEquationService. Definieer hierin een Web Service Client die gebruik maakt van EquationService. Roep de operatie solveQuadratic op en schrijf het resultaat uit. Zorg ook voor gepaste foutafhandeling.

Hoe wordt de nulpunten-array hier geïmplementeerd? 

## Oplossing

Maak een nieuw console-project TestEquationService. 

```
File > New Project > Java > Java Application
```

Definieer hierin een Web Service Client die gebruik maakt van EquationService. 

```
Rechtermuis op project02 > New > Web Service Client
```

Roep de operatie solveQuadratic op en schrijf het resultaat uit. 
Zorg ook voor gepaste foutafhandeling. 

Om de webservice te gebruiken gaan we ook code laten genereren, en dit in de main klasse.

```
Rechtermuis in main klasse > Insert Code > Call Web Service Operation
```

And we get the following code generated.

```java
private static java.util.List<java.lang.Double> solveQuadratic(int a, 
		int b, int c) {
    
    ws.EquationService_Service service = new ws.EquationService_Service();
    ws.EquationService port = service.getEquationServicePort();
    return port.solveQuadratic(a, b, c);
}
```

After that, we just need to call the method.

```java
public static void main(String[] args) {
    List<Double> list = solveQuadratic(1, -5, 6);
    for(int i=0; i<list.size(); i++){
        System.out.println(list.get(i) + " ");
    }
}
```
