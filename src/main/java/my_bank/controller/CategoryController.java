package my_bank.controller;

import lombok.AllArgsConstructor;
import my_bank.model.entity.Category;
import my_bank.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@AllArgsConstructor
public class CategoryController {
    CategoryService categoryService;

    @GetMapping("/categories")
    public ResponseEntity<List<Category>> findAll() {
        return ResponseEntity.ok(categoryService.findAll());
    }
    @PutMapping("/categories")
    public ResponseEntity<Category> saveOrUpdate(@RequestBody Category toSaveOrUpdate) {
        return ResponseEntity.ok(categoryService.saveOrUpdate(toSaveOrUpdate));
    }

    @GetMapping("/categories/{id}")
    public ResponseEntity<Category> findById(@PathVariable int id) {
        return ResponseEntity.ok(categoryService.findById(id));
    }
    @DeleteMapping("/categories/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable int id) {
        return ResponseEntity.ok(categoryService.deleteById(id));
    }
    @GetMapping("/transfers/{idTransfer}/category")
    public ResponseEntity<Category> findByIdTransfer(
            @PathVariable Integer idTransfer
            ) {
        return ResponseEntity.ok(categoryService.findOneByIdTransfer(idTransfer));
    }
    @GetMapping("/transactions/{idTransaction}/category")
    public ResponseEntity<Category> findByIdTransaction(
            @PathVariable Integer idTransaction
    ) {
        return ResponseEntity.ok(categoryService.findOneByIdTransaction(idTransaction));
    }
}
