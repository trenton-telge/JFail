# JFail
Java swing lightweight error reporting library

JFail currently only has three methods to be called:

```java
JFail.showErrorDialog(Exception exception, boolean isFatal)
```
Shows an error dialog for the specified error with an "OK" button. Quits parent program after delay if fatal.


```java
JFail.printErrorReport(Exception exception, String path, boolean full)
```
Prints an error report with stack trace to specified path. If full is set, includes system info.

```java
JFail.sendErrorReport(Exception exception, String to, String from, String programName)
```
Sends stack trace to "to" email address.
