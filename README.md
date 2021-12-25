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
            android:value="you key here" />
        <meta-data
            android:name="slack.api.b"
            android:value="you key here" />
	<meta-data
            android:name="slack.api.c"
            android:value="you key here" />
	    ```
	    
4. Add ExceptionHandler.setExceptionHandler() on yout BaseActivity class
5. Add ExceptionHandler.init(this) on your Application class and don't forgot register application class in manifest android:name=".Application"

####  For implementation

```gradle
allprojects {
    repositories {
	        maven { url = "https://maven.pkg.github.com/NTonakanyan/Android-Exception-library" }
    }
}
	
dependencies {
	implementation 'com.github.NTonakanyan:exception-library:1.0.0'
}
```
