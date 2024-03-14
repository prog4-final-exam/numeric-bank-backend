package my_bank.ignore;

import org.springframework.http.ResponseEntity;

public interface ControllerInterface<T> {
    ResponseEntity<T> save(T toSave);
    ResponseEntity<Boolean> deleteById(int id);
    ResponseEntity<T> update(T toUpdate);
}
