package identity;


import java.lang.reflect.GenericDeclaration;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;

public class TypeVariableResolver implements TypeResolver {

    @Override
    public Type resolveType(TypeReference<?> context, Type toResolve) {

        checkArgument(toResolve instanceof TypeVariable, "Type isn't an instance of TypeVariable!");

        Type resolvedType = resolveTypeVariable(
                context.getType(), context.getRawType(), (TypeVariable<?>) toResolve
        );

        if (resolvedType == toResolve) {
            return resolvedType;
        }

        return ContextualTypes.resolveContextually(context, resolvedType);
    }

    public static Type resolveTypeVariable(Type type, Class<?> rawType, TypeVariable<?> resolvingType) {

        GenericDeclaration context = resolvingType.getGenericDeclaration();

        if (!(context instanceof Class)) {
            return resolvingType;
        }

        Class<?> rawContext = (Class<?>) context;
        Type contextType = ContextualTypes.getSupertype(type, rawType, rawContext);

        if (!(contextType instanceof ParameterizedType)) {
            return resolvingType;
        }

        ParameterizedType parameterizedContext = (ParameterizedType) contextType;
        TypeVariable<?>[] typeParameters = rawContext.getTypeParameters();

        for (int i = 0; i < typeParameters.length; i++) {
            TypeVariable<?> typeVariable = typeParameters[i];
            if (typeVariable.equals(resolvingType)) {
                return parameterizedContext.getActualTypeArguments()[i];
            }
        }

        throw new IllegalStateException("There's no a resolvable type variable!");

    }

}
