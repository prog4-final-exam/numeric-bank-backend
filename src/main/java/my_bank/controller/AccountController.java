package my_bank.controller;

import lombok.AllArgsConstructor;
import my_bank.model.Account;
import my_bank.service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@AllArgsConstructor
public class AccountController {
    AccountService accountService;

    @GetMapping("/accounts")
    public ResponseEntity<List<Account>> findAll() {
        return ResponseEntity.ok(accountService.findAll());
    }
    @PostMapping("/accounts")
    public ResponseEntity<Account> save(@RequestBody Account toSave) {
        return ResponseEntity.ok(accountService.save(toSave));
    }

    @PutMapping("/accounts")
    public ResponseEntity<Account> update(@RequestBody Account toUpdate) {
        return ResponseEntity.ok(accountService.update(toUpdate));
    }


    @GetMapping("/accounts/{id}")
    public ResponseEntity<Account> findById(@PathVariable int id) {
        return ResponseEntity.ok(accountService.findById(id));
    }
    @DeleteMapping("/accounts/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable int id) {
        return ResponseEntity.ok(accountService.deleteById(id));
    }
}
