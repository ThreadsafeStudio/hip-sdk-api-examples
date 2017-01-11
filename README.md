# HIP/SDK examples
Example project for running HIP/SDK, following instructions at http://hip.se/getting-hiped

## Running this example

### Add your license file
Following step 1 here: http://hip.se/getting-hiped 

### Install dependencies
Either use Maven from the command line:

```
mvn install
```

or use your IDE:

![image](https://cloud.githubusercontent.com/assets/193289/21809805/86fce0c4-d749-11e6-909f-d7b4d12290c0.png)

### Run the application
Either use Maven from the command line:
```
mvn spring-boot:run
```

Or use your IDE, e.g. IntelliJ
![image](https://cloud.githubusercontent.com/assets/193289/21815594/94a41288-d75d-11e6-8acb-792e6ad7fb9b.png)

### Send some requests
```
curl localhost:8080/caredocumentation
```

If you want pretty-printed JSON:
```
curl localhost:8080/caredocumentation | python -m json.tool
```
