package my_bank.controller;

import lombok.AllArgsConstructor;
import my_bank.model.entity.AccountStatement;
import my_bank.service.AccountStatementService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin
public class AccountStatementController {
    AccountStatementService accountStatementService;

    @GetMapping("/accounts/{accountId}/statements")
    public ResponseEntity<List<AccountStatement>> getAccountStatement(@PathVariable int accountId) {
        return ResponseEntity.ok(accountStatementService.getAccountStatement(accountId));
    }
}
