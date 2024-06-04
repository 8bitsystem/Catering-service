package com.Catering_Server.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Catering_Server.Entity.Items;
import com.Catering_Server.Repository.ItemsRepository;
import com.Catering_Server.Service.ItemsService;
import com.Catering_Server.dto.ItemsDto;

@RestController
@RequestMapping("/items")
public class ItemsController {

	@Autowired
	private ItemsService itemsService;

	@Autowired
	private ItemsRepository itemrepo;

	@GetMapping("/getAll")
	public ResponseEntity<List<Items>> getAllItems() {
		List<Items> items = itemsService.getAllItems();
		return ResponseEntity.ok(items);
	}

	@GetMapping("/getById/{id}")
	public ResponseEntity<Items> getItemById(@PathVariable Long id) throws ChangeSetPersister.NotFoundException {
		Items item = itemsService.getItemById(id);
		return ResponseEntity.ok(item);
	}
	
	
//    @PostMapping("/save")
//    public ResponseEntity<List<Items>> createItems(@RequestBody List<Items> itemsList) {
//        List<Items> createdItems = new ArrayList<>();
//
//        for (Items items : itemsList) {
//            if (items.getCreatedOn() == null) {
//                items.setCreatedOn(new Date());
//            }
//            items.setUpdatedOn(new Date());
//            Items createdItem = itemsService.createItem(items); // Assuming itemsService is properly configured
//            createdItems.add(createdItem);
//        }
//
//        return ResponseEntity.status(HttpStatus.CREATED).body(createdItems);
//    }

	@PostMapping("/save")
	public ResponseEntity<Items> createItem(@RequestParam("itemName") String itemName,
			@RequestParam("itemDescription") String itemDescription, @RequestParam("category") List<Long> categoryIds) {
		try {
			// Construct the Items object
			Items item = new Items();
			item.setItemName(itemName);
			item.setItemDescription(itemDescription);
			// Set other properties if needed

			// Assuming itemsService is properly configured
			Items createdItem = itemsService.createItem(item, categoryIds);

			return ResponseEntity.status(HttpStatus.CREATED).body(createdItem);
		} catch (Exception e) {
			// Handle exception
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<Items> updateItem(@PathVariable Long id, @RequestBody ItemsDto updatedItem)
			throws ChangeSetPersister.NotFoundException {
		System.out.println(updatedItem);
		Items res = itemsService.updateItem(id, updatedItem);
		return ResponseEntity.ok(res);
	}

	
	

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Void> deleteItem(@PathVariable Long id) throws ChangeSetPersister.NotFoundException {
		itemsService.deleteItem(id);
		return ResponseEntity.noContent().build();
	}
}
