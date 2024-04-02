package my_bank.repository;

import my_bank.model.Enum.FindSourceType;
import my_bank.model.KeyAndValue;

import java.util.List;

public interface CrudOperation<T> {
    T save(T toSave);
    boolean deleteById(int id);
    T update(T toUpdate);
    List<T> findAll();
    T findFirstOneByKey(List<KeyAndValue> keyAndValueList, FindSourceType findSourceType, Object paramsObj);
    T findLastOneByKey(List<KeyAndValue> keyAndValueList, FindSourceType findSourceType, Object paramsObj);
    List<T> findManyByKey(List<KeyAndValue> keyAndValueList, FindSourceType findSourceType, Object paramsObj);
}
