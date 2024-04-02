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

    @GetMapping("/categories/{idCategory}")
    public ResponseEntity<Category> findById(@PathVariable int idCategory) {
        return ResponseEntity.ok(categoryService.findById(idCategory));
    }
    @DeleteMapping("/categories/{idCategory}")
    public ResponseEntity<Boolean> delete(@PathVariable int idCategory) {
        return ResponseEntity.ok(categoryService.deleteById(idCategory));
    }
}
