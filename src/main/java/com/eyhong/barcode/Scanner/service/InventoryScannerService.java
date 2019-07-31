package com.eyhong.barcode.Scanner.service;

import com.eyhong.barcode.Scanner.entity.Item;
import com.eyhong.barcode.Scanner.exception.NoDataFoundException;

public interface InventoryScannerService {
    Item scan(String barcode) throws NoDataFoundException;
}
