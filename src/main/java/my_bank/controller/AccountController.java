package my_bank.controller;

import lombok.AllArgsConstructor;
import my_bank.model.entity.Account;
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
    @PutMapping("/accounts")
    public ResponseEntity<Account> saveOrUpdate(@RequestBody Account toSaveOrUpdate) {
       return ResponseEntity.ok(accountService.saveOrUpdate(toSaveOrUpdate));
    }

    @GetMapping("/accounts/{idAccount}")
    public ResponseEntity<Account> findById(@PathVariable int idAccount) {
        return ResponseEntity.ok(accountService.findById(idAccount));
    }
    @DeleteMapping("/accounts/{idAccount}")
    public ResponseEntity<Boolean> delete(@PathVariable int idAccount) {
        return ResponseEntity.ok(accountService.deleteById(idAccount));
    }
}
