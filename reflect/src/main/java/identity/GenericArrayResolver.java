package identity;

import team.unnamed.inject.identity.type.ContextualTypes;
import team.unnamed.inject.identity.type.TypeReference;
import team.unnamed.inject.identity.type.Types;

import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Type;

import static team.unnamed.inject.internal.Preconditions.checkArgument;

public class GenericArrayResolver implements TypeResolver {

    @Override
    public Type resolveType(TypeReference<?> context, Type type) {

        checkArgument(type instanceof GenericArrayType, "Type isn't an instance of GenericArrayType!");

        Type componentType = ((GenericArrayType) type).getGenericComponentType();
        Type resolvedComponentType = ContextualTypes.resolveContextually(context, componentType);

        if (componentType == resolvedComponentType) {
            return type;
        }

        return Types.genericArrayTypeOf(resolvedComponentType);

    }

}
