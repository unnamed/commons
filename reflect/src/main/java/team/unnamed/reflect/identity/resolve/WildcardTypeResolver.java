package team.unnamed.reflect.identity.resolve;

import team.unnamed.reflect.identity.TypeReference;
import team.unnamed.reflect.identity.TypeResolver;
import team.unnamed.reflect.identity.Types;
import team.unnamed.validate.Validate;

import java.lang.reflect.Type;
import java.lang.reflect.WildcardType;

public class WildcardTypeResolver implements TypeResolver {

  @Override
  public Type resolveType(TypeReference<?> context, Type type) {

    Validate.argument(type instanceof WildcardType, "Type isn't instanceof WildcardType!");

    WildcardType wildcard = (WildcardType) type;
    Type[] lowerBounds = wildcard.getLowerBounds();
    Type[] upperBounds = wildcard.getUpperBounds();

    if (lowerBounds.length == 1) {
      Type lowerBound = lowerBounds[0];
      Type resolvedLowerBound = ContextualTypes.resolveContextually(context, lowerBound);
      if (lowerBound == resolvedLowerBound) {
        return type;
      }
      return Types.wildcardSuperTypeOf(resolvedLowerBound);
    }

    if (upperBounds.length == 1) {
      Type upperBound = upperBounds[0];
      Type resolvedUpperBound = ContextualTypes.resolveContextually(context, upperBound);
      if (resolvedUpperBound != upperBound) {
        return Types.wildcardSubTypeOf(resolvedUpperBound);
      }
    }

    return type;
  }

}
