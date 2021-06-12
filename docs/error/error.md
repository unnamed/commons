# Error

Library for multiple-error handling and complete error report.

## Examples

We usually just throw an exception if something is wrong

```java
for (Listener listener : getListenersForEvent(event)) {
    try {
        listener.listen(event);
    } catch (Exception e) {
        throw new EventException("Cannot pass event " + event.getName(), e); 
    }
}
```

This only reports the first error found, bad if we want to fix all the errors without having to compile, test, find
errors, fix and so on with all the errors, because it will be reported one at a time.

But with this library:

```java
// the main message of the errors
ErrorDetails errors = new ErrorDetails("Cannot pass event " + event.getName());
for (Listener listener : getListenersForEvent(event)) {
    try {
        listener.listen(event);
    } catch (Exception e) {
        // error is added, not thrown
        errors.add(e);
    }
}
// then, throw all exceptions once
throw errors.toException(EventException::new);
```

This reports all errors, not only the first. It also prints a pretty message with all the stack traces in this format:

```cs
Exception in thread <thread> <package>.EventException: Cannot pass event <eventName>
1) java.lang.NullPointerException: null
    at sample.stack.Trace.make(Trace.java:25)
    at sample.stack.Trace.make(Trace.java:34)
    at sample.stack.Trace.create(Trace.java:50)
    at sample.listener.Listener.listen(Listener.java:32)

2) java.lang.IllegalStateException: The library is so cool
    at sample.stack.Trace.make(Trace.java:25)
    at sample.stack.Trace.make(Trace.java:34)
    at sample.stack.Trace.create(Trace.java:50)
    at sample.listener.Listener.listen(Listener.java:32)
```

All stack-traces enumerated and formatted

## Dependency

You can download the JAR in the repository releases, also you can just add the dependency to your `pom.xml`

Repository:

```xml
<repository>
    <id>unnamed-releases</id>
    <url>https://repo.unnamed.team/repository/unnamed-releases/</url>
</repository>
```

Dependency:

```xml
<dependency>
    <groupId>team.unnamed.common</groupId>
    <artifactId>error</artifactId>
    <version>VERSION</version>
</dependency>
```