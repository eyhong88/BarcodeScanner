package com.eyhong.barcode.scanner.service;

import com.eyhong.barcode.scanner.entity.Item;
import com.eyhong.barcode.scanner.exception.NoDataFoundException;

public interface InventoryScannerService {
    Item scan(String barcode) throws NoDataFoundException;
}
