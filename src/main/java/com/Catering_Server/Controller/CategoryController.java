package com.Catering_Server.Controller;

import com.Catering_Server.Entity.Category;
import com.Catering_Server.Entity.Items;
import com.Catering_Server.Service.CategoryService;
import com.Catering_Server.dto.ItemDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/getAll")
    public ResponseEntity<List<Category>> getAllCategories() {
        List<Category> categories = categoryService.getAllCategories();
        return ResponseEntity.ok(categories);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable Long id) throws ChangeSetPersister.NotFoundException {
        Category category = categoryService.getCategoryById(id);
        return ResponseEntity.ok(category);
    }
    
    @GetMapping("/{categoryId}")
    public ResponseEntity<List<ItemDto>> getItemsByCategoryId(@PathVariable Long categoryId) {
        try {
            List<ItemDto> items = categoryService.getItemsByCategoryId(categoryId);
            if (items.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT); // Return 204 if no items found
            }
            return new ResponseEntity<>(items, HttpStatus.OK); // Return items with 200 OK
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); // Return 500 on error
        }
    }



    @PostMapping("/save")
    public ResponseEntity<List<Category>> createCategories(@RequestBody List<Category> categories) {
        List<Category> createdCategories = new ArrayList<>();
        for (Category category : categories) {
            createdCategories.add(categoryService.createCategory(category));
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCategories);
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable Long id, @RequestBody Category updatedCategory) throws ChangeSetPersister.NotFoundException {
        Category category = categoryService.updateCategory(id, updatedCategory);
        return ResponseEntity.ok(category);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) throws ChangeSetPersister.NotFoundException {
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }
}
