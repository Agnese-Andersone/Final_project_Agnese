CREATE TABLE IF NOT EXISTS `user` (

    `id` bigint NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `username` varchar(MAX),
    `email` varchar(MAX),
    `personal_code` varchar(12),
    `address` varchar(200),
    `favourite_book` varchar(200)

)ENGINE=InnoDB DEFAULT CHARSET=UTF8;