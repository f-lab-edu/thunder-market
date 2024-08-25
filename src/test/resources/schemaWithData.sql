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

create table productDetails
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
);

INSERT INTO `users` (id, email, password)
VALUES
    (1, 'jaen6563@naver.com', 'password');

INSERT INTO `products` (`id`, `title`, `name`, `price`, `status`, `user_id`)
VALUES
    (1, '아이폰 팝니다', 'iPhone11', 200000, 'AVAILABLE', 1);

INSERT INTO `productDetails` (`id`, `product_id`, `color`, `product_condition`, `battery_condition`, `camera_condition`, `accessories`, `purchase_date`, `warranty_duration`, `trade_location`, `delivery_fee`, `video_file_path`, `thumbnail_file_path`)
VALUES
    (1, 1, 'white', 'New', 'Good', 'Good', 'Charger, Earphones', '2023-01-02', '12 months', 'Seoul', 5000, 'https://kr.object.ncloudstorage.com/thunder-market-bucket/video/video_3c03c215-e0d5-48cb-8029-d06337051e8a_5sec.mp4', 'https://kr.object.ncloudstorage.com/thunder-market-bucket/thumbnail/thumbnail_3c03c215-e0d5-48cb-8029-d06337051e8a.jpg');

INSERT INTO `keywords` (`id`, `keyword`, `user_id`)
VALUES
    (1, '아이폰', 1);
