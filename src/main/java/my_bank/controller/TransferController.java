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
    @GetMapping("/accounts/{idAccountSource}/transfers")
    public ResponseEntity<List<Transfer>> findByIdAccountSource(
            @PathVariable Integer idAccountSource,
            @RequestParam(required = false) String label,
            @RequestParam(required = false) LocalDateTime transferDatetime,
            @RequestParam(required = false) String reference,
            @RequestParam(required = false) String destinationAccountNumber,
            @RequestParam(required = false) Integer idTransfer
    ) {
        List<KeyAndValue> keyAndValueList = new ArrayList<>();
        keyAndValueList.add(new KeyAndValue("idAccountSource", idAccountSource.toString()));
        keyAndValueList.add(new KeyAndValue("label", label));
        keyAndValueList.add(new KeyAndValue("transferDatetime", transferDatetime.toString()));
        keyAndValueList.add(new KeyAndValue("reference", reference));
        keyAndValueList.add(new KeyAndValue("destinationAccountNumber", destinationAccountNumber));
        keyAndValueList.add(new KeyAndValue("id", idTransfer.toString()));

        return ResponseEntity.ok(transferService.findManyByIdAccountSource(keyAndValueList));
    }
}
