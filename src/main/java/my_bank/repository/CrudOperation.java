package my_bank.repository;

import java.util.List;
import java.util.Optional;

public interface CrudOperation<T> {
    T save(T toSave);
    boolean deleteById(int id);
    T update(T toUpdate);
    List<T> findAll();
    T findOneByKey(String key, String value);
}
