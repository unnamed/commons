package team.unnamed.promise;

import java.util.concurrent.Future;
import java.util.function.Consumer;

public interface Promise<T> extends Future<T> {

    <R> Promise<R> then(PromiseAction<R, T> actionThen);

    Promise<T> then(Consumer<T> actionThen);

    static <T> Promise<T> all() {
    }

    static <T> Promise<T> resolve(T value) {
        return null;
    }

}
