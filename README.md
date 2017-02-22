Sample Appium UI Test 
---
The configuration executes Mobile UI tests using Appium on local iOS device and also in TestDroid Cloud. 
Test are executed per device.

* In the Class AndroidTest set the value of your Testdroid APIKEY and local computer name. Login into Testdroid and navigate to “My Account” to get you APIKEY 
* Set the path to your IPA. IPA for iOS is the installable file of the application, it is located in the app folder of the project. 


In order to run the tests, you will need to install [Apache Maven](http://maven.apache.org), and Appium (according to the Appium [installation instructions](https://github.com/appium/appium).

You will then need to start appium, for local test run eg:

    appium

To compile and run test, on a local device and on the cloud run:

    mvn test

Or 

``mvn -Dtest=AppiumiOSTest clean test``
