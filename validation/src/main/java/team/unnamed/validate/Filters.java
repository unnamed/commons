package team.unnamed.validate;

import java.util.Arrays;
import java.util.function.Predicate;

/**
 * A collection of utils for filtering objects and more
 * things. Code shortening
 */
public final class Filters {

    private Filters() {
        // this class shouldn't be instantiated
        throw new UnsupportedOperationException();
    }

    /**
     * Checks if any element asserts with the predicate.
     * If an element in the iterable asserts with the predicate,
     * returns true.
     * @param iterable The checked iterable
     * @param predicate The element predicate
     * @param <T> The type of the iterable
     * @return True if an element asserts with the predicate
     */
    public static <T> boolean any(Iterable<? extends T> iterable, Predicate<? super T> predicate) {
        Validate.notNull(iterable, "iterable");
        Validate.notNull(predicate, "predicate");
        for (T element : iterable) {
            if (predicate.test(element)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if any element asserts with the predicate.
     * If an element in the array asserts with the predicate,
     * returns true.
     * @param array The checked array
     * @param predicate The element predicate
     * @param <T> The type of the array
     * @return True if an element asserts with the predicate
     */
    public static <T> boolean any(T[] array, Predicate<? super T> predicate) {
        Validate.notNull(array, "array");
        return any(Arrays.asList(array), predicate);
    }

    /**
     * Checks if all elements matches with the predicate,
     * if an element doesn't, returns false.
     * @param iterable The checked iterable
     * @param predicate The element predicate
     * @param <T> The type of the elements
     * @return True if all elements matches with the predicate
     */
    public static <T> boolean all(Iterable<? extends T> iterable, Predicate<? super T> predicate) {
        Validate.notNull(iterable, "iterable");
        Validate.notNull(predicate, "predicate");
        for (T element : iterable) {
            if (!predicate.test(element)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks if all elements matches with the predicate,
     * if an element doesn't, returns false.
     * @param array The checked array
     * @param predicate The element predicate
     * @param <T> The type of the elements
     * @return True if all elements matches with the predicate
     */
    public static <T> boolean all(T[] array, Predicate<? super T> predicate) {
        Validate.notNull(array);
        return all(Arrays.asList(array), predicate);
    }

}
