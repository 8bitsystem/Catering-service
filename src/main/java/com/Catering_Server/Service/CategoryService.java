package com.Catering_Server.Service;

import com.Catering_Server.Entity.Category;
import com.Catering_Server.Entity.Items;
import com.Catering_Server.Repository.CategoryRepository;
import com.Catering_Server.dto.ItemDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;
    
    public Category findById(Long id) {
        return categoryRepository.findById(id).orElse(null);
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Category getCategoryById(Long id) throws ChangeSetPersister.NotFoundException {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new ChangeSetPersister.NotFoundException());
    }

    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }
    
    public List<ItemDto> getItemsByCategoryId(Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                                              .orElseThrow(() -> new IllegalArgumentException("Category not found"));

        return category.getItems().stream()
                                  .map(item -> {
                                      ItemDto itemDto = new ItemDto();
                                      itemDto.setItems_id(item.getItems_id());
                                      itemDto.setItemName(item.getItemName());
                                      itemDto.setItemDescription(item.getItemDescription());
                                      // Set category IDs (assuming each item has a list of category IDs)
                                     
                                      return itemDto;
                                  })
                                  .collect(Collectors.toList());
    }


    public Category updateCategory(Long id, Category updatedCategory) throws ChangeSetPersister.NotFoundException {
        Category category = getCategoryById(id);
        category.setCategoryName(updatedCategory.getCategoryName());
        return categoryRepository.save(category);
    }

    public void deleteCategory(Long id) throws ChangeSetPersister.NotFoundException {
        Category category = getCategoryById(id);
        categoryRepository.delete(category);
    }
    
    public List<Category> getCategoriesByIds(List<Long> categoryIds) {
        return categoryRepository.findAllById(categoryIds);
    }
}
