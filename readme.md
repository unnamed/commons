# Unnamed Commons [![Build Status](https://travis-ci.com/unnamed/commons.svg?branch=master)](https://travis-ci.com/unnamed/commons)

Collection of public util and lightweight libraries of Unnamed Development Team. Actually, there're only 4 modules:

- [Reflection](https://github.com/unnamed/commons/tree/master/reflect) for easy handling of types and generic types
  using [TypeReference](https://github.com/unnamed/commons/blob/master/reflect/src/main/java/team/unnamed/reflect/identity/TypeReference.java)
- [Validation](https://github.com/unnamed/commons/tree/master/validation) for creating fail-fast (If an entered argument
  is invalid, the method must not respond as if it were a valid argument) APIs, shorten code, etc.
- [Error](https://github.com/unnamed/commons/tree/master/error) for multiple error logging, track all errors, once all
  have been collected, warn about all, not just the first one
- [Functional](https://github.com/unnamed/commons/tree/master/functional) for programming with a functional-style, using
  Java 8 lambda functional interfaces, lambda method references

These small but useful libraries are used in various other libraries in our organization.