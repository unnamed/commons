package team.unnamed.promise;

@FunctionalInterface
public interface PromiseAction<R, T> {

    R act(T value);

}
