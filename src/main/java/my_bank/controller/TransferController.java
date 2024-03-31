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
        if (toSaveOrUpdate.getId() == null) {
            return ResponseEntity.ok(transferService.save(toSaveOrUpdate));
        } else if (transferService.findById(toSaveOrUpdate.getId()) != null) {
            return ResponseEntity.ok(transferService.update(toSaveOrUpdate));
        }
        return null;
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
            keyAndValueList.add(new KeyAndValue("id", idTransfer.toString()));
        }
        return ResponseEntity.ok(transferService.findManyByIdAccountSource(keyAndValueList));
    }
}
