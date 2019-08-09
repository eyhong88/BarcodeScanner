package com.eyhong.barcode.scanner.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
public class ScannerConfig {
    @Value("${scanner.title.name}")
    private String titleName;
    @Value("${scanner.inventory.title.name}")
    private String inventoryTitleName;
    @Value("${scanner.barcode.label}")
    private String barcodeLabel;
    @Value("${scanner.returned.barcode.label}")
    private String returnedBarcodeLabel;
    @Value("${scanner.returned.brand.label}")
    private String returnedBrandLabel;
    @Value("${scanner.barcode.textbox.size:20}")
    private int barcodeTextBoxSize;
    @Value("${scanner.frame.width}")
    private int frameWidth;
    @Value("${scanner.frame.height}")
    private int frameHeight;
    @Value("${scanner.price.font.size}")
    private int priceFontSize;
    @Value("${scanner.barcode.font.size}")
    private int barcodeFontSize;
    @Value("${scanner.brand.font.size}")
    private int brandFontSize;
    @Value("${scanner.comment.font.size}")
    private int commentFontSize;

}

