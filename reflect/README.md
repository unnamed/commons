# Reflect Utils
There're some classes for easy handling of types, generic types, and
other things with reflection. This module contains three principal util classes:
 - [TypeReference](https://github.com/unnamed/commons/blob/master/reflect/src/main/java/team/unnamed/reflect/identity/TypeReference.java) (An
util class for handling generic and raw types, like [Google Gson's TypeToken](https://github.com/google/gson/blob/master/gson/src/main/java/com/google/gson/reflect/TypeToken.java))
- [Types](https://github.com/unnamed/commons/blob/master/reflect/src/main/java/team/unnamed/reflect/identity/Types.java) (A collection of util methods
for resolving types and constructing `TypeReference` objects)
- [ContextualTypes](https://github.com/unnamed/commons/blob/master/reflect/src/main/java/team/unnamed/reflect/identity/resolve/ContextualTypes.java). Util methods for contextually-resolve types (TypeVariables)

## Usage
The main use of `TypeReference` is to easily manipulate (compare, store, etc) generic types.
You can see some examples in [syringe](https://github.com/unnamed/syringe) (A dependency injection framework)


```java
// Here we create a type reference that holds a List<String> generic type
TypeReference<List<String>> listType = new TypeReference<List<String>>() {};

// Now we check all the method return types and parameters (fully-specified)
for (Method method : List.class.getDeclaredMethods()) {
  List<TypeReference<?>> parameterTypes = listType.getParameters(method);
  TypeReference<?> returnType = listType.getReturnType(method);
}
```
A type is `fully-specified` when it doesn't require a context or a `GenericDeclaration` to
be completed. For example:

```java
public class Foo<T> { // GenericDeclaration

    private final List<T> list; // non-fully-specified type
    private final Set<String> set; // fully-specified type!

    // ...
}
```
Here, the `list` cannot be completed if there isn't a GenericDeclaration.
`TypeReference`, `Types` and `ContextualTypes` helps you with this.

## Dependency
You can download the JAR in the repository releases, also you can
just add the dependency to your `pom.xml`

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
    <artifactId>reflect</artifactId>
    <version>VERSION</version>
</dependency>
```