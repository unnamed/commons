package identity;

import team.unnamed.inject.identity.type.TypeReference;
import team.unnamed.inject.identity.type.Types;

import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Objects;

import static team.unnamed.inject.internal.Preconditions.checkNotNull;

public abstract class Key<T> {

    private final TypeReference<T> type;
    private final Class<? extends Annotation> qualifierType;
    private final Annotation qualifier;

    protected Key(Class<? extends Annotation> qualifierType, Annotation qualifier) {

        Type superClass = getClass().getGenericSuperclass();

        if (superClass instanceof Class) {
            throw new IllegalStateException("There're no type parameters!");
        }

        ParameterizedType parameterized = (ParameterizedType) superClass;

        this.type = TypeReference.of(parameterized.getActualTypeArguments()[0]);

        if (qualifier != null && qualifierType == null) {
            this.qualifierType = qualifier.annotationType();
        } else {
            this.qualifierType = qualifierType;
        }

        this.qualifier = qualifier;

    }

    protected Key() {
        this(null, null);
    }

    private Key(TypeReference<T> typeReference, Class<? extends Annotation> qualifierType, Annotation qualifier) {
        this.type = checkNotNull(typeReference);
        this.qualifierType = qualifierType;
        this.qualifier = qualifier;
    }

    public final TypeReference<T> getType() {
        return type;
    }

    public final Annotation getQualifier() {
        return qualifier;
    }

    public final Class<? extends Annotation> getQualifierType() {
        return qualifierType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Key)) return false;
        Key<?> key = (Key<?>) o;
        return key.type.equals(type) &&
                Objects.equals(qualifierType, key.qualifierType) &&
                Objects.equals(qualifier, key.qualifier);
    }

    @Override
    public int hashCode() {
        return type.hashCode() * 31 + (qualifierType == null ? 0 : qualifierType.hashCode())
                + (qualifier == null ? 0 : qualifier.hashCode());
    }

    @Override
    public String toString() {
        return Types.asString(type.getType());
    }

    public static <T> Key<T> of(Class<T> type) {
        return of(type, null, null);
    }

    public static <T> Key<T> of(Class<T> type, Annotation annotation) {
        return of(type, annotation.annotationType(), annotation);
    }

    public static <T> Key<T> of(Class<T> type, Class<? extends Annotation> qualifierType, Annotation qualifier) {
        return of(TypeReference.of(type), qualifierType, qualifier);
    }

    public static <T> Key<T> of(TypeReference<T> type) {
        return of(type, null, null);
    }

    public static <T> Key<T> of(TypeReference<T> type, Annotation annotation) {
        return of(type, annotation.annotationType(), annotation);
    }

    public static <T> Key<T> of(TypeReference<T> type, Class<? extends Annotation> qualifierType, Annotation qualifier) {
        return new Key<T>(type, qualifierType, qualifier) {};
    }

}
