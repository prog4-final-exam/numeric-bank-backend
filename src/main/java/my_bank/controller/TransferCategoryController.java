package my_bank.controller;

import lombok.AllArgsConstructor;
import my_bank.model.Enum.CategoryType;
import my_bank.model.entity.TransferCategory;
import my_bank.service.TransferCategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@AllArgsConstructor
public class TransferCategoryController {
    TransferCategoryService transferCategoryService;

    @GetMapping("/transfers/categories")
    public ResponseEntity<List<TransferCategory>> findAll() {
        return ResponseEntity.ok(transferCategoryService.findAll());
    }
    @PostMapping("/transfers/categories")
    public ResponseEntity<TransferCategory> save(@RequestBody TransferCategory toSave) {
        return ResponseEntity.ok(transferCategoryService.save(toSave));
    }

    @PutMapping("/transfers/categories")
    public ResponseEntity<TransferCategory> update(@RequestBody TransferCategory toUpdate) {
        return ResponseEntity.ok(transferCategoryService.update(toUpdate));
    }
    @GetMapping("/transfers/categories/{id}")
    public ResponseEntity<TransferCategory> findById(@PathVariable int id) {
        return ResponseEntity.ok(transferCategoryService.findById(id));
    }
    @DeleteMapping("/transfers/categories/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable int id) {
        return ResponseEntity.ok(transferCategoryService.deleteById(id));
    }
    @GetMapping("/transfers/{idTransfer}/category")
    public ResponseEntity<TransferCategory> findByIdTransfer(
            @PathVariable Integer idTransfer
            ) {
        return ResponseEntity.ok(transferCategoryService.findOneByIdTransfer(idTransfer));
    }
}
