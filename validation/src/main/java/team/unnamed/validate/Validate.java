package team.unnamed.validate;

/**
 * Collection of util methods for
 * parameter, argument, states validation.
 *
 * Created for code shortening and
 * for making legible code. Make fail-fast
 * methods.
 *
 * Example with {@link Validate}:
 * <code>
 *     public void setName(String name) {
 *          this.name = Validate.notEmpty(name);
 *     }
 * </code>
 * Example without {@link Validate}:
 * <code>
 *     public void setName(String name) {
 *         if (name == null || name.isEmpty()) {
 *             throw new IllegalArgumentException("name cannot be null");
 *         }
 *         this.name = name;
 *     }
 * </code>
 *
 * Example with {@link Validate}:
 * <code>
 *     public void setTitle(String title) {
 *         Validate.state(this.title == null, "The title is already defined");
 *         this.title = Validate.notEmpty(title);
 *     }
 * </code>
 * Example without {@link Validate}:
 * <code>
 *     public void setTitle(String title) {
 *         if (this.title != null) {
 *             throw new IllegalStateException("The title is already defined");
 *         }
 *         if (title == null || title.isEmpty()) {
 *             throw new IllegalArgumentException("title cannot be null");
 *         }
 *         this.title = title;
 *     }
 * </code>
 *
 * Example with {@link Validate} and {@link Filters}:
 * <code>
 *     public void setPages(String[] pages) {
 *         Validate.argument(Filters.all(pages, page -> page.length() < 500));
 *         this.pages = pages;
 *     }
 * </code>
 * Example without {@link Validate} nor {@link Filters}:
 * <code>
 *     public void setPages(String[] pages) {
 *         if (pages == null) {
 *             throw new NullPointerException("pages cannot be null");
 *         }
 *         for (String page : pages) {
 *             if (page.length > 500) {
 *                 throw new IllegalArgumentException("invalid page length");
 *             }
 *         }
 *         this.pages = pages;
 *     }
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
     * @param object The checked object
     * @param message The exception message
     * @param <T> The type of the checked object
     * @throws NullPointerException if object is null
     * @return The object, never null
     */
    public static <T> T notNull(T object, String message) {
        if (object == null) {
            throw new NullPointerException(message);
        } else {
            return object;
        }
    }

    /**
     * Validates that the provided object isn't null,
     * if it is, the method throws an {@link NullPointerException}
     * @param object The checked object
     * @param <T> The type of the checked object
     * @throws NullPointerException if object is null
     * @return The object, never null
     */
    public static <T> T notNull(T object) {
        return notNull(object, null);
    }

    /**
     * Validates that the specified expression is true,
     * if it is false, the method throws a {@link IllegalStateException}
     * with the specified message.
     * @param expression The checked expression
     * @param message The message for the thrown exception
     * @throws IllegalStateException If expression is false
     */
    public static void state(boolean expression, String message) {
        if (!expression) {
            throw new IllegalStateException(message);
        }
    }

    /**
     * Validates that the specified expression is true,
     * if it is false, the method throws a {@link IllegalStateException}
     * @param expression The checked expression
     * @throws IllegalStateException If expression is false
     */
    public static void state(boolean expression) {
        state(expression, null);
    }

    /**
     * Validates that the specified expression is true,
     * if it is false, the method throws a {@link IllegalArgumentException}
     * with the specified message
     * @param expression The checked expression
     * @param message The message for the thrown exception
     * @throws IllegalArgumentException If expression is false
     */
    public static void argument(boolean expression, String message) {
        if (!expression) {
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * Validates that the specified expression is true,
     * if it is false, the method throws a {@link IllegalArgumentException}
     * @param expression The checked expression
     * @throws IllegalArgumentException If expression is false
     */
    public static void argument(boolean expression) {
        argument(expression, null);
    }

    /**
     * Validates that the specified string is
     * not null and not empty. If the string is
     * null, throws a {@link NullPointerException},
     * if the string is empty, throws a
     * {@link IllegalArgumentException}.
     * @param string The checked string
     * @param message The message used for the messages
     * @throws NullPointerException if the string is null
     * @throws IllegalArgumentException If the string is empty
     * @return The string, not null and not empty
     */
    public static String notEmpty(String string, String message) {
        if (string == null) {
            throw new NullPointerException(message);
        } else if (string.isEmpty()) {
            throw new IllegalArgumentException(message);
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
     * @throws NullPointerException if the string is null
     * @throws IllegalArgumentException If the string is empty
     * @return The string, not null and not empty
     */
    public static String notEmpty(String string) {
        return notEmpty(string, null);
    }

}
