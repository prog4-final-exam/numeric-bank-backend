package my_bank.controller;

import lombok.AllArgsConstructor;
import my_bank.model.KeyAndValue;
import my_bank.model.TransferWithCategory;
import my_bank.service.TransferWithCategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin
public class TransferWithCategoryController {
    TransferWithCategoryService transferWithCategoryService;

    @GetMapping("/transfers/with-category")
    public ResponseEntity<List<TransferWithCategory>> findAll() {
        return ResponseEntity.ok(transferWithCategoryService.findAll());
    }
    @GetMapping("/transfers/with-category/{idTransfer}")
    public ResponseEntity<TransferWithCategory> findById(@PathVariable Integer idTransfer) {
        return ResponseEntity.ok(transferWithCategoryService.findByIdTransfer(idTransfer));
    }
    @GetMapping("/accounts/{idAccountOwner}/transfers/with-category")
    public ResponseEntity<List<TransferWithCategory>> findByIdAccountOwner(
            @PathVariable Integer idAccountOwner,
            @RequestParam(required = false) String label,
            @RequestParam(required = false) LocalDateTime transferDatetime,
            @RequestParam(required = false) String reference,
            @RequestParam(required = false) String correspondantAccountNumber,
            @RequestParam(required = false) Integer idTransfer,
            @RequestParam(required = false) Integer idCategory,
            @RequestParam(required = false) String categoryName,
            @RequestParam(required = false) String categoryType
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
        if (correspondantAccountNumber != null) {
            keyAndValueList.add(new KeyAndValue("correspondantAccountNumber", correspondantAccountNumber));
        }
        if (idTransfer != null) {
            keyAndValueList.add(new KeyAndValue("idTransfer", idTransfer.toString()));
        }
        if (idCategory != null) {
            keyAndValueList.add(new KeyAndValue("idCategory", idCategory.toString()));
        }
        if (categoryName != null) {
            keyAndValueList.add(new KeyAndValue("categoryName", categoryName));
        }
        if (categoryType != null) {
            keyAndValueList.add(new KeyAndValue("categoryType", categoryType));
        }
        return ResponseEntity.ok(transferWithCategoryService.findManyByKey(keyAndValueList));
    }
}
