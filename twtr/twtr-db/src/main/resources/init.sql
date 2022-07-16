DROP DATABASE IF EXISTS twtrdb;

CREATE DATABASE twtrdb CHARACTER SET utf8mb4;

USE twtrdb;

DROP USER IF EXISTS 'app-user'@'%';
SET GLOBAL validate_password_policy=LOW;
CREATE USER 'app-user'@'%' IDENTIFIED BY 'password';
GRANT ALL PRIVILEGES ON twtrdb.* TO 'app-user'@'%';
