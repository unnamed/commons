package identity;

import java.lang.reflect.Type;
import java.lang.reflect.WildcardType;

class WildcardTypeReference implements WildcardType {

    private final Type upperBound;
    private final Type lowerBound;

    WildcardTypeReference(WildcardType prototype) {
        this(prototype.getUpperBounds(), prototype.getLowerBounds());
    }

    WildcardTypeReference(Type[] upperBounds, Type[] lowerBounds) {

        if (lowerBounds.length == 1) {
            this.lowerBound = Types.wrap(lowerBounds[0]);
            this.upperBound = Object.class;
            return;
        }

        this.lowerBound = null;
        this.upperBound = Types.wrap(upperBounds[0]);
    }

    @Override
    public Type[] getUpperBounds() {
        return new Type[] { upperBound };
    }

    @Override
    public Type[] getLowerBounds() {
        if (lowerBound == null) {
            return Types.EMPTY_TYPE_ARRAY;
        }
        return new Type[] { lowerBound };
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof WildcardType)) {
            return false;
        }
        WildcardType other = (WildcardType) o;
        return Types.typeEquals(this, other);
    }

    @Override
    public int hashCode() {
        return (lowerBound != null ? 31 + lowerBound.hashCode() : 1) ^ (31 + upperBound.hashCode());
    }

    @Override
    public String toString() {
        if (lowerBound != null) {
            return "? super " + Types.asString(lowerBound);
        }
        if (upperBound == Object.class) {
            return "?";
        }
        return "? extends " + Types.asString(upperBound);
    }

}
