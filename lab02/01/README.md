# Opgave 1: Aanpassen EquationService

## Opgave

Test de opdracht solveQuadratic van de webservice EquationService uit het vorige labo uit via de testpagina (zie ook Getting Started with JAX-WS Web Services). Dit kan enkel als je Glassfish als JE22-server gebruikt. Bekijk de SOAP-berichten die de service ontvangt en terugstuurt als antwoord. Test vervolgens uit met de clientapplicatie uit het vorige labo.

Pas nu de naam van de parameters in de annotaties @WebParam aan bv. c0, c1 en c2 in plaats van a, b en c. Test de opdracht solveQuadratic nu opnieuw uit en bekijk hoe de SOAP-berichten veranderd zijn. Testen met de clientapplicatie lukt nu niet meer. Waarom?

## Oplossing

We laden de EquationService en de TestEquationService projecten in Netbeans in. We veranderen ook de namen van de parameters in onze web methode van de EquationService klasse.

```java
@WebMethod(operationName = "solveQuadratic")
public double[] solveQuadratic(@WebParam(name = "c0") int a, @WebParam(
        name = "c1") int b, @WebParam(name = "c2") int c)
```

Als we nadien de main methode van TestEquationService uitvoeren krijgen we een exceptie.

Dus: indien we de namen van de web param gaan aanpassen, dan kan de client die niet meer correct aanspreken.


