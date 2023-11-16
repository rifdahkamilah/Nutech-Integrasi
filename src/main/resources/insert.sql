CREATE DATABASE sims_ppob;
USE sims_ppob;

DROP TABLE IF EXISTS t_user;
CREATE TABLE t_user (
   ID             INT(11) NOT NULL AUTO_INCREMENT,
   EMAIL          VARCHAR(100) NOT NULL,
   FIRST_NAME      VARCHAR(50),
   LAST_NAME       VARCHAR(50),
   PASSWORD      VARCHAR(100) NOT NULL,
   CONSTRAINT PK_USER PRIMARY KEY (ID)
);

INSERT INTO t_user (ID, EMAIL, FIRST_NAME, LAST_NAME, PASSWORD) VALUES ('1', 'user@nutech-integrasi.com', 'User', 'Nutech', '$2a$10$Mwl3ytOO5CzkyMFPrTTAQ.MwfPsiEOpadZjHjcGL6fS4W5gR8yYpK');

DROP TABLE IF EXISTS role;
CREATE TABLE role (
    ID            VARCHAR(100) NOT NULL,
	NAME          VARCHAR(100) NOT NULL,
	CONSTRAINT PK_ROLE PRIMARY KEY (ID)
);

INSERT INTO role (ID,NAME) VALUES ('ROLE_ADMIN','Role Administrator');
INSERT INTO role (ID,NAME) VALUES ('ROLE_USER','Role User');

DROP TABLE IF EXISTS userrole;
CREATE TABLE userrole (
    EMAIL         VARCHAR(100) NOT NULL,
	ROLEID        VARCHAR(100) NOT NULL,
	CONSTRAINT PK_USERROLE PRIMARY KEY (EMAIL,ROLEID)
);

INSERT INTO userrole VALUES ('user@nutech-integrasi.com','ROLE_ADMIN');

DROP TABLE IF EXISTS t_userprofile;
CREATE TABLE t_userprofile (
    PROFILEID        		  INT(11) NOT NULL AUTO_INCREMENT,
    PROFILE_EMAIL			  VARCHAR(100) NOT NULL,
    PROFILE_FIRST_NAME        VARCHAR(100) NOT NULL,
    PROFILE_LAST_NAME         VARCHAR(100) NOT NULL,
    PROFILE_IMAGE		      VARCHAR(100) NOT NULL,
    CONSTRAINT PK_PROFILE PRIMARY KEY (PROFILEID)
);

INSERT INTO t_userprofile (PROFILEID, PROFILE_EMAIL, PROFILE_FIRST_NAME, PROFILE_LAST_NAME, PROFILE_IMAGE) VALUES ('1', 'user@nutech-integrasi.com', 'User', 'Nutech', 'https://yoururlapi.com/profile-updated.jpeg');

DROP TABLE IF EXISTS t_banner;
CREATE TABLE t_banner (
	ID        			INT(11) NOT NULL AUTO_INCREMENT,
    BANNER_NAME         VARCHAR(100) NOT NULL,
    BANNER_IMAGE		VARCHAR(100) NOT NULL,
    DESCRIPTION		    VARCHAR(500) NOT NULL,
	CONSTRAINT PK_BANNER PRIMARY KEY (ID)
);

INSERT INTO t_banner (ID, BANNER_NAME, BANNER_IMAGE, DESCRIPTION) VALUES ('1', 'Banner 1', 'https://nutech-integrasi.app/dummy.jpg', 'Lerem Ipsum Dolor sit amet');
INSERT INTO t_banner (ID, BANNER_NAME, BANNER_IMAGE, DESCRIPTION) VALUES ('2', 'Banner 2', 'https://nutech-integrasi.app/dummy.jpg', 'Lerem Ipsum Dolor sit amet');
INSERT INTO t_banner (ID, BANNER_NAME, BANNER_IMAGE, DESCRIPTION) VALUES ('3', 'Banner 3', 'https://nutech-integrasi.app/dummy.jpg', 'Lerem Ipsum Dolor sit amet');
INSERT INTO t_banner (ID, BANNER_NAME, BANNER_IMAGE, DESCRIPTION) VALUES ('4', 'Banner 4', 'https://nutech-integrasi.app/dummy.jpg', 'Lerem Ipsum Dolor sit amet');
INSERT INTO t_banner (ID, BANNER_NAME, BANNER_IMAGE, DESCRIPTION) VALUES ('5', 'Banner 5', 'https://nutech-integrasi.app/dummy.jpg', 'Lerem Ipsum Dolor sit amet');
INSERT INTO t_banner (ID, BANNER_NAME, BANNER_IMAGE, DESCRIPTION) VALUES ('6', 'Banner 6', 'https://nutech-integrasi.app/dummy.jpg', 'Lerem Ipsum Dolor sit amet');

