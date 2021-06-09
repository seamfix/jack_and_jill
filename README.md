# jack_and_jill
A library that provides the download links of Android apps on Google Playstore

### Dependency
```
<dependency>
    <groupId>com.seamfix.api</groupId>
    <artifactId>googledownloader</artifactId>
    <version>1.0.0-SNAPSHOT</version>
</dependency>
```
### Implementation

Create a config:

```
Config config = new Config();
config.setEmail("your email");
config.setPassword("your password");
config.setAndroidID("3db73058499949ce");

//Set the config:
PlayStoreManager playStoreManager = new PlayStoreManager();
playStoreManager.init(config);
```

To get the download link an app on Google playstore, provide the app's package name like so:
```
String packageName = "com.seamfix.bioregistra";
Application application = new Application(packageName);

//Request the download url:
AppDetailsResponse  appDetails = playStoreManager.getAppDetails(application);
System.out.println(appDetails);
```
