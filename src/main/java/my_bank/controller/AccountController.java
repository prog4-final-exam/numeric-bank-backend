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
        if (toSaveOrUpdate.getId() == null) {
            return ResponseEntity.ok(accountService.save(toSaveOrUpdate));
        } else if (accountService.findById(toSaveOrUpdate.getId()) != null){
            return ResponseEntity.ok(accountService.update(toSaveOrUpdate));
        }
        return null;
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
