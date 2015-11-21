# Opgave 4

## Opgave

Zelf een SOAP Web Service maken: Maak een nieuw Webproject EquationService. Definieer hierin een nieuwe Web Service EquationService in package ws.

Definieer een operatie double[] solveQuadratic(double a, double b, double c) toe, die de nulpunten van de vierkantsvergelijking ax2+bx+c berekent. Het resultaat is een tabel van nul, een of twee reële getallen.
Test uit via de browser. Enkele testcases:

```
x2-5x+6  --> {2,3}
2x2-4x+2 --> {1}
5x2+x+2  --> {}
```

Bekijk het SOAP-antwoord. Hoe wordt de nulpunten-array voorgesteld?

## Oplossing

We maken een nieuw project aan, namelijk een webproject genaamd EquationService.

```
File > New Project > Java Web > Web Application
```

We doorlopen de stappen, we vullen alles correct in. 
Indien nog geen server aanwezig is moeten we ook een server installeren. 
Hiervoor kiezen we voor de GlassFish server.

We maken ook een nieuwe package aan.

```
Rechtermuis op EquationService > New > Java Package
```

En we vullen alle nodige velden in. Ook maken we een nieuwe 

```
Rechtermuis op EquationService > New > Web Service
```

We schrijven er onze methode in.

```java
@WebMethod(operationName = "solveQuadratic")
public double[] solveQuadratic(@WebParam(name = "a") int a, 
    @WebParam(name = "b") int b, @WebParam(name = "c") int c) {

    int d = b * b - 4 * a * c;

    double[] solutions;
    if (d < 0) {
        solutions = new double[0];
    } else if (d == 0) {
        solutions = new double[1];
        solutions[0] = -b / (2 * a);
    } else {
        solutions = new double[2];
        solutions[0] = (-b - Math.sqrt(d)) / (2 * a);
        solutions[1] = (-b + Math.sqrt(d)) / (2 * a);
    }
    return solutions;
}
```

Om deze te testen gaan we eerst het project builden en daarna deployen. 
Als dit gebeurd is, dan kan de methode getest worden.

```
Rechtermuis op EquationService onder Web Services > Test Web Service
```

En dan komen we op volgende URL terecht: http://localhost:8080/EquationService/EquationService?Tester. 
Enkele testcases:

```
x2-5x+6  --> {2,3}
2x2-4x+2 --> {1}
5x2+x+2  --> {}
```

Vraag: Bekijk het SOAP-antwoord. Hoe wordt de nulpunten-array voorgesteld?

## Request 1

```
<?xml version="1.0" encoding="UTF-8"?>
<S:Envelope xmlns:S="http://schemas.xmlsoap.org/soap/envelope/" 
    xmlns:SOAP-ENV="http://schemas.xmlsoap.org/soap/envelope/">
    <SOAP-ENV:Header/>
    <S:Body>
        <ns2:solveQuadratic xmlns:ns2="http://ws/">
            <a>1</a>
            <b>-5</b>
            <c>6</c>
        </ns2:solveQuadratic>
    </S:Body>
</S:Envelope>
```

```
<?xml version="1.0" encoding="UTF-8"?>
<S:Envelope xmlns:S="http://schemas.xmlsoap.org/soap/envelope/" 
    xmlns:SOAP-ENV="http://schemas.xmlsoap.org/soap/envelope/">
    <SOAP-ENV:Header/>
    <S:Body>
        <ns2:solveQuadraticResponse xmlns:ns2="http://ws/">
            <return>2.0</return>
            <return>3.0</return>
        </ns2:solveQuadraticResponse>
    </S:Body>
</S:Envelope>
```

## Request 2

```
<?xml version="1.0" encoding="UTF-8"?>
<S:Envelope xmlns:S="http://schemas.xmlsoap.org/soap/envelope/" 
    xmlns:SOAP-ENV="http://schemas.xmlsoap.org/soap/envelope/">
    <SOAP-ENV:Header/>
    <S:Body>
        <ns2:solveQuadratic xmlns:ns2="http://ws/">
            <a>2</a>
            <b>-4</b>
            <c>2</c>
        </ns2:solveQuadratic>
    </S:Body>
</S:Envelope>
```

```
<?xml version="1.0" encoding="UTF-8"?>
<S:Envelope xmlns:S="http://schemas.xmlsoap.org/soap/envelope/" 
    xmlns:SOAP-ENV="http://schemas.xmlsoap.org/soap/envelope/">
    <SOAP-ENV:Header/>
    <S:Body>
        <ns2:solveQuadraticResponse xmlns:ns2="http://ws/">
            <return>1.0</return>
        </ns2:solveQuadraticResponse>
    </S:Body>
</S:Envelope>
```

## Request 3

```
<?xml version="1.0" encoding="UTF-8"?>
<S:Envelope xmlns:S="http://schemas.xmlsoap.org/soap/envelope/" 
    xmlns:SOAP-ENV="http://schemas.xmlsoap.org/soap/envelope/">
    <SOAP-ENV:Header/>
    <S:Body>
        <ns2:solveQuadratic xmlns:ns2="http://ws/">
            <a>5</a>
            <b>1</b>
            <c>2</c>
        </ns2:solveQuadratic>
    </S:Body>
</S:Envelope>
```

```
<?xml version="1.0" encoding="UTF-8"?>
<S:Envelope xmlns:S="http://schemas.xmlsoap.org/soap/envelope/" 
    xmlns:SOAP-ENV="http://schemas.xmlsoap.org/soap/envelope/">
    <SOAP-ENV:Header/>
    <S:Body>
        <ns2:solveQuadraticResponse xmlns:ns2="http://ws/"/>
    </S:Body>
</S:Envelope>
```


