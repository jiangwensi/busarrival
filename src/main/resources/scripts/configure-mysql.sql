CREATE DATABASE busarrival;
CREATE USER 'app'@'localhost' IDENTIFIED BY 'passw0rd';
CREATE USER 'app'@'%' IDENTIFIED BY 'passw0rd';

CREATE USER 'admin'@'localhost' IDENTIFIED BY 'passw0rd';
CREATE USER 'admin'@'%' IDENTIFIED BY 'passw0rd';

GRANT SELECT,INSERT,DELETE,UPDATE to 'app'@'localhost';
GRANT SELECT,INSERT,DELETE,UPDATE to 'app'@'%';

GRANT ALL PRIVILEGES to 'admin'@'%';
GRANT ALL PRIVILEGES to 'admin'@'localhost';