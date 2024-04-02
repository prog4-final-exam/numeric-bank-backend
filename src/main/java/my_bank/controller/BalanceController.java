package my_bank.controller;

import lombok.AllArgsConstructor;
import my_bank.model.KeyAndValue;
import my_bank.model.entity.Balance;
import my_bank.service.BalanceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@AllArgsConstructor
public class BalanceController {
    BalanceService balanceService;

    @GetMapping("/balances")
    public ResponseEntity<List<Balance>> findAll() {
        return ResponseEntity.ok(balanceService.findAll());
    }
    @PutMapping("/balances")
    public ResponseEntity<Balance> saveOrUpdate(@RequestBody Balance toSaveOrUpdate) {
        return ResponseEntity.ok(balanceService.saveOrUpdate(toSaveOrUpdate));
    }

    @GetMapping("/balances/{idBalance}")
    public ResponseEntity<Balance> findById(@PathVariable int idBalance) {
        return ResponseEntity.ok(balanceService.findById(idBalance));
    }
    @DeleteMapping("/balances/{idBalance}")
    public ResponseEntity<Boolean> delete(@PathVariable int idBalance) {
        return ResponseEntity.ok(balanceService.deleteById(idBalance));
    }
    @GetMapping("/accounts/{idAccount}/balances")
    public ResponseEntity<List<Balance>> findByIdAccount(
            @PathVariable Integer idAccount,
            @RequestParam(required = false) Integer idBalance,
            @RequestParam(required = false) LocalDateTime balanceDatetime
            ) {
        List<KeyAndValue> keyAndValueList = new ArrayList<>();
        keyAndValueList.add(new KeyAndValue("idAccount", idAccount.toString()));
        if (idBalance != null) {
            keyAndValueList.add(new KeyAndValue("idBalance", idBalance.toString()));
        }
        if (balanceDatetime != null) {
            keyAndValueList.add(new KeyAndValue("balanceDatetime", balanceDatetime.toString()));
        }
        return ResponseEntity.ok(balanceService.findManyByIdAccount(keyAndValueList));
    }
}
