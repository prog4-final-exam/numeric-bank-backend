package my_bank.repository;

import my_bank.model.KeyAndValue;

import java.util.List;
import java.util.Optional;

public interface CrudOperation<T> {
    T save(T toSave);
    boolean deleteById(int id);
    T update(T toUpdate);
    List<T> findAll();
    T findFirstOneByKey(List<KeyAndValue> keyAndValueList);
    T findLastOneByKey(List<KeyAndValue> keyAndValueList);
    List<T> findManyByKey(List<KeyAndValue> keyAndValueList);
}
