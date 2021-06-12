package team.unnamed.validate;

/**
 * Collection of util methods for
 * parameter, argument, states validation.
 * <p>
 * Created for code shortening and
 * for making legible code. Make fail-fast
 * methods.
 * <p>
 * Example with {@link Validate}:
 * <code>
 * public void setName(String name) {
 * this.name = Validate.notEmpty(name);
 * }
 * </code>
 * Example without {@link Validate}:
 * <code>
 * public void setName(String name) {
 * if (name == null || name.isEmpty()) {
 * throw new IllegalArgumentException("name cannot be null");
 * }
 * this.name = name;
 * }
 * </code>
 * <p>
 * Example with {@link Validate}:
 * <code>
 * public void setTitle(String title) {
 * Validate.state(this.title == null, "The title is already defined");
 * this.title = Validate.notEmpty(title);
 * }
 * </code>
 * Example without {@link Validate}:
 * <code>
 * public void setTitle(String title) {
 * if (this.title != null) {
 * throw new IllegalStateException("The title is already defined");
 * }
 * if (title == null || title.isEmpty()) {
 * throw new IllegalArgumentException("title cannot be null");
 * }
 * this.title = title;
 * }
 * </code>
 */
public final class Validate {

	private Validate() {
		// the class shouldn't be instantiated
		throw new UnsupportedOperationException();
	}

	/**
	 * Validates that the provided object isn't null,
	 * if it is, the method throws an {@link NullPointerException}
	 * with the specified message.
	 * @param object     The checked object
	 * @param message    The exception message
	 * @param parameters Parameters for the message,
	 *                   message is formatted using
	 *                   {@link String#format}.
	 * @param <T>        The type of the checked object
	 * @return The object, never null
	 * @throws NullPointerException if object is null
	 */
	public static <T> T isNotNull(T object, String message, Object... parameters) {
		if (object == null) {
			throw new NullPointerException(String.format(message, parameters));
		} else {
			return object;
		}
	}

	/**
	 * Validates that the provided object isn't null,
	 * if it is, the method throws an {@link NullPointerException}
	 * @param object The checked object
	 * @param <T>    The type of the checked object
	 * @return The object, never null
	 * @throws NullPointerException if object is null
	 */
	public static <T> T isNotNull(T object) {
		return isNotNull(object, null);
	}

	/**
	 * Validates that the specified expression is true,
	 * if it is false, the method throws a {@link IllegalStateException}
	 * with the specified message.
	 * @param expression The checked expression
	 * @param message    The message for the thrown exception
	 * @param parameters The parameters for the exception
	 *                   message. The message is formatted
	 *                   using {@link String#format}.
	 * @throws IllegalStateException If expression is false
	 */
	public static void isState(boolean expression, String message, Object... parameters) {
		if (!expression) {
			throw new IllegalStateException(String.format(message, parameters));
		}
	}

	/**
	 * Validates that the specified expression is true,
	 * if it is false, the method throws a {@link IllegalStateException}
	 * @param expression The checked expression
	 * @throws IllegalStateException If expression is false
	 */
	public static void isState(boolean expression) {
		isState(expression, null);
	}

	/**
	 * Validates that the specified expression is true,
	 * if it is false, the method throws a {@link IllegalArgumentException}
	 * with the specified message
	 * @param expression The checked expression
	 * @param message    The message for the thrown exception
	 * @param parameters The parameters for the exception
	 *                   message. The message is formatted
	 *                   using {@link String#format}.
	 * @throws IllegalArgumentException If expression is false
	 */
	public static void isTrue(boolean expression, String message, Object... parameters) {
		if (!expression) {
			throw new IllegalArgumentException(String.format(message, parameters));
		}
	}

	/**
	 * Validates that the specified expression is true,
	 * if it is false, the method throws a {@link IllegalArgumentException}
	 * @param expression The checked expression
	 * @throws IllegalArgumentException If expression is false
	 */
	public static void isTrue(boolean expression) {
		isTrue(expression, null);
	}

	/**
	 * Validates that the specified string is
	 * not null and not empty. If the string is
	 * null, throws a {@link NullPointerException},
	 * if the string is empty, throws a
	 * {@link IllegalArgumentException}.
	 * @param string     The checked string
	 * @param message    The message used for the messages
	 * @param parameters The parameters for the exception
	 *                   message. The message is formatted
	 *                   using {@link String#format}.
	 * @return The string, not null and not empty
	 * @throws NullPointerException     if the string is null
	 * @throws IllegalArgumentException If the string is empty
	 */
	public static String isNotEmpty(String string, String message, Object... parameters) {
		if (string == null) {
			throw new NullPointerException(String.format(message, parameters));
		} else if (string.isEmpty()) {
			throw new IllegalArgumentException(String.format(message, parameters));
		}
		return string;
	}

	/**
	 * Validates that the specified string is
	 * not null and not empty. If the string is
	 * null, throws a {@link NullPointerException},
	 * if the string is empty, throws a
	 * {@link IllegalArgumentException}.
	 * @param string The checked string
	 * @return The string, not null and not empty
	 * @throws NullPointerException     if the string is null
	 * @throws IllegalArgumentException If the string is empty
	 */
	public static String isNotEmpty(String string) {
		return isNotEmpty(string, null);
	}

}
