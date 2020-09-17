package team.unnamed.functional;

import team.unnamed.validate.Validate;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Supplier;

public interface Trying<T> {

    Optional<T> getIfSuccess();

    Optional<Throwable> getIfFailure();

    boolean isSuccess();

    boolean isFailure();

    @SuppressWarnings("unchecked")
    default Trying<T> orElse(Trying<? extends T> other) {
        return isSuccess() ? this : (Trying<T>) Validate.notNull(other, "Other can not be null");
    }

    default Trying<T> ifFailure(Consumer<? super Throwable> action) {
        Optional<Throwable> cause = getIfFailure();

        if (isFailure() && cause.isPresent()) {
            Validate.notNull(action, "Action can not be null").accept(cause.get());
        }

        return this;
    }

    default Trying<T> ifSuccess(Consumer<? super T> action) {
        Optional<T> value = getIfSuccess();

        if (isSuccess() && value.isPresent()) {
            Validate.notNull(action, "Action can not be null").accept(value.get());
        }

        return this;
    }

    static <T> Trying<T> of(Supplier<? extends T> supplier) {
        try {
            return success(Validate.notNull(supplier, "Supplier can not be null").get());
        } catch (Throwable throwable) {
            return failure(throwable);
        }
    }

    static <T> Trying<T> success(T value) {
        return new Success<>(Validate.notNull(value, "Value can not be null"));
    }

    static <T> Trying<T> failure(Throwable cause) {
        return new Failure<>(cause);
    }

    final class Success<T> implements Trying<T> {

        private final T value;

        public Success(T value) {
            this.value = value;
        }

        @Override
        public Optional<T> getIfSuccess() {
            return Optional.ofNullable(value);
        }

        @Override
        public Optional<Throwable> getIfFailure() {
            return Optional.empty();
        }

        @Override
        public boolean isSuccess() {
            return true;
        }

        @Override
        public boolean isFailure() {
            return false;
        }

    }

    final class Failure<T> implements Trying<T> {

        private final Throwable cause;

        public Failure(Throwable cause) {
            this.cause = cause;
        }

        @Override
        public Optional<T> getIfSuccess() {
            return Optional.empty();
        }

        @Override
        public Optional<Throwable> getIfFailure() {
            return Optional.ofNullable(cause);
        }

        @Override
        public boolean isSuccess() {
            return false;
        }

        @Override
        public boolean isFailure() {
            return true;
        }

    }

}