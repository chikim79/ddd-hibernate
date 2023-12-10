INSERT INTO ADDRESS (id, street, city) values (1, '123 home ave', 'los angeles');
INSERT INTO ADDRESS (id, street, city) values (2, '345 shipping lane', 'los angeles');

INSERT INTO CUSTOMER (id, email, home_address_id) values (1, 'chi@test.com', 1);

INSERT INTO ADMIN (id, email) values (1, 'admin@test.com');

INSERT INTO PRODUCT(id, description, price, admin_id) values (1, 'Best Book Ever', 11.11, 1);

INSERT INTO ORDERS (id, customer_id, total_price, shipping_address_id) values (1, 1, 33.33, 2);

INSERT INTO ORDER_ITEM(id, order_id, quantity, product_id) values (1, 1, 3, 1);

INSERT INTO IMAGE (id, url) values (1, 'http://fake.com/real.jpg');

INSERT INTO PRODUCT_IMAGES (images_id, product_id) values (1, 1);



