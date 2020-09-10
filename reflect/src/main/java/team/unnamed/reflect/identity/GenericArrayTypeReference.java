package team.unnamed.reflect.identity;

import team.unnamed.reflect.identity.resolve.ContextualTypes;
import team.unnamed.validate.Validate;

import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Type;
import java.util.Objects;

/**
 * GenericArrayType represents an array type
 * whose component type is either a parameterized type or a type variable.
 */
class GenericArrayTypeReference implements GenericArrayType, CompositeType {

    private final Type componentType;

    GenericArrayTypeReference(GenericArrayType prototype) {
        this(prototype.getGenericComponentType());
    }

    GenericArrayTypeReference(Type componentType) {
        Validate.notNull(componentType);
        this.componentType = Types.wrap(componentType);
    }

    @Override
    public Type getGenericComponentType() {
        return this.componentType;
    }

    @Override
    public boolean requiresContext() {
        return ContextualTypes.requiresContext(componentType);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof GenericArrayType)) {
            return false;
        }
        GenericArrayType other = (GenericArrayType) o;
        return Types.typeEquals(this, other);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(componentType);
    }

    @Override
    public String toString() {
        return Types.asString(componentType) + "[]";
    }

}
