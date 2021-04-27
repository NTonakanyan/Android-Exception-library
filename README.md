# Android-Exception-library

[![](https://jitpack.io/v/NTonakanyan/Android-Exception-library.svg)](https://jitpack.io/#NTonakanyan/Android-Exception-library)

Android application crash handler.
When crash your application(throwable, manufacture, deviceModel, versionName, versionCode, deviceModel, androidVersionName, deviceId, threadName, stackTrace) send your slack chanel.
How to implement dependecy
1. need to add meta-data yout application manifaest 
2. am.ABM.ApiKey is applicaton key
3. slack.api.a , slack.api.b, slack.api.c need to get yout slack account 
 	```manifest 
	<meta-data
            android:name="am.ABM.ApiKey"
            android:value="aaasbbb" />
        <meta-data
            android:name="slack.api.a"
            android:value="T01SQ5TN8MT" />
        <meta-data
            android:name="slack.api.b"
            android:value="B01SCGUB5AT" />
	<meta-data
            android:name="slack.api.c"
            android:value="S4eh4AW3vc3UMyLCqrqtaP0r" />
	    ```
	    
4. Add ExceptionHandler.setExceptionHandler() on yout BaseActivity class
5. Add ExceptionHandler.init(this) on your Application class and don't forgot register application class in manifest android:name=".Application"

####  For implementation

```gradle
allprojects {
    repositories {
	maven { url 'https://jitpack.io' }
    }
}
	
dependencies {
	implementation 'com.github.NTonakanyan:Android-Exception-library:Tag'
}
```