DROP TABLE IF EXISTS t_services;
CREATE TABLE t_services (
    ID		        	INT(11) NOT NULL AUTO_INCREMENT,
    SERVICE_CODE        VARCHAR(100) NOT NULL,
    SERVICE_NAME        VARCHAR(100) NOT NULL,
    SERVICE_ICON		VARCHAR(100) NOT NULL,
    SERVICE_TARIF	    INT(100) NOT NULL,
	CONSTRAINT PK_SERVICES PRIMARY KEY (ID)
);

INSERT INTO t_services (ID, SERVICE_CODE, SERVICE_NAME, SERVICE_ICON, SERVICE_TARIF) VALUES ('1', 'PAJAK', 'Pajak PBB', 'https://nutech-integrasi.app/dummy.jpg', '40000');
INSERT INTO t_services (ID, SERVICE_CODE, SERVICE_NAME, SERVICE_ICON, SERVICE_TARIF) VALUES ('2', 'PLN', 'Listrik', 'https://nutech-integrasi.app/dummy.jpg', '10000');
INSERT INTO t_services (ID, SERVICE_CODE, SERVICE_NAME, SERVICE_ICON, SERVICE_TARIF) VALUES ('3', 'PDAM', 'PDAM Berlangganan', 'https://nutech-integrasi.app/dummy.jpg', '40000');
INSERT INTO t_services (ID, SERVICE_CODE, SERVICE_NAME, SERVICE_ICON, SERVICE_TARIF) VALUES ('4', 'PULSA', 'Pulsa', 'https://nutech-integrasi.app/dummy.jpg', '40000');
INSERT INTO t_services (ID, SERVICE_CODE, SERVICE_NAME, SERVICE_ICON, SERVICE_TARIF) VALUES ('5', 'PGN', 'PGN Berlangganan', 'https://nutech-integrasi.app/dummy.jpg', '50000');
INSERT INTO t_services (ID, SERVICE_CODE, SERVICE_NAME, SERVICE_ICON, SERVICE_TARIF) VALUES ('6', 'MUSIK', 'Musik Berlangganan', 'https://nutech-integrasi.app/dummy.jpg', '50000');
INSERT INTO t_services (ID, SERVICE_CODE, SERVICE_NAME, SERVICE_ICON, SERVICE_TARIF) VALUES ('7', 'TV', 'TV Berlangganan', 'https://nutech-integrasi.app/dummy.jpg', '50000');
INSERT INTO t_services (ID, SERVICE_CODE, SERVICE_NAME, SERVICE_ICON, SERVICE_TARIF) VALUES ('8', 'PAKET_DATA', 'Paket Data', 'https://nutech-integrasi.app/dummy.jpg', '50000');
INSERT INTO t_services (ID, SERVICE_CODE, SERVICE_NAME, SERVICE_ICON, SERVICE_TARIF) VALUES ('9', 'VOUCHER_GAME', 'Voucher Game', 'https://nutech-integrasi.app/dummy.jpg', '100000');
INSERT INTO t_services (ID, SERVICE_CODE, SERVICE_NAME, SERVICE_ICON, SERVICE_TARIF) VALUES ('10', 'VOUCHER_MAKANAN', 'Voucher Makanan', 'https://nutech-integrasi.app/dummy.jpg', '100000');
INSERT INTO t_services (ID, SERVICE_CODE, SERVICE_NAME, SERVICE_ICON, SERVICE_TARIF) VALUES ('11', 'QURBAN', 'Qurban', 'https://nutech-integrasi.app/dummy.jpg', '200000');
INSERT INTO t_services (ID, SERVICE_CODE, SERVICE_NAME, SERVICE_ICON, SERVICE_TARIF) VALUES ('12', 'ZAKAT', 'Zakat', 'https://nutech-integrasi.app/dummy.jpg', '300000');

