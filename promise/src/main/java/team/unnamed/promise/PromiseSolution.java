package team.unnamed.promise;

public interface PromiseSolution<T> {

    void resolve(T value);

    void reject(Throwable reason);

}
