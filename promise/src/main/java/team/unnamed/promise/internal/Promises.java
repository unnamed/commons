package team.unnamed.promise.internal;

import team.unnamed.promise.Promise;

/**
 * Functionality of static methods of
 * {@link Promise}
 */
public final class Promises {

    private Promises() {
        throw new UnsupportedOperationException();
    }

    public static <R> Promise<R> all(Iterable<Promise<?>> promises) {
        
    }

}
