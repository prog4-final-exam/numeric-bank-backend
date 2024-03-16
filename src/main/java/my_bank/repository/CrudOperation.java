package my_bank.repository;

public interface CrudOperation<T> {
    T save(T toSave);
    boolean deleteById(int id);
    T update(T toUpdate);
}
