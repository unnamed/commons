package team.unnamed.reflect.identity;

import team.unnamed.reflect.identity.resolve.ContextualTypes;
import team.unnamed.validate.Validate;

import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.List;

public class TypeReference<T> {

  private final Class<? super T> rawType;
  private final Type type;

  @SuppressWarnings("unchecked")
  protected TypeReference() {

    Type superClass = getClass().getGenericSuperclass();

    Validate.state(superClass instanceof ParameterizedType,
        "Invalid TypeReference creation.");

    ParameterizedType parameterized = (ParameterizedType) superClass;

    this.type = Types.wrap(parameterized.getActualTypeArguments()[0]);
    this.rawType = (Class<? super T>) Types.getRawType(type);
  }

  @SuppressWarnings("unchecked")
  public TypeReference(Type type) {
    Validate.notNull(type);
    this.type = Types.wrap(type);
    this.rawType = (Class<? super T>) Types.getRawType(this.type);
  }

  private TypeReference(Type type, Class<? super T> rawType) {
    Validate.notNull(type, "type");
    Validate.notNull(rawType, "rawType");
    this.type = type;
    this.rawType = rawType;
  }

  public final Class<? super T> getRawType() {
    return rawType;
  }

  public final Type getType() {
    return type;
  }

  /**
   * Resolves the field fully-specified type.
   *
   * @param field The field
   * @return The resolved type
   * @throws IllegalArgumentException If the field isn't present
   *                                  in this class or any superclass
   */
  public final TypeReference<?> getFieldType(Field field) {

    Validate.notNull(field, "field");
    Validate.argument(field.getDeclaringClass().isAssignableFrom(rawType),
        field.getName() + " isn't defined by " + toString());

    Type fieldType = ContextualTypes.resolveContextually(
        this, field.getGenericType()
    );

    return new TypeReference<>(fieldType);
  }

  /**
   * Resolves all the parameter types of the specified
   * method or constructor.
   *
   * @param member The method or constructor.
   * @return The resolved types
   * @throws IllegalArgumentException If the member isn't
   *                                  defined by this class or any superclass.
   */
  public final List<TypeReference<?>> getParameters(Member member) {

    Validate.argument(member instanceof Method || member instanceof Constructor,
        "Not a method or a constructor: " + member.getName());
    Validate.argument(member.getDeclaringClass().isAssignableFrom(rawType),
        member.getName() + " isn't defined by " + toString());

    Executable executable = (Executable) member;
    List<TypeReference<?>> parameters = new ArrayList<>();

    for (Type genericParameterType : executable.getGenericParameterTypes()) {
      parameters.add(new TypeReference<>(genericParameterType));
    }

    return parameters;
  }

  public final TypeReference<?> getReturnType(Method method) {

    Validate.notNull(method, "method");
    Validate.argument(method.getDeclaringClass().isAssignableFrom(rawType),
        method.getName() + " isn't defined by " + toString());

    return new TypeReference<>(method.getGenericReturnType());
  }

  /**
   * Removes the reference for the upper class. For
   * anonymous classes. If you store a {@link TypeReference}
   * in cache, you should execute this method before.
   *
   * @return The type reference.
   */
  public final TypeReference<T> canonicalize() {
    if (getClass() == TypeReference.class) {
      return this;
    } else {
      return new TypeReference<>(type, rawType);
    }
  }

  @Override
  public final int hashCode() {
    return type.hashCode();
  }

  @Override
  public final boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (!(o instanceof TypeReference<?>)) {
      return false;
    }

    TypeReference<?> other = (TypeReference<?>) o;
    return Types.typeEquals(type, other.type);
  }

  @Override
  public final String toString() {
    return Types.asString(type);
  }

  @Override
  protected final Object clone() throws CloneNotSupportedException {
    return super.clone();
  }

  @Override
  protected final void finalize() throws Throwable {
    super.finalize();
  }

  public static <T> TypeReference<T> of(Type type) {
    return new TypeReference<>(type);
  }

  public static TypeReference<?> of(Type rawType, Type... typeArguments) {
    Validate.notNull(rawType);
    return of(new ParameterizedTypeReference(null, rawType, typeArguments));
  }

}