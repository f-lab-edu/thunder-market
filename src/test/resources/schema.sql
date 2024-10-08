CREATE TABLE users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL
);

CREATE TABLE products (
   id BIGINT AUTO_INCREMENT PRIMARY KEY,
   title VARCHAR(255) NOT NULL,
   name VARCHAR(255) NOT NULL,
   price int NOT NULL,
   status VARCHAR(255) NOT NULL,
   user_id BIGINT,
   FOREIGN KEY (user_id) REFERENCES users(id)
);

create table product_details
(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    product_id BIGINT,
    color            varchar(255) null,
    product_condition      varchar(255) null,
    battery_condition varchar(255) null,
    camera_condition  varchar(255) null,
    accessories      varchar(255) null,
    purchase_date     varchar(255) null,
    warranty_duration varchar(255) null,
    trade_location    varchar(255) null,
    delivery_fee      int          null,
    video_file_path    varchar(255) null,
    thumbnail_file_path    varchar(255) null,
    FOREIGN KEY (product_id) REFERENCES products(id)
);

create table keywords
(
    id         BIGINT AUTO_INCREMENT PRIMARY KEY,
    keyword VARCHAR(255) NOT NULL,
    user_id BIGINT,
    FOREIGN KEY (user_id) REFERENCES users(id)
);

create table comments
(
    id         BIGINT AUTO_INCREMENT PRIMARY KEY,
    text VARCHAR(255) NOT NULL,
    user_id BIGINT,
    product_id BIGINT,
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (product_id) REFERENCES products(id)
)