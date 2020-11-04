DROP DATABASE IF EXISTS AbayaFarm;
CREATE DATABASE IF NOT EXISTS AbayaFarm;
use AbayaFarm;

CREATE TABLE login(
	id int auto_increment,
	userName varchar(20) not null,
	password varchar(8) not null,
	CONSTRAINT PRIMARY KEY(id)
);

CREATE TABLE supplier(
	supplierId varchar(5),
	supplierName varchar(50) not null,
	supplierTp int(10),
	supplierAddress varchar(60),
	type varchar(20) not null,
	CONSTRAINT PRIMARY KEY (supplierId)
);

CREATE TABLE batch(
	batchId varchar(10),
	type varchar(10) not null,
	qty int(5) not null,
	status varchar(4) not null,
	CONSTRAINT PRIMARY KEY(batchId)
);

CREATE TABLE supplierOrder(
	supOrderId varchar(20),
	supplierId varchar(5),
	batchId varchar(10),
	description varchar(80),
	qtyOnHand int(10) not null,
	unitPrice  decimal(12,2),
	date DATE not null,
	CONSTRAINT PRIMARY KEY(supOrderId),
	CONSTRAINT FOREIGN KEY(supplierId) REFERENCES supplier(supplierId)
	ON UPDATE CASCADE ON DELETE CASCADE,
	CONSTRAINT FOREIGN KEY(batchId) REFERENCES batch(batchId)
	ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE cage(
	cageId varchar(5) not null,
	batchId varchar(10),
	maxQty int(10) not null,
	availableQty int(10) not null,
	CONSTRAINT PRIMARY KEY(cageId),
	CONSTRAINT FOREIGN KEY(batchId) REFERENCES batch(batchId)
	ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE store(
	storeId varchar(3) not null,
	type varchar(15) not null,
	qtyOnStore int not null,
	unitPrice decimal(12,2) not null,
	CONSTRAINT PRIMARY KEY(storeId)
	
);

CREATE TABLE egg(
	cageId varchar(5),
	storeId varchar(3),
	type varchar(1) NOT NULL,
	qty int not null,
	term int not null,
	damage int,
	date DATE not null,
	CONSTRAINT PRIMARY KEY(cageId,term,type,date),
	CONSTRAINT FOREIGN KEY(cageId) REFERENCES cage(cageId)
	ON UPDATE CASCADE ON DELETE CASCADE,
	CONSTRAINT FOREIGN KEY(storeId) REFERENCES store(storeId)
	ON UPDATE CASCADE ON DELETE CASCADE		
	
);

CREATE TABLE manure(
	cageId varchar(5),
	packSize varchar(4) NOT NULL,
	qty int not null,
	unitPrice decimal(12,2),
	date DATE,
	CONSTRAINT PRIMARY KEY(cageId,date,packsize),
	CONSTRAINT FOREIGN KEY(cageId) REFERENCES cage(cageId)
	ON UPDATE CASCADE ON DELETE CASCADE	
);

CREATE TABLE feed(
	feedId varchar(4) not null,
	qtyOnHand int not null,
	feedType varchar(30) not null,
	packSize int,
	CONSTRAINT PRIMARY KEY(feedId)
);

CREATE TABLE feedDetails(
	cageId varchar(5),
	term int NOT NULL,
	usedQty int not null,
	date DATE,
	CONSTRAINT PRIMARY KEY(cageId,date,term),
	CONSTRAINT FOREIGN KEY(cageId) REFERENCES cage(cageId)
	ON UPDATE CASCADE ON DELETE CASCADE	
);

CREATE TABLE medicine(
	medicineId varchar(5) not null,
	medicineName varchar(50) not null,
	description varchar(30),
	CONSTRAINT PRIMARY KEY(medicineId)
);

CREATE TABLE medicineDetails(
	cageId varchar(5),
	usedQty int not null,
	date DATE,
	time TIME,
	CONSTRAINT PRIMARY KEY(cageId,date),
	CONSTRAINT FOREIGN KEY(cageId) REFERENCES cage(cageId)
	ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE customer(
	custId varchar(5) not null,
	custName varchar(30) not null,
	custTp int(10),
	CONSTRAINT PRIMARY KEY(custId)
);

CREATE TABLE orders(
	custId varchar(5) NOT NULL,
	orderId varchar(20),
	date DATE not null,
	CONSTRAINT PRIMARY KEY(orderId),
	CONSTRAINT FOREIGN KEY(custId) REFERENCES customer(custId)
	ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE orderDetails(
	orderId varchar(20),
	storeId varchar(3),
	orderQty int ,
	CONSTRAINT PRIMARY KEY (orderId,storeId),
    CONSTRAINT FOREIGN KEY (orderId) REFERENCES orders(orderId) 
	ON DELETE CASCADE ON UPDATE CASCADE,
	CONSTRAINT FOREIGN KEY (storeId) REFERENCES store(storeId)
	ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE income(
	orderId varchar(20),
	date DATE,
	total decimal(12,2),
	CONSTRAINT PRIMARY KEY (orderId),
	CONSTRAINT FOREIGN KEY (orderId) REFERENCES orders(orderId) 
	ON DELETE CASCADE ON UPDATE CASCADE
);

INSERT INTO batch(batchId, type,qty,status)
	VALUES('B1','chick',100,'in'),
		('B2','grower',150,'in'),
		('B3','layer',200,'in'),
		('B4','layer',150,'exit'),
		('B5','chick',250,'in'),
		('B6','layer',150,'in');

INSERT INTO cage(cageId,batchId,maxQty,availableQty)
	VALUES('C1','B1',100,100),
		('C2','B2',200,150),
		('C3','B3',200,200),
		('C4','B5',150,250),
		('C5','B6',200,150);


INSERT INTO store(storeId,type,qtyOnStore,unitPrice)
	VALUES('S1','Egg-S',152,15.50),
		('S2','Egg-M',200,17.50),
		('S3','Egg-L',217,18.50),
		('S4','Manure-5kg',10,30.00),
		('S5','Manure-10kg',40,60.00),
		('S6','Manure-25kg',25,150.00),
		('S7','Manure-50kg',18,300.00);

INSERT INTO egg(cageId,storeId,type,qty,term,damage,date)
	VALUES('C3','S1','s',50,1,2,'2020-05-06'),
		('C3','S2','m',53,1,2,'2020-05-06'),
		('C3','S3','l',78,1,6,'2020-05-06'),
		('C4','S1','s',62,1,2,'2020-05-06'),
		('C4','S2','m',84,1,7,'2020-05-06'),
		('C4','S3','l',52,1,2,'2020-05-06'),
		('C3','S1','s',70,3,5,'2020-05-06'),
		('C3','S2','m',40,3,8,'2020-05-06'),
		('C3','S3','l',60,3,2,'2020-05-06');
	
INSERT INTO feed(feedId,qtyOnHand,feedType,packSize)
	VALUES('F1',10,'chick',25),
		('F2',11,'grower',50),
		('F3',14,'layer',50);

SELECT * FROM batch;
SELECT * FROM cage;
SELECT * FROM store;
SELECT * FROM egg;
SELECT * FROM feed;


SELECT f.feedId, f.qtyOnHand,f.feedType,f.packSize FROM feed f, batch b WHERE f.feedType=b.type and  b.type='layer' GROUP BY(feedType);



