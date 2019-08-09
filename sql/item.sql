CREATE TABLE inventory.item
(item_id	int AUTO_INCREMENT,
 name		varchar(100),
 barcode	varchar(100),
 quantity	int,
 price		decimal(12,2),
 comment	varchar(100),
 PRIMARY KEY (item_id)
);

CREATE UNIQUE INDEX ui_item_barcode ON item(barcode);

ALTER TABLE item ADD COLUMN comment varchar(100);