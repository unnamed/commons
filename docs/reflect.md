# Reflect Utils

There're some classes for easy handling of types, generic types, and other things with reflection. This module contains
three principal util classes:

- `TypeReference` An util class to handle Java types
- `Types` A collection of util methods for resolving types and constructing `TypeReference` objects
- `CompositeTypeReflector` To resolve type variables

## Usage

The main use of `TypeReference` is to easily manipulate (compare, store, etc)
Java types, just like a Java `Class` type but supporting parameterized types,
wildcard types and more

```java
// Here we create a type reference that holds a List<String> generic type
TypeReference<List<String>> listType = new TypeReference<List<String>>() {};

// Now we check all the method return types and parameters (fully-specified)
for (Method method : List.class.getDeclaredMethods()) {
  List<TypeReference<?>> parameterTypes = listType.getParameters(method);
  TypeReference<?> returnType = listType.getReturnType(method);
}
```

A type is `fully-specified` when it doesn't require a context or a
`GenericDeclaration` to be completed. For example:

```java
public class Foo<T> { // GenericDeclaration

    private final List<T> list; // non-fully-specified type
    private final Set<String> set; // fully-specified type!

    // ...
}
```

Here, the `list` cannot be completed if there isn't a GenericDeclaration.
`TypeReference`, `Types` and `CompositeTypeReflector` help you with this

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
    <artifactId>commons-reflect</artifactId>
    <version>VERSION</version>
</dependency>
```