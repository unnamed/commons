package team.unnamed.reflect.identity;

import team.unnamed.validate.Validate;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;

public class TypeReference<T> extends Types.AbstractTypeWrapper implements Types.CompositeType {

	private final Class<? super T> rawType;
	private final Type type;

	@SuppressWarnings("unchecked")
	protected TypeReference() {

		Type superClass = getClass().getGenericSuperclass();

		Validate.isState(superClass instanceof ParameterizedType,
				"Invalid TypeReference creation.");

		ParameterizedType parameterized = (ParameterizedType) superClass;

		this.type = Types.compose(parameterized.getActualTypeArguments()[0]);
		this.rawType = (Class<? super T>) Types.getRawType(type);
		super.components.add(this.type);
	}

	@SuppressWarnings("unchecked")
	TypeReference(Type type) {
		Validate.isNotNull(type);
		this.type = Types.compose(type);
		this.rawType = (Class<? super T>) Types.getRawType(this.type);
		super.components.add(this.type);
	}

	@SuppressWarnings("unchecked")
	TypeReference(Type type, Class<? super T> rawType) {
		Validate.isNotNull(type, "type");
		Validate.isNotNull(rawType, "rawType");
		this.type = Types.compose(type);
		this.rawType = (Class<? super T>) Types.getRawType(rawType); // convert primitives to wrapper types
		super.components.add(this.type);
	}

	public static <T> TypeReference<T> of(Type type) {
		return new TypeReference<>(type);
	}

	public static <T> TypeReference<T> of(Class<?> rawType, Type... typeArguments) {
		Validate.isNotNull(rawType);
		return of(Types.parameterizedTypeOf(null, rawType, typeArguments));
	}

	public static <K, V> TypeReference<Map<K, V>> mapTypeOf(TypeReference<K> key, TypeReference<V> value) {
		return of(Map.class, key.getType(), value.getType());
	}

	public final TypeReference<?> getFieldType(Field field) {
		Validate.isNotNull(field, "field");
		Validate.isTrue(
				field.getDeclaringClass().isAssignableFrom(rawType),
				"Field '%s' isn't present in any super-type of '%s'",
				field.getName(),
				rawType
		);
		Type resolvedType = CompositeTypeReflector.resolveContextually(
				this, field.getGenericType()
		);
		@SuppressWarnings({"rawtypes", "unchecked"})
		TypeReference fieldType = new TypeReference(resolvedType, field.getType());
		return fieldType;
	}

	/**
	 * Unsafe method, type must be the type of a member of this type,
	 * else, it throws a {@link IllegalStateException}
	 */
	public final TypeReference<?> resolve(Type type) {
		Validate.isNotNull(type, "type");
		type = CompositeTypeReflector.resolveContextually(this, type);
		return new TypeReference<>(type);
	}

	public final Class<? super T> getRawType() {
		return rawType;
	}

	public final Type getType() {
		return type;
	}

	/**
	 * Removes the reference for the upper class. For
	 * anonymous classes. If you store a {@link TypeReference}
	 * in cache, you should execute this method before.
	 * @return The type reference.
	 */
	public final TypeReference<T> defer() {
		// This object isn't an instance of
		// an anonymous class
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
		if (o == this) return true;
		if (!(o instanceof TypeReference<?>)) return false;
		TypeReference<?> other = (TypeReference<?>) o;
		return type.equals(other.type);
	}

	@Override
	public final String toString() {
		return Types.getTypeName(type);
	}

	@Override
	protected final Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	@Override
	protected final void finalize() throws Throwable {
		super.finalize();
	}

}
