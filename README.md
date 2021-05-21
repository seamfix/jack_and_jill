# jack_and_jill
A library that provides the download links of Android apps on Google Playstore

### Setup
1) Download and install Raccoon.jar from here: https://raccoon.onyxbits.de
2) Login to Raccoonn with your Google Play gmail account.
3) Choose to register a new pseudo device or mimick an existing device.
4) Then add the following dependency to your maven:

### Dependency
```
<dependency>
  <groupId>com.seamfix.sdk</groupId>
  <artifactId>jackandjill</artifactId>
  <version>1.0.0</version>
</dependency>
```
### Implementation
To get the download link an app on Google playstore, provide the app's package name like so:
```
String packageName = "com.seamfix.bioregistra";
Application application = new Application(packageName);

PlayStoreManager.getDownloadUrl(application, new OperationResponse() {
    public void success(String downloadUrl) {
        System.out.println("Download url: "+ downloadUrl);
    }

    public void error(String errorMessage) {
        System.out.println("Error message: "+errorMessage);
    }
});
```
