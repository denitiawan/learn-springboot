DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`
(
    `id`        bigint(20) NOT NULL AUTO_INCREMENT,
    `username`  varchar(20)  DEFAULT NULL,
    `password`  varchar(20)  DEFAULT NULL,
    `full_name` varchar(255) DEFAULT NULL,
    `role_id`   bigint(20),
    PRIMARY KEY (`id`)
);
