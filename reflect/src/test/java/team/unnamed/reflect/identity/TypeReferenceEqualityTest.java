package team.unnamed.reflect.identity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class TypeReferenceEqualityTest {

  @Test
  public void test() throws Exception {

    TypeReference<List<?>> listReference = new TypeReference<List<?>>() {};
    TypeReference<List<String>> stringListReference = new TypeReference<List<String>>() {};

    Assertions.assertNotEquals(listReference, stringListReference);

    TypeReference<List<String>> stringListReference2 = new TypeReference<List<String>>() {};

    Assertions.assertEquals(stringListReference, stringListReference2);
    Assertions.assertEquals(stringListReference.hashCode(), stringListReference2.hashCode());

    TypeReference<Foo<String>> fooTypeReference = new TypeReference<Foo<String>>() {};
    Field listField = Foo.class.getDeclaredField("list");
    TypeReference<?> listTypeReference = fooTypeReference.getFieldType(listField);

    Assertions.assertEquals(stringListReference, listTypeReference);
  }

  private static class Foo<T> {
    private final List<T> list = new ArrayList<>();
  }

}
