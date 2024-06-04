package com.Catering_Server.Service;

import com.Catering_Server.Entity.Category;
import com.Catering_Server.Entity.Items;
import com.Catering_Server.Repository.CategoryRepository;
import com.Catering_Server.Repository.ItemsRepository;
import com.Catering_Server.dto.ItemsDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ItemsService {

	@Autowired
	private ItemsRepository itemsRepository;

	@Autowired
	private CategoryRepository categoryRepository;


	public List<Items> getAllItems() {
		return itemsRepository.findAll();
	}

	public Items getItemById(Long id) throws ChangeSetPersister.NotFoundException {
		return itemsRepository.findById(id).orElseThrow(() -> new ChangeSetPersister.NotFoundException());
	}

//	public Items createItem(Items item) {
//		for (Category ca : item.getCategory()) {
//			Category data = categoryRepository.findById(ca.getCategory_id()).get();
//			if (data != null) {
//				ca.setCategoryName(data.getCategoryName());
//			}
//
//		}
//		return itemsRepository.save(item);
//	}
//	
	
	 public Items createItem(Items item, List<Long> categoryIds) {
	        // Populate categories for the item using category IDs
	        List<Category> categories = categoryRepository.findAllById(categoryIds);
	        item.setCategories(categories);

	        // Assuming other logic remains the same

	        return itemsRepository.save(item);
	    }

	 public Items updateItem(Long id, ItemsDto updatedItem) throws ChangeSetPersister.NotFoundException {
		    // Retrieve the item by its ID
		    Items item = getItemById(id);
		    
		    List<Category> catList = new ArrayList<Category>();
		    		    
		    item.setItems_id(id);
		    item.setItemName(updatedItem.getItemName());
		    item.setItemDescription(updatedItem.getItemDescription());
		    item.setUpdatedOn(new Date());

		    for(Long data :updatedItem.getCategoryIds())
		    {
		    	Category anshul = categoryRepository.findById(data).get();
		    	catList.add(anshul);
		    	
		    }

		    item.setCategories(catList);
		   System.out.println(item);
		    return itemsRepository.save(item);
		}


	public void deleteItem(Long id) throws ChangeSetPersister.NotFoundException {
		Items item = getItemById(id);
		itemsRepository.delete(item);
	}



//	public Items updateMenuItem(Long id, String itemName, String itemDescription, List<Long> categoryIds) throws NotFoundException {
//
//	        Items menuItem = itemsRepository.findById(id)
//	                .orElseThrow(() -> new NotFoundException());
//
//	        menuItem.setItemName(itemName);
//	        menuItem.setItemDescription(itemDescription);
//
//	        List<Category> categories = categoryRepository.findAllById(categoryIds);
//	        menuItem.setCategory(categories);
//
//	        menuItem.setUpdatedOn(new Date());
//
//	        return itemsRepository.save(menuItem);
//	    
//	}

	


}
