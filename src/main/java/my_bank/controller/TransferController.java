package my_bank.controller;

import my_bank.model.Transfer;
import my_bank.repository.AutoCrudOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class TransferController {
    AutoCrudOperation<Transfer> transferAutoCrudOperation;

    @PostMapping("/transfers")
    public ResponseEntity<Transfer> save(@RequestBody Transfer toSave) {
        return ResponseEntity.ok(transferAutoCrudOperation.save(toSave));
    }

    @PutMapping("/transfers")
    public ResponseEntity<Transfer> update(@RequestBody Transfer toUpdate) {
        return ResponseEntity.ok(transferAutoCrudOperation.update(toUpdate));
    }

    @DeleteMapping("/transfers/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable int id) {
        return ResponseEntity.ok(transferAutoCrudOperation.deleteById(id));
    }
}
