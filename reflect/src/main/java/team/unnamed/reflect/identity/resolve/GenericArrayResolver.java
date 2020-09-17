package team.unnamed.reflect.identity.resolve;

import team.unnamed.reflect.identity.TypeReference;
import team.unnamed.reflect.identity.TypeResolver;
import team.unnamed.reflect.identity.Types;
import team.unnamed.validate.Validate;

import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Type;

public class GenericArrayResolver implements TypeResolver {

  @Override
  public Type resolveType(TypeReference<?> context, Type type) {

    Validate.argument(type instanceof GenericArrayType,
        "Type isn't an instance of GenericArrayType!");

    Type componentType = ((GenericArrayType) type).getGenericComponentType();
    Type resolvedComponentType = ContextualTypes.resolveContextually(context, componentType);

    if (componentType == resolvedComponentType) {
      return type;
    }

    return Types.genericArrayTypeOf(resolvedComponentType);
  }

}
