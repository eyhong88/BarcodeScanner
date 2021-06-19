package com.eyhong.barcode.scanner.model;

import com.eyhong.barcode.scanner.ApplicationEnum;
import com.eyhong.barcode.scanner.config.ScannerConfig;
import com.eyhong.barcode.scanner.entity.Item;
import com.eyhong.barcode.scanner.exception.NoDataFoundException;
import com.eyhong.barcode.scanner.service.InventoryScannerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Collections;

@Component
@Slf4j
public class InventoryScannerAdd implements InventoryScanner {

    private InventoryScannerService scannerService;
    private ItemRegistration itemScreen;
    private ScannerConfig config;
    private MainScreenGUI mainScreenGUI;

    @Autowired
    public InventoryScannerAdd(InventoryScannerService scannerService, ItemRegistration itemScreen,
                                ScannerConfig scannerConfig, MainScreenGUI mainScreenGUI){
        this.scannerService = scannerService;
        this.itemScreen = itemScreen;
        this.config = scannerConfig;
        this.mainScreenGUI = mainScreenGUI;
    }

    /**
     * This method creates the SWING-based UI making use of a {@link GridBagLayout}.
     */
    public void displayUI(){

        log.debug("Beginning of InventoryScannerAdd.createFrame.");

        final JFrame frame = new JFrame(config.getInventoryTitleName());

        JPanel barcodePanel = createBarcodePrompter();

        final JPanel panel = new JPanel();

        barcodePanel.setBackground(Color.WHITE);
        panel.setBackground(Color.WHITE);

        panel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;

        c.gridx = 0;
        c.gridy = 0;
        panel.add(barcodePanel, c);

        JLabel homeIcon = createHomeIcon();
        InventoryMouseListener homeIconClickable = new InventoryMouseListener(frame, homeIcon);
        homeIcon.addMouseListener(homeIconClickable);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(config.getFrameWidth(), config.getFrameHeight());
        frame.add(homeIcon);
        frame.add(panel);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);
    }

    /**
     * Textbox for barcode input is created.
     * Barcode scanners input data ending with a new-line character ("Enter").
     * There is an actionlistener that is waiting for the "Enter".
     *
     */
    private JPanel createBarcodePrompter() {
        final JLabel barcodeLbl = new JLabel(config.getBarcodeLabel());
        final JTextField barcodeTextBox = new JTextField(config.getBarcodeTextBoxSize());
        barcodeTextBox.requestFocusInWindow();
        barcodeLbl.setLabelFor(barcodeTextBox);

        final JPanel barcodePanel = new JPanel();
        barcodePanel.add(barcodeLbl);
        barcodePanel.add(barcodeTextBox);

        barcodeTextBox.setFocusTraversalKeys(
                KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, Collections.EMPTY_SET);

        KeyListener keyListener = new InventoryKeyListener(barcodeTextBox);

        barcodeTextBox.addKeyListener(keyListener);
        return barcodePanel;
    }

    public ApplicationEnum getType(){
        return ApplicationEnum.ADD;
    }

    class InventoryKeyListener implements KeyListener {
        JTextField barcodeTextBox;

        public InventoryKeyListener(JTextField barcodeTextBox){
            this.barcodeTextBox = barcodeTextBox;
        }

        @Override
        public void keyTyped(KeyEvent e) {}

        @Override
        public void keyPressed(KeyEvent ke) {
            if(ke.getKeyCode() == KeyEvent.VK_TAB){
                Item item = Item.builder().barcode(barcodeTextBox.getText()).build();
                try {
                    item = scannerService.scan(barcodeTextBox.getText());

                } catch (NoDataFoundException e) {
                    log.debug("No item found. May need to insert new item. {}", e.getMessage());
                }
                log.info("Processing barcode#= {}", item.getBarcode());

                // If the item exists, we pass it in, otherwise we pass in null.
                itemScreen.itemRegistration(item);
                barcodeTextBox.setText("");
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {}
    }


    /**
     * Home Icon mouselistener
     */
    class InventoryMouseListener implements MouseListener {

        private JLabel homeIcon;
        private JFrame currFrame;

        public InventoryMouseListener(JFrame currFrame, JLabel homeIcon) {
            this.currFrame = currFrame;
            this.homeIcon = homeIcon;
        }

        @Override
        public void mouseClicked(MouseEvent mouseEvent) {}

        @Override
        public void mousePressed(MouseEvent mouseEvent) {}

        @Override
        public void mouseReleased(MouseEvent e) {
            if(onButton(e, homeIcon)) {
                mainScreenGUI.displayUI();
                currFrame.dispose();
            }
        }

        @Override
        public void mouseEntered(MouseEvent mouseEvent) {
            homeIcon.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        }

        @Override
        public void mouseExited(MouseEvent mouseEvent) {
            homeIcon.setBorder(null);
        }

        private boolean onButton(MouseEvent e, JLabel button) {
            return e.getPoint().getX() >= 0 && e.getPoint().getY() >= 0 &&
                    e.getPoint().getX() <= button.getWidth() && e.getPoint().getY() <= button.getHeight();
        }
    }

}
