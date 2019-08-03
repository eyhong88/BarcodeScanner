package com.eyhong.barcode.scanner.model;

import com.eyhong.barcode.scanner.ApplicationEnum;

public interface InventoryScanner {

    void displayUI();
    void createFrame();
    ApplicationEnum getType();
}
