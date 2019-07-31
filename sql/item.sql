CREATE TABLE inventory.item
(item_id	int AUTO_INCREMENT,
 name		varchar(100),
 barcode	varchar(100),
 quantity	int,
 price		decimal(12,2),
 PRIMARY KEY (item_id)
);
CREATE UNIQUE INDEX ui_item_barcode ON item(barcode);