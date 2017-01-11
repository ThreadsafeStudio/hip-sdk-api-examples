# HIP/SDK example project
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

## Steps to reproduce this example

### 1. Download a Spring Boot Starter project for Spring Web from http://start.spring.io/
![image](https://cloud.githubusercontent.com/assets/193289/21809535/7c78b4a8-d748-11e6-824e-9db3fe13ce97.png)

### 2. Unpack the zip file you downloaded in the last step and open in your IDE. 

We recommend IntelliJ.

### 3. Add hip-release-repository to `pom.xml`

See `pom.xml`: https://github.com/ThreadsafeStudio/getting-hiped/blob/master/pom.xml#L27

Enable auto-import in your IDE to have dependencies downloaded automatically:
![image](https://cloud.githubusercontent.com/assets/193289/21809805/86fce0c4-d749-11e6-909f-d7b4d12290c0.png)

### 4. Add `hip-release-repository` server to your `~/.m2/settings.xml`

```
<settings xmlns="http://maven.apache.org/SETTINGS/1.0.0"
          xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
          xsi:schemaLocation="http://maven.apache.org/SETTINGS/1.0.0
                          https://maven.apache.org/xsd/settings-1.0.0.xsd">
    <servers>
       <server>
         <id>hip-release-repository</id>
         <username>hip-external-user</username>
         <password>123456qwerty</password>
       </server>
    </servers>
</settings>
```

### 5. Add dependencies to `pom.xml`

```
 <dependency>
    <groupId>se.hip.sdk</groupId>
    <artifactId>hip-sdk</artifactId>
    <version>2.1.8</version>
</dependency>
<dependency>
    <groupId>se.hip.sdk</groupId>
    <artifactId>pdl-access-control-service</artifactId>
    <version>2.1.8</version>
</dependency>
<dependency>
    <groupId>se.hip.sdk</groupId>
    <artifactId>caredocumentation-service</artifactId>
    <version>2.1.8</version>
</dependency>
```
See: https://github.com/ThreadsafeStudio/getting-hiped/blob/master/pom.xml#L47

### 6. Add HipSDKConfiguration file
See: https://github.com/ThreadsafeStudio/getting-hiped/blob/master/src/main/java/com/threadsafestudio/hip/HipSDKConfiguration.java

### 7. Modify main class to work with HIP/SDK.
See: https://github.com/ThreadsafeStudio/getting-hiped/commit/b70a858311249afb81ff17ae31d18c6d998362ce#diff-f49882b3169f6b42da956d59381c3b4e

### 8. Add a controller, e.g. CareDocumentationController
See: https://github.com/ThreadsafeStudio/getting-hiped/blob/master/src/main/java/com/threadsafestudio/hip/CareDocumentationController.java

This controller posts consent on every request, which you wouldn't do in a real application, but it serves for demonstration purposes. Consent is posted here: https://github.com/ThreadsafeStudio/getting-hiped/blob/master/src/main/java/com/threadsafestudio/hip/CareDocumentationController.java#L52
