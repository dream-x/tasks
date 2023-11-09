package com.badey.itemcrud.service;

import com.badey.itemcrud.entity.Item;
import com.badey.itemcrud.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ItemService {

    private final ItemRepository itemRepository;

    @Autowired
    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public Item createItem(Item item) {
        return itemRepository.save(item);
    }

    public Item getItemById(long id) {
        return itemRepository.findById(id).orElse(null);
    }

    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    public Item updateItem(long id, Item updatedItem) {
        Item item = itemRepository.findById(id).orElse(null);
        if (item != null) {
            item.setName(updatedItem.getName());
            item.setPrice(updatedItem.getPrice());
            return itemRepository.save(item);
        }
        return null;
    }

    public boolean deleteItem(long id) {
        if (itemRepository.existsById(id)) {
            itemRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
