package identity;

import team.unnamed.inject.identity.type.ContextualTypes;
import team.unnamed.inject.identity.type.TypeReference;
import team.unnamed.inject.identity.type.Types;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import static team.unnamed.inject.internal.Preconditions.checkState;

public class ParameterizedTypeResolver implements TypeResolver {

    @Override
    public Type resolveType(TypeReference<?> context, Type type) {

        checkState(type instanceof ParameterizedType, "Type isn't instance of ParameterizedType!");

        ParameterizedType parameterizedType = (ParameterizedType) type;
        Type ownerType = parameterizedType.getOwnerType();
        Type resolvedOwnerType = ContextualTypes.resolveContextually(context, ownerType);
        boolean changed = resolvedOwnerType != ownerType;

        Type[] actualTypeArgs = parameterizedType.getActualTypeArguments();

        for (int i = 0; i < actualTypeArgs.length; i++) {

            Type argument = actualTypeArgs[i];
            Type resolvedTypeArgument = ContextualTypes.resolveContextually(context, argument);

            if (resolvedTypeArgument == argument) {
                continue;
            }

            if (!changed) {
                actualTypeArgs = actualTypeArgs.clone();
                changed = true;
            }

            actualTypeArgs[i] = resolvedTypeArgument;

        }

        if (changed) {
            return Types.parameterizedTypeOf(resolvedOwnerType, parameterizedType.getRawType(), actualTypeArgs);
        }

        return parameterizedType;

    }

}
