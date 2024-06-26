package my_bank.controller;

import lombok.AllArgsConstructor;
import my_bank.model.KeyAndValue;
import my_bank.model.entity.Transfer;
import my_bank.service.TransferService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
    @PutMapping("/transfers")
    public ResponseEntity<Transfer> saveOrUpdate(@RequestBody Transfer toSaveOrUpdate) {
        return ResponseEntity.ok(transferService.saveOrUpdate(toSaveOrUpdate));
    }

    @GetMapping("/transfers/{idTransfer}")
    public ResponseEntity<Transfer>  findById(@PathVariable int idTransfer) {
        return ResponseEntity.ok(transferService.findById(idTransfer));
    }
    @DeleteMapping("/transfers/{idTransfer}")
    public ResponseEntity<Boolean> delete(@PathVariable int idTransfer) {
        return ResponseEntity.ok(transferService.deleteById(idTransfer));
    }
    @GetMapping("/accounts/{idAccountOwner}/transfers")
    public ResponseEntity<List<Transfer>> findByIdAccountOwner(
            @PathVariable Integer idAccountOwner,
            @RequestParam(required = false) String label,
            @RequestParam(required = false) LocalDateTime transferDatetime,
            @RequestParam(required = false) String reference,
            @RequestParam(required = false) String destinationAccountNumber,
            @RequestParam(required = false) Integer idTransfer
    ) {
        List<KeyAndValue> keyAndValueList = new ArrayList<>();
        keyAndValueList.add(new KeyAndValue("idAccountOwner", idAccountOwner.toString()));
        if (label != null) {
            keyAndValueList.add(new KeyAndValue("label", label));
        }
        if (transferDatetime != null) {
            keyAndValueList.add(new KeyAndValue("transferDatetime", transferDatetime.toString()));
        }
        if (reference != null) {
            keyAndValueList.add(new KeyAndValue("reference", reference));
        }
        if (destinationAccountNumber != null) {
            keyAndValueList.add(new KeyAndValue("destinationAccountNumber", destinationAccountNumber));
        }
        if (idTransfer != null) {
            keyAndValueList.add(new KeyAndValue("idTransfer", idTransfer.toString()));
        }
        return ResponseEntity.ok(transferService.findManyByIdAccountOwner(keyAndValueList));
    }
}
