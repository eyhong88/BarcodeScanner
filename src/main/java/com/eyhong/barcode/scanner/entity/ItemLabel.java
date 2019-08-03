package com.eyhong.barcode.scanner.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;

@Getter
@Setter
@Builder
public class ItemLabel {
    JLabel price;
    JLabel brand;
    JLabel barcode;
    JLabel returnedBarcodeLbl;
    JLabel returnedBrandLbl;
}
