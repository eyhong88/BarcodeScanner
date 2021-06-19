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
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Collections;

@Component
@Slf4j
public class InventoryScannerPrice implements InventoryScanner {

    private InventoryScannerService scannerService;
    private ScannerConfig config;
    private AddPopupGUI popup;
    private MainScreenGUI mainScreenGUI;

    @Autowired
    public InventoryScannerPrice(InventoryScannerService scannerService, ScannerConfig config, AddPopupGUI popup,
                                 MainScreenGUI mainScreenGUI){
        this.scannerService = scannerService;
        this.config = config;
        this.popup = popup;
        this.mainScreenGUI = mainScreenGUI;
    }


    /**
     * This method creates the SWING-based UI making use of a {@link GridBagLayout}.
     */
    public void displayUI(){


        log.debug("Beginning of InventoryScannerPrice.createFrame.");

        final JFrame frame = new JFrame(config.getTitleName());

        ItemLabel itemLabel = createItemLabel();
        JPanel barcodePanel = createBarcodePrompter(itemLabel);

        final JPanel panel = new JPanel();

        barcodePanel.setBackground(Color.WHITE);
        panel.setBackground(Color.WHITE);

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

        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0;
        c.gridy = 4;
        panel.add(itemLabel.getComment(), c);

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
        final JLabel commentText = new JLabel("", SwingConstants.CENTER);
        commentText.setFont(new Font(commentText.getFont().getFontName(), Font.BOLD, config.getCommentFontSize()));

        return ItemLabel.builder()
                .price(priceText)
                .barcode(barcodeText)
                .brand(brandText)
                .comment(commentText)
                .build();
    }

    /**
     * Textbox for barcode input is created.
     * Barcode scanners input data ending with a TAB character ("TAB").
     * There is an actionlistener that is waiting for the "TAB".
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

        barcodeTextBox.setFocusTraversalKeys(
                KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, Collections.EMPTY_SET);

        KeyListener keyListener = new InventoryKeyListener(itemLabel, barcodeTextBox);
        barcodeTextBox.addKeyListener(keyListener);

        return barcodePanel;
    }

    private void promptToAdd(Item item) {
        popup.displayUI(item);
    }

    /**
     * Set the details of the item that will be displayed on screen.
     * @param priceText
     * @param brandText
     * @param commentText
     * @param item
     */
    private void setItemDetails(JLabel priceText, JLabel brandText, JLabel commentText, Item item) {
        priceText.setForeground(Color.BLACK);
        priceText.setText("$" + String.format("%.2f", item.getPrice()));
        brandText.setForeground(Color.BLACK);
        brandText.setText(item.getName());
        commentText.setForeground(Color.BLACK);
        commentText.setText(item.getComment());
    }

    public ApplicationEnum getType(){
        return ApplicationEnum.SCAN;
    }

    /**
     * KeyListener for the Price Scanner.
     */
    class InventoryKeyListener implements KeyListener {
        ItemLabel itemLabel;
        JTextField barcodeTextBox;

        public InventoryKeyListener(ItemLabel itemLabel, JTextField barcodeTextBox){
            this.itemLabel = itemLabel;
            this.barcodeTextBox = barcodeTextBox;
        }
            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyPressed(KeyEvent ke) {
                if(ke.getKeyCode() == KeyEvent.VK_TAB){
                    log.debug("Inside KeyEvent TAB.");

                    final JLabel priceText = itemLabel.getPrice();
                    final JLabel brandText = itemLabel.getBrand();
                    final JLabel commentText = itemLabel.getComment();

                    try {
                        Item item = scannerService.scan(barcodeTextBox.getText());
                        setItemDetails(priceText, brandText, commentText, item);

                        barcodeTextBox.setText("");
                    } catch (NoDataFoundException e) {
                        log.error(e.getMessage());
                        promptToAdd(Item.builder().barcode(barcodeTextBox.getText()).build());
                        priceText.setForeground(Color.RED);
                        priceText.setFont(new Font(priceText.getFont().getFontName(), Font.BOLD, 32));
                        priceText.setText(e.getMessage());
                        brandText.setText("");
                        commentText.setText("");

                        barcodeTextBox.setText("");

                    }
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
