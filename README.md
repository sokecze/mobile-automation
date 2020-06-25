# A simple framework for mobile automation

### Structure

There are 3 modules of the project:
- **framework-commons** contains all the necessary elements and driver actions
- **unitconverter** is an example of Android app automation implementation
- **iosexample** is an example of iOS app automation implementation

### Configuration

In order to implement the framework, _config.properties_ and _log4j2-test.properties_ files should be created in src/main/resources directory (see examples).
The structure of _config.properties_ is the following:
```
appium.hub=

#Optional, override if non-default
node.path=
appium.path=

device.name=
device.platform=

app.installed=

#Optional, provide if app is not installed
app.file=

#Android only, provide if app is installed
app.package=
app.activity=

#iOS only, provide if app is installed
app.bundleId=
```

### Test Running

To run a test example, find the appropriate module and run the following:
`mvn clean test`

To generate an Allure report, use the following command:
`mvn allure:report`