package com.eyhong.barcode.scanner.model;

import com.eyhong.barcode.scanner.ApplicationEnum;
import com.eyhong.barcode.scanner.factory.InventoryScannerFactory;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@Component
public class MainScreenGUI extends JFrame {
    private JLabel mainTitle;
    private JLabel priceScanButton;
    private JLabel inventoryButton;
    private JLabel exitButton;
    private JPanel innerPanel;
    private JPanel panel1;
    private JLabel cartImage;

    public MainScreenGUI() {
        priceScanButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                priceScanButton.setIcon(new ImageIcon(getClass().getResource("/buttons/priceScanHover.png")));
            }
        });
        priceScanButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                priceScanButton.setIcon(new ImageIcon(getClass().getResource("/buttons/PriceScanButton.png")));
            }
        });
        priceScanButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if(onButton(e, priceScanButton))
                    setDisplay(ApplicationEnum.SCAN);
            }
        });

        //INVENTORY BUTTON
        inventoryButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                inventoryButton.setIcon(new ImageIcon(getClass().getResource("/buttons/inventoryHover.png")));
            }
        });
        inventoryButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                inventoryButton.setIcon(new ImageIcon(getClass().getResource("/buttons/InventoryButton.png")));
            }
        });
        inventoryButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if(onButton(e, inventoryButton))
                    setDisplay(ApplicationEnum.ADD);
            }
        });

        // EXIT BUTTON
        exitButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                exitButton.setIcon(new ImageIcon(getClass().getResource("/buttons/exitHover.png")));
            }
        });
        exitButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                exitButton.setIcon(new ImageIcon(getClass().getResource("/buttons/ExitButton.png")));
            }
        });
        exitButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if(onButton(e, exitButton))
                    System.exit(0);
            }
        });
    }

    private boolean onButton(MouseEvent e, JLabel button) {
        return e.getPoint().getX() >= 0 && e.getPoint().getY() >= 0 &&
                e.getPoint().getX() <= button.getWidth() && e.getPoint().getY() <= button.getHeight();
    }

    public void displayUI(){
        setTitle("Price Scanner Application");
        setContentPane(panel1);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }

    private void setDisplay(ApplicationEnum applicationEnum){
        InventoryScannerFactory.getInstance(applicationEnum).displayUI();
        setVisible(false);
    }
}
