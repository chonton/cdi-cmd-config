# Use [CDI](http://www.cdi-spec.org/) to inject command line options.

CmdLineParameters is a marker annotation for CDI managed beans indicating they have some attributes that need filling from command line options.  With this library, CDI will inject @Parameter marked attributes with the designated command line option.

```java
public class MyClassThatNeedsConfiguration {

    @Parameter(names = {"-s", "--string"}, description = "A string parameter")
	private String string;
    
    @Parameter(names = {"-l", "--long"}, description = "A long parameter", required = true)
	private Long longValue;
    
    public String doSomethingThatNeedsConfiguration() {
    	ConfigBean config = configCache.get();
        // use values from config
        // ...
	}
}

public class Main {
    public static void main(String ... args) {
        CmdLineParametersInterceptor.setArguments(args);
        
        // .. start up CDI
        WeldContainer container = new Weld().initialize();
    }

}

```

## Features
 * Thread safe
 * Annotation driven command line parsing

### Multiple configuration objects
It is possible to have multiple configured objects.  Each object will be filled with its attributes defined in command line arguments.

### Extra attributes
Attributes specified in the command line options without a corresponding configuration object attribute are ignored.

### Missing attributes
Configuration object attributes without a corresponding command line option are left are set based upon the @Parameter arguments.


## Add to your maven project
```xml
<dependency>
  <groupId>org.honton.chas</groupId>
  <artifactId>cmd-config-cdi</artifactId>
  <version>0.1.0</version>
</dependency>
```

### Mapping Annotations 
[JCommander](http://jcommander.org/) is used to parse arguments and populate the configuration pojos.  Most of the [standard JCommander annotations](http://jcommander.org/apidocs/index.html?com/beust/jcommander/Parameter.html) can be used to direct the arguments to attributes mapping.
