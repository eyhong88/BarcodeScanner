package com.eyhong.barcode.scanner.model;

import com.eyhong.barcode.scanner.ApplicationEnum;
import com.eyhong.barcode.scanner.config.ScannerConfig;
import com.eyhong.barcode.scanner.entity.Item;
import com.eyhong.barcode.scanner.entity.ItemLabel;
import com.eyhong.barcode.scanner.exception.NoDataFoundException;
import com.eyhong.barcode.scanner.service.InventoryScannerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Collections;

@Component
@Slf4j
public class InventoryScannerAdd implements InventoryScanner {

    @Autowired
    private InventoryScannerService scannerService;
    @Autowired
    private ItemRegistration itemScreen;
    @Autowired
    private ScannerConfig config;

    public void displayUI(){
        createFrame();
    }

    /**
     * This method creates the SWING-based UI making use of a {@link GridBagLayout}.
     */
    public void createFrame() {
        log.debug("Beginning of InventoryScannerAdd.createFrame.");

        final JFrame frame = new JFrame(config.getTitleName());

        JPanel barcodePanel = createBarcodePrompter();

        final JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;

        c.gridx = 0;
        c.gridy = 0;
        panel.add(barcodePanel, c);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(config.getFrameWidth(), config.getFrameHeight());
        frame.getContentPane().add(panel);
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

        //TODO Make this into its own method/class.
        KeyListener keyListener = new KeyListener(){

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
        };

        barcodeTextBox.addKeyListener(keyListener);
        return barcodePanel;
    }

    public ApplicationEnum getType(){
        return ApplicationEnum.ADD;
    }
}
