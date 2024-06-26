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

    @GetMapping("/transfers/categories/have")
    public ResponseEntity<List<TransferHave>> findAll() {
        return ResponseEntity.ok(transferHaveService.findAll());
    }
    @GetMapping("/transfers/categories/have/{idTransferHave}")
    public ResponseEntity<TransferHave> findById(@PathVariable int idTransferHave) {
        return ResponseEntity.ok(transferHaveService.findById(idTransferHave));
    }

    @PutMapping("/transfers/categories/have")
    public ResponseEntity<TransferHave> saveOrUpdate(@RequestBody TransferHave toSaveOrUpdate) {
        return ResponseEntity.ok(transferHaveService.saveOrUpdate(toSaveOrUpdate));
    }
    @DeleteMapping("/transfers/categories/have/{idTransferHave}")
    public ResponseEntity<Boolean> deleteById(@PathVariable int idTransferHave) {
        return ResponseEntity.ok(transferHaveService.deleteById(idTransferHave));
    }
    @GetMapping("/transfers/{idTransfer}/category/have")
    public ResponseEntity<TransferHave> findByIdTransfer(@PathVariable int idTransfer) {
        return ResponseEntity.ok(transferHaveService.findByIdTransfer(idTransfer));
    }
}
