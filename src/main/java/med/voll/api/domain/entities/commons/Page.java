package med.voll.api.domain.entities.commons;

public class Page<T> {
    private long total;
    private T data;

    public Page(long total, T data) {
        this.total = total;
        this.data = data;
    }

    public long getTotal() {
        return this.total;
    }

    public T getData() {
        return this.data;
    }
}
