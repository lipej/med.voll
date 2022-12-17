package med.voll.api.domain.usecases;

public interface BaseUseCase<T, K> {
    K execute(T input);
}
