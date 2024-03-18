package my_bank.controller;

import lombok.AllArgsConstructor;
import my_bank.model.Transfer;
import my_bank.repository.AutoCrudOperation;
import my_bank.service.TransferService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@AllArgsConstructor
public class TransferController {
    TransferService transferService;

    @GetMapping("/transfers")
    public ResponseEntity<List<Transfer>> findAll() {
        return ResponseEntity.ok(transferService.findAll());
    }
    @PostMapping("/transfers")
    public ResponseEntity<Transfer> save(@RequestBody Transfer toSave) {
        return ResponseEntity.ok(transferService.save(toSave));
    }

    @PutMapping("/transfers")
    public ResponseEntity<Transfer> update(@RequestBody Transfer toUpdate) {
        return ResponseEntity.ok(transferService.update(toUpdate));
    }
    @GetMapping("/transfers/{id}")
    public ResponseEntity<Transfer>  findById(@PathVariable int id) {
        return ResponseEntity.ok(transferService.findById(id));
    }
    @DeleteMapping("/transfers/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable int id) {
        return ResponseEntity.ok(transferService.deleteById(id));
    }
}
