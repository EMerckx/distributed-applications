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

Noem het project BadmintonClientConsole
```

We voegen dan een service reference toe

```
Rechtermuis op project > Add Service Reference
Via Discover kunnen we de juiste service vinden

We kiezen voor Service1.svc
Als naam geven we BadmintonServiceReference in
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
                   contract="BadmintonServiceReference.IService1">
          <identity>
            <dns value="localhost"/>
          </identity>
        </endpoint>
        <endpoint contract="IMetadataExchange" binding="mexTcpBinding" 
                  address="mex"/>
        <host>
          <baseAddresses>
            <add baseAddress="net.tcp://localhost:2927/Service1.svc"/>
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
    <!--<bindings>
            <basicHttpBinding>
                <binding name="BasicHttpBinding_IService1" />
            </basicHttpBinding>
        </bindings>
        <client>
            <endpoint address="http://localhost:2927/Service1.svc" 
                binding="basicHttpBinding"
                bindingConfiguration="BasicHttpBinding_IService1" 
                contract="BadmintonServiceReference.IService1"
                name="BasicHttpBinding_IService1" />
        </client>-->
  </system.serviceModel>
</configuration>
```

[4-1]: https://raw.githubusercontent.com/EMerckx/distributed-applications/master/lab04-05/res/4-1.png
