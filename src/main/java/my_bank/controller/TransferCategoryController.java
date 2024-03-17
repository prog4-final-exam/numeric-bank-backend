package my_bank.controller;

import my_bank.model.TransferCategory;
import my_bank.repository.AutoCrudOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class TransferCategoryController {
    AutoCrudOperation<TransferCategory> transferCategoryAutoCrudOperation;

    @PostMapping("/transfers/categories")
    public ResponseEntity<TransferCategory> save(@RequestBody TransferCategory toSave) {
        return ResponseEntity.ok(transferCategoryAutoCrudOperation.save(toSave));
    }

    @PutMapping("/transfers/categories")
    public ResponseEntity<TransferCategory> update(@RequestBody TransferCategory toUpdate) {
        return ResponseEntity.ok(transferCategoryAutoCrudOperation.update(toUpdate));
    }

    @DeleteMapping("/transfers/categories/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable int id) {
        return ResponseEntity.ok(transferCategoryAutoCrudOperation.deleteById(id));
    }
}
