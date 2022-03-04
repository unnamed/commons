# Validation

Library for create fail-fast APIs and shorten code

## Examples

For example, validating normally

```java
public void setPages(List<String> pages) {
    if (this.pages != null) {
        throw new IllegalStateException("Pages is already defined");
    }
    if (pages == null) {
        throw new NullPointerException("pages cannot be null");
    }
    for (String page : pages) {
        if (page.length() > 500) {
            throw new IllegalArgumentException("a page cannot contain more than 500 characters");
        }
    }
    this.pages = pages;
}
```

Using this library:

```java
public void setPages(List<String> pages) {
    Validate.state(this.pages == null, "Pages is already defined!");
    Validate.notNull(pages, "Pages cannot be null");
    Validate.argument(Filters.all(page -> page.length() < 500), "A page cannot contain more than 500 characters");
    this.pages = pages;
}
```

## Why fail-fast?

Fail-fast methodology is very helpful for debugging. Functions that fail quickly
in case of providing invalid input make it easier to search for errors

This library helps you create fail-fast functions without the need to write so
much code. In addition, it helps you easily create messages for errors

For example, in a non-fail-fast method:

```java
public String format(String text) {
    if (text == null) {
        return null;
    }
    return /* format the text */;
}
```

Then

```java
String message = messages.get("message-name"); // nullable, this is the bug
String formattedMessage = format(message); // null message, null formattedMessage
// ...do other things with non-fail-fast-methods...
log(formattedMessage.replace('.', '/')); // NullPointerException!
```

In this case, the error was not reported from the beginning, it was reported later. In this case, you would be seeing
the exception's stack-trace, checking on which line it failed, which variable could be null, checking on which line this
variable was created, etc.

With a fail-fast method:

```java
public String format(String text) {
    Validate.notNull(text, "text cannot be null");
    return /* format the text */;
}
```

Then

```java
String message = messages.get("message-name"); // nullable, this is the bug
String formattedMessage = format(message); // in case of null, fails
// ...do other things...
log(formattedMessage.replace('.', '/'));
```

The method fails instantly on passing it a null reference, it fails with a
descriptive message about what failed. The error is easily found


## Dependency

You can download the JAR in the repository releases, or add the dependency to
Gradle build-script or your Maven project object model

Repository:

```xml
<repository>
    <id>unnamed-public</id>
    <url>https://repo.unnamed.team/repository/unnamed-public/</url>
</repository>
```

Dependency:

```xml
<dependency>
    <groupId>team.unnamed</groupId>
    <artifactId>commons-validation</artifactId>
    <version>VERSION</version>
</dependency>
```