INSERT INTO `users` (id, email, password)
VALUES
    (1, 'test01@email.com', 'password');

INSERT INTO `products` (`id`, `title`, `name`, `price`, `status`, `user_id`)
VALUES
    (1, '아이폰 팝니다', 'iPhone11', 200000, 'available', 1);

INSERT INTO `productDetails` (`id`, `product_id`, `color`, `product_condition`, `battery_condition`, `camera_condition`, `accessories`, `purchase_date`, `warranty_duration`, `trade_location`, `delivery_fee`, `video_file_path`, `thumbnail_file_path`)
VALUES
    (1, 1, 'white', 'New', 'Good', 'Good', 'Charger, Earphones', '2023-01-02', '12 months', 'Seoul', 5000, 'https://kr.object.ncloudstorage.com/thunder-market-bucket/video/video_3c03c215-e0d5-48cb-8029-d06337051e8a_5sec.mp4', 'https://kr.object.ncloudstorage.com/thunder-market-bucket/thumbnail/thumbnail_3c03c215-e0d5-48cb-8029-d06337051e8a.jpg');
