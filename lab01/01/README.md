# Opgave 1

Voor de datalaag van de onderstaande GUI zullen we gebruik maken van de SOAP-webservice [DictService][dict].

Bekijk de WSDL en test enkele operaties uit via de browser: [link][wsdl]. 

Vraagjes:

* Wat is de naam van de aangeboden service?

```
Word Dictionary Web Service
```  

* Welke poorten worden er gedefinieerd?

```
port name="DictServiceSoap"
port name="DictServiceSoap12"
port name="DictServiceHttpGet"
port name="DictServiceHttpPost"
```

* Welke transportprotocol gebruikt de DictServiceSoap-binding?

```
http
```

* Welke operaties worden er in de DictServiceSoap-binding gedefinieerd?

```
<wsdl:binding name="DictServiceSoap" type="tns:DictServiceSoap">
    <soap:binding transport="http://schemas.xmlsoap.org/soap/http"/>
    <wsdl:operation name="ServerInfo"></wsdl:operation>
    <wsdl:operation name="DictionaryList"></wsdl:operation>
    <wsdl:operation name="DictionaryListExtended"></wsdl:operation>
    <wsdl:operation name="DictionaryInfo"></wsdl:operation>
    <wsdl:operation name="Define"></wsdl:operation>
    <wsdl:operation name="DefineInDict"></wsdl:operation>
    <wsdl:operation name="StrategyList"></wsdl:operation>
    <wsdl:operation name="Match"></wsdl:operation>
    <wsdl:operation name="MatchInDict"></wsdl:operation>
</wsdl:binding>
```

* Welke parameters verwacht de operatie MatchInDict?

```
<wsdl:message name="MatchInDictHttpPostIn">
    <wsdl:part name="dictId" type="s:string"/>
    <wsdl:part name="word" type="s:string"/>
    <wsdl:part name="strategy" type="s:string"/>
</wsdl:message>
```

* Wat retourneert de operatie MatchInDict? 

```
<s:element name="MatchInDictResponse">
    <s:complexType>
         <s:sequence>
             <s:element minOccurs="0" maxOccurs="1" name="MatchInDictResult" type="tns:ArrayOfDictionaryWord"/>
         </s:sequence>
    </s:complexType>
</s:element>
```

[dict]: http://services.aonaware.com/DictService/DictService.asmx
[wsdl]: http://services.aonaware.com/DictService/DictService.asmx?WSDL
