package identity;

import team.unnamed.validate.Validate;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public abstract class TypeReference<T> {

    private final Class<? super T> rawType;
    private final Type type;

    @SuppressWarnings("unchecked")
    protected TypeReference() {

        Type superClass = getClass().getGenericSuperclass();

        Validate.state(!(superClass instanceof Class), "There're no type parameters!");

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

    public final Class<? super T> getRawType() {
        return rawType;
    }

    public final Type getType() {
        return type;
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

    public static <T> TypeReference<T> of(Type type) {
        return new TypeReference<T>(type) {};
    }

    public static TypeReference<?> of(Type rawType, Type... typeArguments) {
        Validate.notNull(rawType);
        return of(new ParameterizedTypeReference(null, rawType, typeArguments));
    }

}