DROP TABLE IF EXISTS t_balance;
CREATE TABLE t_balance (
    ID        			INT(11) NOT NULL AUTO_INCREMENT,
    USER_EMAIL         	VARCHAR(100) NOT NULL,
    BALANCE      		INT(100) NOT NULL,
    TRANSACTION_TYPE	VARCHAR(50) NOT NULL,
	CONSTRAINT PK_BALANCE PRIMARY KEY (ID)
);

DROP TABLE IF EXISTS t_topup;
CREATE TABLE t_topup (
    ID        	  		INT(11) NOT NULL AUTO_INCREMENT,
    TOP_UP_AMOUNT      	INT(100) NOT NULL,
	CONSTRAINT PK_TOPUP PRIMARY KEY (ID)
);

DROP TABLE IF EXISTS t_transaction;
CREATE TABLE t_transaction (
	ID        			INT(11) NOT NULL AUTO_INCREMENT,
    USER_EMAIL         	VARCHAR(100) NOT NULL,
    INVOICE_NUMBER      VARCHAR(100) NOT NULL,
    SERVICE_CODE		VARCHAR(100) NOT NULL,
    SERVICE_NAME       	VARCHAR(100) NOT NULL,
    TRANSACTION_TYPE	VARCHAR(100) NOT NULL,
    TOTAL_AMOUNT 		INT(100) NOT NULL,
    CREATED_ON			DATE NOT NULL,
	CONSTRAINT PK_TRANSACTION PRIMARY KEY (ID)
);

INSERT INTO t_transaction (ID, USER_EMAIL, INVOICE_NUMBER, SERVICE_CODE, SERVICE_NAME, TRANSACTION_TYPE, TOTAL_AMOUNT, CREATED_ON) VALUES ('1', 'user@nutech-integrasi.com', 'INV17082023-001', 'PLN_PRABAYAR', 'PLN Prabayar', 'PAYMENT', '10000', '2023-08-17');

DROP TABLE IF EXISTS t_transactionhistory;
CREATE TABLE t_transactionhistory (
	ID        			INT(11) NOT NULL AUTO_INCREMENT,
    USER_EMAIL         	VARCHAR(100) NOT NULL,
    INVOICE_NUMBER      VARCHAR(100) NOT NULL,
    TRANSACTION_TYPE	VARCHAR(100) NOT NULL,
    DESCRIPTION			VARCHAR(100) NOT NULL,
    TOTAL_AMOUNT 		INT(100) NOT NULL,
    CREATED_ON			DATE NOT NULL,
	CONSTRAINT PK_TRANSACTIONHISTORY PRIMARY KEY (ID)
);

INSERT INTO t_transactionhistory (ID, USER_EMAIL, INVOICE_NUMBER, TRANSACTION_TYPE, DESCRIPTION, TOTAL_AMOUNT, CREATED_ON) VALUES ('1', 'user@nutech-integrasi.com', 'INV17082023-001', 'TOPUP', 'Top Up Balance', '100000', '2023-08-17');
INSERT INTO t_transactionhistory (ID, USER_EMAIL, INVOICE_NUMBER, TRANSACTION_TYPE, DESCRIPTION, TOTAL_AMOUNT, CREATED_ON) VALUES ('2', 'user@nutech-integrasi.com', 'INV17082023-002', 'PAYMENT', 'PLN Pascabayar', '10000', '2023-08-17');
INSERT INTO t_transactionhistory (ID, USER_EMAIL, INVOICE_NUMBER, TRANSACTION_TYPE, DESCRIPTION, TOTAL_AMOUNT, CREATED_ON) VALUES ('3', 'user@nutech-integrasi.com', 'INV17082023-003', 'PAYMENT', 'Pulsa Indosat', '40000', '2023-08-17');

DROP TABLE IF EXISTS userroleid;
CREATE TABLE userroleid (
	ID					INT(10) NOT NULL AUTO_INCREMENT,
    USERID         		INT(10) NOT NULL,
    ROLEID			    INT(10) NOT NULL,
	CONSTRAINT PK_USERROLEID PRIMARY KEY (ID)
);
