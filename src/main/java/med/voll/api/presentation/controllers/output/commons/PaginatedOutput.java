package med.voll.api.presentation.controllers.output.commons;

public record PaginatedOutput<T>(int page, int limit, long total, T data) {
}
