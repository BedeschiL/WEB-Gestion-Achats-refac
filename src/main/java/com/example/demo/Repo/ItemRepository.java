package com.example.demo.Repo;

import com.example.demo.Models.Item;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Set;

public interface ItemRepository extends CrudRepository<Item, Long> {


    Item findItemByidItem(Long id);

}
