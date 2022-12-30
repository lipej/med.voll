package med.voll.api.infra.http.controllers.output.commons;

public record PaginatedOutput<T>(int page, int limit, long total, T data) {
}
