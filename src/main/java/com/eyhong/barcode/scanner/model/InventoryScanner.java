package com.eyhong.barcode.scanner.model;

import com.eyhong.barcode.scanner.ApplicationEnum;

import javax.swing.*;

public interface InventoryScanner {

    void displayUI();
    ApplicationEnum getType();

    default JLabel createHomeIcon(){
        JLabel homeIcon = new JLabel();
        ImageIcon image = new ImageIcon(getClass().getResource("/images/homeIcon.png"));
        homeIcon.setIcon(image);
        homeIcon.setHorizontalAlignment(JLabel.LEFT);
        homeIcon.setVerticalAlignment(JLabel.TOP);
        homeIcon.setBounds(1, 1, 38, 32);

        return homeIcon;
    }

}
