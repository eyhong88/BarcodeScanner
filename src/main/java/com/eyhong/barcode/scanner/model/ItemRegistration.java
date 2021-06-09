package com.eyhong.barcode.scanner.model;

import com.eyhong.barcode.scanner.entity.Item;
import com.eyhong.barcode.scanner.repository.InventoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@Component
@Slf4j
public class ItemRegistration extends JFrame implements ActionListener {
    @Autowired
    private InventoryRepository repo;

    private JLabel brandLabel, barcodeLabel, priceLabel, quantityLabel, commentLabel;
    private JTextField brandText, barcodeText, priceText, quantityText, commentText;
    private JButton submit, cancel;
    private Item item;

    public void itemRegistration(Item item) {
        log.debug("Beginning of ItemRegistration.itemRegistration for item= {}", item.getBarcode());
        this.item = item;

        //Create and populate the panel.
        JPanel p = new JPanel(new SpringLayout());

        brandLabel = new JLabel("Brand Name: ", JLabel.TRAILING);
        barcodeLabel = new JLabel("Barcode: ", JLabel.TRAILING);
        priceLabel = new JLabel("Price: ", JLabel.TRAILING);
        quantityLabel = new JLabel("Quantity: ", JLabel.TRAILING);
        commentLabel = new JLabel("Comment: ", JLabel.TRAILING);

        brandText = new JTextField();
        brandLabel.setLabelFor(brandText);
        p.add(brandLabel);
        p.add(brandText);

        barcodeText = new JTextField();
        barcodeLabel.setLabelFor(barcodeText);
        p.add(barcodeLabel);
        p.add(barcodeText);

        priceText = new JTextField();
        priceLabel.setLabelFor(priceText);
        p.add(priceLabel);
        p.add(priceText);

        quantityText = new JTextField();
        quantityLabel.setLabelFor(quantityText);
        p.add(quantityLabel);
        p.add(quantityText);

        commentText = new JTextField();
        commentLabel.setLabelFor(commentText);
        p.add(commentLabel);
        p.add(commentText);

        submit = new JButton("Submit");
        cancel = new JButton("Cancel");
        submit.addActionListener(this);
        cancel.addActionListener(this);

        p.add(submit);
        p.add(cancel);

        // Set the data in the text fields if it exists.
        setExistingItemData(brandText, barcodeText, priceText, quantityText, commentText);

        SpringUtilities.makeCompactGrid(p,
                6, 2, //rows, cols
                20, 20,        //initX, initY
                20, 20);       //xPad, yPad

        //Set up the content pane.
        p.setOpaque(true);  //content panes must be opaque
        setContentPane(p);

        //Display the window.
        pack();
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    }

    /**
     *
     * @param brandText
     * @param barcodeText
     * @param priceText
     * @param quantityText
     */
    private void setExistingItemData(final JTextField brandText,
                                     final JTextField barcodeText,
                                     final JTextField priceText,
                                     final JTextField quantityText,
                                     final JTextField commentText) {

        if(null != item.getName() && null != item.getPrice()){
            brandText.setText(item.getName());
            priceText.setText(String.valueOf(item.getPrice()));
            quantityText.setText(String.valueOf(item.getQuantity()));
            commentText.setText(item.getComment());
        }

        //Barcode should always be present.
        barcodeText.setText(item.getBarcode());

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == submit) {
            final int quantity = quantityText.getText().isEmpty() ? 0 :  Integer.valueOf(quantityText.getText());

            if(null == item.getName()){
                item = Item.builder()
                        .name(brandText.getText())
                        .barcode(barcodeText.getText())
                        .price(Double.valueOf(priceText.getText()))
                        .quantity(quantity)
                        .comment(commentText.getText())
                        .build();
            } else {
                // We may be doing unnecessary overrides here..
                item.setName(brandText.getText());
                item.setBarcode(barcodeText.getText());
                item.setPrice(Double.valueOf(priceText.getText()));
                item.setQuantity(quantity);
                item.setComment(commentText.getText());
            }

            repo.save(item);
            dispose();
        }
        else if(e.getSource() == cancel) {
            //Close the JFrame.
            dispose();
        }

    }

}
