# Opgave 4: BadmintonServerConsole

## Opgave

Publiceer de service nu als console-applicatie in een nieuw project BadmintonServerConsole gebruik makende van TCP. Neem alle configuratie op in een configuratiebestand (ipv in code). Een voorbeeld van de configuratie over HTTP is

![Example of configuration file][4-1]

Pas deze configuratie aan naar TCP. Bij het opstarten van de service krijg je een waarschuwing. Klik die weg.

## Oplossing

In onze solution gaan we een nieuw project toevoegen, namelijk een Console Application

```
Rechtermuis op solution > Add > New Project > Visual C# > Windows 
	> Console Application

Noem het project BadmintonServerConsole
```

We voegen dan een reference toe. Dit mag geen Server Reference zijn, maar moet een reference naar een DLL zijn!

```
Rechtermuis op project > Add Reference > Solution
Daar kiezen we de reference van de BadmintonServiceLibrary
```

Nu moeten we de App.config aanpassen

```
<?xml version="1.0" encoding="utf-8" ?>
<configuration>
  <startup>
    <supportedRuntime version="v4.0" sku=".NETFramework,Version=v4.5" />
  </startup>
  <system.serviceModel>
    <services>
      <service name="BadmintonServiceLibrary.Service1">
        <endpoint address="" binding="netTcpBinding"
                   contract="BadmintonServiceLibrary.IService1">
          <identity>
            <dns value="localhost"/>
          </identity>
        </endpoint>
        <endpoint contract="IMetadataExchange" binding="mexTcpBinding"
                  address="mex"/>
        <host>
          <baseAddresses>
            <add baseAddress=
              "net.tcp://localhost:8732/BadmintonServer/BadmintonService"/>
          </baseAddresses>
        </host>
      </service>
    </services>
    <behaviors>
      <serviceBehaviors>
        <behavior>
          <serviceMetadata httpGetEnabled="false"/>
          <serviceDebug includeExceptionDetailInFaults="false"/>
        </behavior>
      </serviceBehaviors>
    </behaviors>
  </system.serviceModel>
</configuration>
```

En dan schrijven we het volgende in de Main methode van Program.cs

```c#
static void Main(string[] args)
{
    using (ServiceHost serviceHost = 
      new ServiceHost(typeof(BadmintonServiceLibrary.Service1)))
    {
        serviceHost.Open();

        Console.WriteLine("The server is ready ");

        Console.ReadLine();
    }
}
```

Nadien debuggen we het project en zien we dat het werkt

[4-1]: https://raw.githubusercontent.com/EMerckx/distributed-applications/master/lab04-05/res/4-1.png
