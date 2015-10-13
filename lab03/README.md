# Lab 3

download the Maven package

```
unzip path_to_maven_package
```

edit in ~/.bashrc

```
PATH=$PATH:/home/ammo/Programs/apache-maven-3.3.3/bin
export PATH
export JAVA_HOME=/usr
```

how to find the dir of java home:

```
which java 
```

don't copy the bin/java part


go to the dir of the project

```
mvn archetype:generate -Dappengine-version=1.9.27 -Dfilter=com.google.appengine.archetypes:
```

* Enter the number 2 to select the archetype remote -> com.google.appengine.archetypes:endpoints-skeleton-archetype from the list of App Engine archetypes.
* Accept the default to use the most recent version.
* When prompted to Define value for property 'groupId', enter the namespace com.example.helloworld to keep this tutorial in sync with the source files at GitHub. (The typical convention is to use a namespace starting with the reversed form of your domain name.)
* When prompted to Define value for property 'artifactId', supply the project name helloworld.
* When prompted to Define value for property 'version', accept the default value.
* When prompted to Define value for property 'package', accept the default value.
* When prompted to confirm your choices, accept the default value (Y).


go to the source directory

```
cd helloworld/src/main/java/com/example/helloworld
```

create the MyBean.java file

```
vi MyBean.java

package com.example.helloworld;

/** The object model for the data we are sending through endpoints */
public class MyBean {

    private String myData;

    public String getData() {
        return myData;
    }

    public void setData(String data) {
        myData = data;
    }
}
```

edit the YourFirstAPI.java file

```
vi YourFirstAPI.java

package com.example.helloworld;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;

import javax.inject.Named;

/** An endpoint class we are exposing */
@Api(name = "myApi",
     version = "v1",
     namespace = @ApiNamespace(ownerDomain = "helloworld.example.com",
                                ownerName = "helloworld.example.com",
                                packagePath=""))
public class YourFirstAPI {

    /** A simple endpoint method that takes a name and says Hi back */
    @ApiMethod(name = "sayHi")
    public MyBean sayHi(@Named("name") String name) {
        MyBean response = new MyBean();
        response.setData("Hi, " + name);

        return response;
    }

}
```

Go back to the root directory of the project and install maven

```
cd helloworld

mvn clean install
```





