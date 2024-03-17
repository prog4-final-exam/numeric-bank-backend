package my_bank.controller;

import my_bank.model.Account;
import my_bank.repository.AutoCrudOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class AccountController {
    AutoCrudOperation<Account> accountAutoCrudOperation;
    @PostMapping("/accounts")
    public ResponseEntity<Account> save(@RequestBody Account toSave) {
        return ResponseEntity.ok(accountAutoCrudOperation.save(toSave));
    }

    @PutMapping("/accounts")
    public ResponseEntity<Account> update(@RequestBody Account toUpdate) {
        return ResponseEntity.ok(accountAutoCrudOperation.update(toUpdate));
    }

    @DeleteMapping("/accounts/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable int id) {
        return ResponseEntity.ok(accountAutoCrudOperation.deleteById(id));
    }
}
