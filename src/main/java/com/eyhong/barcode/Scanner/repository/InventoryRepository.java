package com.eyhong.barcode.Scanner.repository;

import com.eyhong.barcode.Scanner.entity.Item;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface InventoryRepository extends CrudRepository<Item, String> {

    Optional<Item> findByBarcode(String barcode);
}
