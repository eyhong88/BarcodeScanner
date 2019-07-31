package com.eyhong.barcode.Scanner.service;

import com.eyhong.barcode.Scanner.entity.Item;
import com.eyhong.barcode.Scanner.exception.NoDataFoundException;
import com.eyhong.barcode.Scanner.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InventoryScannerServiceImpl implements InventoryScannerService {

    @Autowired
    private InventoryRepository repo;

    /**
     * Find if barcode exists in our persistence layer.
     *
     * @param barcode {@link String}
     * @return {@link Item}
     * @throws NoDataFoundException
     */
    @Override
    public Item scan(String barcode) throws NoDataFoundException {
        return repo.findByBarcode(barcode).orElseThrow(() -> new NoDataFoundException("Barcode could not be found."));
    }
}
