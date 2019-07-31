package com.eyhong.barcode.Scanner.model;

import com.eyhong.barcode.Scanner.config.ScannerConfig;
import com.eyhong.barcode.Scanner.entity.Item;
import com.eyhong.barcode.Scanner.exception.NoDataFoundException;
import com.eyhong.barcode.Scanner.service.InventoryScannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;

@Component
public class InventoryScannerUI {

    @Autowired
    private InventoryScannerService scannerService;
    @Autowired
    private ScannerConfig config;

    public void displayUI(){
        createFrame();
    }

    /**
     * This method creates the SWING-based UI making use of a {@link GridBagLayout}.
     */
    private void createFrame() {
        final JFrame frame = new JFrame(config.getTitleName());

        ItemLabel itemLabel = createItemLabel();
        JPanel barcodePanel = createBarcodePrompter(itemLabel);

        final JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;

        c.gridx = 0;
        c.gridy = 0;
        panel.add(barcodePanel, c);

        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0;
        c.gridy = 1;
        panel.add(itemLabel.getBarcode(), c);

        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0;
        c.gridy = 2;
        panel.add(itemLabel.getBrand(), c);

        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0;
        c.gridy = 3;
        panel.add(itemLabel.getPrice(), c);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(config.getFrameWidth(), config.getFrameHeight());
        frame.getContentPane().add(panel);
        frame.setVisible(true);
    }

    /**
     * A helper {@link ItemLabel} is populated for cleanliness.
     *
     * @return {@link ItemLabel}
     */
    private ItemLabel createItemLabel() {
        final JLabel priceText = new JLabel("", SwingConstants.CENTER);
        priceText.setFont(new Font(priceText.getFont().getFontName(), Font.BOLD, config.getPriceFontSize()));
        final JLabel brandText = new JLabel("", SwingConstants.CENTER);
        brandText.setFont(new Font(brandText.getFont().getFontName(), Font.BOLD, config.getBrandFontSize()));
        final JLabel barcodeText = new JLabel("", SwingConstants.CENTER);
        barcodeText.setFont(new Font(barcodeText.getFont().getFontName(), Font.BOLD, config.getBarcodeFontSize()));

        return ItemLabel.builder()
                .price(priceText)
                .barcode(barcodeText)
                .brand(brandText)
                .build();
    }

    /**
     * Textbox for barcode input is created.
     * Barcode scanners input data ending with a new-line character ("Enter").
     * There is an actionlistener that is waiting for the "Enter".
     *
     * @param itemLabel {@link ItemLabel}
     */
    private JPanel createBarcodePrompter(ItemLabel itemLabel) {
        final JLabel barcodeLbl = new JLabel(config.getBarcodeLabel());
        final JTextField barcodeTextBox = new JTextField(config.getBarcodeTextBoxSize());
        barcodeTextBox.requestFocusInWindow();
        barcodeLbl.setLabelFor(barcodeTextBox);

        final JPanel barcodePanel = new JPanel();
        barcodePanel.add(barcodeLbl);
        barcodePanel.add(barcodeTextBox);

        barcodeTextBox.addActionListener(x -> {
            System.out.println("Inside Action Listener : " + x.getActionCommand());
            final JLabel priceText = itemLabel.getPrice();
            final JLabel barcodeText = itemLabel.getBarcode();
            final JLabel brandText = itemLabel.getBrand();

            try {
                Item item = scannerService.scan(barcodeTextBox.getText());
                priceText.setForeground(Color.GREEN);
                priceText.setText("$" + item.getPrice());
//                barcodeText.setForeground(Color.BLACK);
//                barcodeText.setText(item.getBarcode());
                brandText.setForeground(Color.BLACK);
                brandText.setText(item.getName());

                barcodeTextBox.setText("");
            } catch (NoDataFoundException e) {
                System.out.println("Do something. " + e.getMessage());
                priceText.setForeground(Color.RED);
                priceText.setFont(new Font(priceText.getFont().getFontName(), Font.BOLD, 32));
                priceText.setText(e.getMessage());
                brandText.setText("");
                barcodeTextBox.setText("");
            }
        });

        return barcodePanel;
    }
}
