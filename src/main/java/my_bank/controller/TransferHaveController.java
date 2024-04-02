package my_bank.controller;

import lombok.AllArgsConstructor;
import my_bank.model.entity.TransferHave;
import my_bank.service.TransferHaveService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@AllArgsConstructor
public class TransferHaveController {
    TransferHaveService transferHaveService;

    @GetMapping("/transfers/categories")
    public ResponseEntity<List<TransferHave>> findAll() {
        return ResponseEntity.ok(transferHaveService.findAll());
    }
    @GetMapping("/transfers/categories/{id}")
    public ResponseEntity<TransferHave> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(transferHaveService.findById(id));
    }

    @PutMapping("/transfers/categories")
    public ResponseEntity<TransferHave> saveOrUpdate(@RequestBody TransferHave toSaveOrUpdate) {
        return ResponseEntity.ok(transferHaveService.saveOrUpdate(toSaveOrUpdate));
    }
    @DeleteMapping("/transfers/categories/{id}")
    public ResponseEntity<Boolean> deleteById(@PathVariable Integer id) {
        return ResponseEntity.ok(transferHaveService.deleteById(id));
    }
}
