package com.eyhong.barcode.scanner.model;

import com.eyhong.barcode.scanner.entity.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@Component
public class AddPopupGUI extends JFrame{
    private JLabel YESButton;
    private JLabel NOButton;
    private JPanel panel1;
    private JLabel panelTitle;
    @Autowired
    private ItemRegistration itemReg;

    private boolean onButton(MouseEvent e, JLabel button) {
        return e.getPoint().getX() >= 0 && e.getPoint().getY() >= 0 &&
                e.getPoint().getX() <= button.getWidth() && e.getPoint().getY() <= button.getHeight();
    }

    public void displayUI(Item item){
        //INVENTORY BUTTON
        YESButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                YESButton.setIcon(new ImageIcon(getClass().getResource("/buttons/yesButton.png")));
            }
        });
        YESButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                YESButton.setIcon(new ImageIcon(getClass().getResource("/buttons/yesButtonRed.png")));
            }
        });
        YESButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if(onButton(e, YESButton)){
                    itemReg.itemRegistration(item);
                    dispose();
                }
            }
        });

        // EXIT BUTTON
        NOButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                NOButton.setIcon(new ImageIcon(getClass().getResource("/buttons/noButton.png")));
            }
        });
        NOButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                NOButton.setIcon(new ImageIcon(getClass().getResource("/buttons/noButtonRed.png")));
            }
        });
        NOButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if(onButton(e, NOButton))
                    dispose();
            }
        });
        setTitle("Add Item?");
        setContentPane(panel1);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pack();
        setVisible(true);
    }
}
