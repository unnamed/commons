package team.unnamed.functional;

import team.unnamed.validate.Validate;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Collection of classes for {@link Map} handling
 */
public final class Maps {

	private Maps() {
		throw new UnsupportedOperationException();
	}

	/**
	 * Creates an empty map and loops all values of the
	 * specified map, executing the mapFunction for all
	 * values and adds the return value to the new map
	 * @param map         The original map
	 * @param mapFunction The map function
	 * @return The new map
	 */
	public static <K, V, V2> Map<K, V2> mapValues(Map<K, V> map,
																								Function<? super V, ? extends V2> mapFunction) {
		Validate.isNotNull(map, "map");
		Validate.isNotNull(mapFunction, "mapFunction");

		Map<K, V2> newMap = new HashMap<>();
		map.forEach((key, value) ->
				newMap.put(key, mapFunction.apply(value))
		);
		return newMap;
	}

	/**
	 * Removes an entry if a key passes the specified
	 * key predicate
	 * @param map          The original map, must be mutable,
	 *                     it will be modified if a key passes
	 *                     the key predicate
	 * @param keyPredicate The key predicate
	 * @return The same map
	 */
	public static <K, V> Map<K, V> removeKeyIf(Map<K, V> map, Predicate<? super K> keyPredicate) {
		Validate.isNotNull(map, "map");
		Validate.isNotNull(keyPredicate, "keyPredicate");

		map.entrySet().removeIf(entry ->
				keyPredicate.test(entry.getKey()));
		return map;
	}

	/**
	 * Removes an entry if a value passes the specified
	 * value predicate.
	 * @param map            The original map, must be mutable,
	 *                       it will be modified if a value passes
	 *                       the value predicate
	 * @param valuePredicate The value predicate
	 * @return The same map
	 */
	public static <K, V> Map<K, V> removeValueIf(Map<K, V> map, Predicate<? super V> valuePredicate) {
		Validate.isNotNull(map, "map");
		Validate.isNotNull(valuePredicate, "valuePredicate");

		map.entrySet().removeIf(entry ->
				valuePredicate.test(entry.getValue()));
		return map;
	}

}
