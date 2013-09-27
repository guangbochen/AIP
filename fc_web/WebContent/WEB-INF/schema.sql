DROP DATABASE fcSportsware;
CREATE DATABASE IF NOT EXISTS fcSportsware;
USE fcSportsware;

CREATE TABLE IF NOT EXISTS admin (
    name     VARCHAR(20) NOT NULL,
    password VARCHAR(20) NOT NULL
);

CREATE TABLE IF NOT EXISTS products (
    id          INTEGER 		NOT  NULL AUTO_INCREMENT,
    category    VARCHAR(20) 	NOT NULL,
    code        VARCHAR(20) 	NOT NULL,
    description VARCHAR(200)	NOT NULL,
    price       DOUBLE(7,2) 	NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS orderlines (
    id          	INTEGER NOT  NULL AUTO_INCREMENT,
    product_id  	INTEGER NOT NULL,
    orderNumber 	VARCHAR(255) NOT NULL,
    quantity    	INTEGER(10) NOT NULL,
    lineTotal   	DOUBLE(7,2) NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (product_id) REFERENCES products(id)
);

CREATE TABLE IF NOT EXISTS orders (
    id              INTEGER NOT  NULL AUTO_INCREMENT,
    orderNumber     VARCHAR(32) NOT NULL,
    title           VARCHAR(10) NOT NULL,
    surname         VARCHAR(32) NOT NULL,
    givenName       VARCHAR(32) NOT NULL,
    email           VARCHAR(100) NOT NULL,
    unitNumber      VARCHAR(32) NOT NULL,
    street          VARCHAR(32) NOT NULL,
    state           VARCHAR(32) NOT NULL,
    suburb          VARCHAR(32) NOT NULL,
    postCode        VARCHAR(10) NOT NULL,
    country         VARCHAR(32) NOT NULL,
    paymentDetails  VARCHAR(100) NOT NULL,
    status          VARCHAR(7) NOT NULL,
    grandTotal      DOUBLE(7,2) NOT NULL,
    PRIMARY KEY (id)
);


-- insert dummy data 
INSERT INTO admin(name,password) VALUES('orders','orderspw');

INSERT INTO products(category,code,description,price) VALUES('jerseys', '1' ,'adidas Traversa 11 Goalkeepers Jersey Silver', 39);
INSERT INTO products(category,code,description,price) VALUES('jerseys', '2' ,'adidas Traversa 11 Goalkeepers Jersey Black', 39);
INSERT INTO products(category,code,description,price) VALUES('jerseys', '3' ,'adidas Traversa 11 Goalkeepers Jersey Yellow', 39);
INSERT INTO products(category,code,description,price) VALUES('jerseys', '4' ,'adidas Traversa 11 Goalkeepers Jersey Red', 39);
INSERT INTO products(category,code,description,price) VALUES('jerseys', '5' ,'adidas Traversa 11 Goalkeepers Jersey Navy', 39);
INSERT INTO products(category,code,description,price) VALUES('jerseys', '6' ,'adidas Traversa 11 Goalkeepers Jersey Green', 39);
INSERT INTO products(category,code,description,price) VALUES('shorts', '7' ,'adidas Parma II Short Silver', 17.99);
INSERT INTO products(category,code,description,price) VALUES('shorts', '8' ,'adidas Parma II Short Black', 17.99);
INSERT INTO products(category,code,description,price) VALUES('shorts', '9' ,'adidas Parma II Short Yellow', 17.99);
INSERT INTO products(category,code,description,price) VALUES('shorts', '10' ,'adidas Parma II Short Red', 17.99);
INSERT INTO products(category,code,description,price) VALUES('shorts', '11' ,'adidas Parma II Short Navy', 17.99);
INSERT INTO products(category,code,description,price) VALUES('shorts', '12' ,'adidas Parma II Short Green', 17.99);
INSERT INTO products(category,code,description,price) VALUES('socks', '13' ,'adidas Adisock Silver', 9.99);
INSERT INTO products(category,code,description,price) VALUES('socks', '14' ,'adidas Adisock Black', 9.99);
INSERT INTO products(category,code,description,price) VALUES('socks', '15' ,'adidas Adisock Yellow', 9.99);
INSERT INTO products(category,code,description,price) VALUES('socks', '16' ,'adidas Adisock Red', 9.99);
INSERT INTO products(category,code,description,price) VALUES('socks', '17' ,'adidas Adisock Navy', 9.99);
INSERT INTO products(category,code,description,price) VALUES('socks', '18' ,'adidas Adisock Green', 9.99);
INSERT INTO products(category,code,description,price) VALUES('socks', '19' ,'adidas Adisock White', 9.99);
INSERT INTO products(category,code,description,price) VALUES('gloves', '20' ,'Nike GK Spyne Pro Glove White/Blue', 75.99);
INSERT INTO products(category,code,description,price) VALUES('gloves', '21' ,'Nike LTWT Field Players Glove Black/White', 26.99);
INSERT INTO products(category,code,description,price) VALUES('gloves', '22' ,'Nike5 futsal Glove White/Yellow', 24.99);
INSERT INTO products(category,code,description,price) VALUES('gloves', '23' ,'Puma PowerCat 1.12 Grip GK Gloves', 54.99);
INSERT INTO products(category,code,description,price) VALUES('gloves', '24' ,'Puma King CC GK Gloves', 39.99);
INSERT INTO products(category,code,description,price) VALUES('gloves', '25' ,'Puma PowerCat 2.12 Grip GC GK Gloves', 35.99);
INSERT INTO products(category,code,description,price) VALUES('gloves', '26' ,'Puma PowerCat 2.12 Protect GC GK Gloves', 35.99);
INSERT INTO products(category,code,description,price) VALUES('jackets', '27' ,'adidas Core II Rain Jacket Silver', 42.99);
INSERT INTO products(category,code,description,price) VALUES('jackets', '28' ,'adidas Core II Rain Jacket Black', 42.99);
INSERT INTO products(category,code,description,price) VALUES('jackets', '29' ,'adidas Core II Rain Jacket Yellow', 42.99);
INSERT INTO products(category,code,description,price) VALUES('jackets', '30' ,'adidas Core II Rain Jacket Red', 42.99);
INSERT INTO products(category,code,description,price) VALUES('jackets', '31' ,'adidas Core II Rain Jacket Navy', 42.99);
INSERT INTO products(category,code,description,price) VALUES('jackets', '32' ,'adidas Core II Rain Jacket Green', 42.99);
INSERT INTO products(category,code,description,price) VALUES('jackets', '33' ,'Puma TT King Reversible Jacket - Red', 89.99);
INSERT INTO products(category,code,description,price) VALUES('jackets', '34' ,'Puma TT King Reversible Jacket - Black', 89.99);
INSERT INTO products(category,code,description,price) VALUES('jackets', '35' ,'Puma evoSPEED VersaCat Jacket - Black/Yellow', 74.99);
INSERT INTO products(category,code,description,price) VALUES('jackets', '36' ,'Puma evoSPEED Woven Jacket - Black/Yellow', 49.99);
INSERT INTO products(category,code,description,price) VALUES('jackets', '37' ,'Puma King Lightweight Rain Jacket - Black', 30.00);
INSERT INTO products(category,code,description,price) VALUES('equipment', '38' ,'Nike Brasilia 5 Medium Duffel Bag Black', 37.99);
INSERT INTO products(category,code,description,price) VALUES('equipment', '39' ,'Nike Brasilia 5 Medium Duffel Bag Navy', 37.99);
INSERT INTO products(category,code,description,price) VALUES('equipment', '40' ,'Nike Club Nutmeg Medium Backpack Black', 33.99);
INSERT INTO products(category,code,description,price) VALUES('equipment', '41' ,'Nike Club Nutmeg Medium Backpack Navy', 33.99);
INSERT INTO products(category,code,description,price) VALUES('equipment', '42' ,'Nike Team Equipment Bag with Wheels Black', 98.99);
INSERT INTO products(category,code,description,price) VALUES('equipment', '43' ,'Nike Team Equipment Ball Bag Black', 29.99);
INSERT INTO products(category,code,description,price) VALUES('equipment', '44' ,'Nike Club Team White Match Ball', 32.99);
INSERT INTO products(category,code,description,price) VALUES('equipment', '45' ,'Nike Premier Team FIFA White Match Ball', 47.99);
INSERT INTO products(category,code,description,price) VALUES('equipment', '46' ,'adidas 11Glider Training Ball', 17.99);
INSERT INTO products(category,code,description,price) VALUES('equipment', '47' ,'adidas 11Training Pro Ball', 27.99);
INSERT INTO products(category,code,description,price) VALUES('equipment', '48' ,'Nike Strike White Training Ball', 24.99);
INSERT INTO products(category,code,description,price) VALUES('equipment', '49' ,'Nike Team Trainer Football Orange Ball', 19.99);
INSERT INTO products(category,code,description,price) VALUES('equipment', '50' ,'Nike Team Trainer Football White Ball', 19.99);
INSERT INTO products(category,code,description,price) VALUES('equipment', '51' ,'Nike Guard Stay II Black', 9.99);
INSERT INTO products(category,code,description,price) VALUES('equipment', '52' ,'Nike J Guard Shinguard Black', 9.99);
INSERT INTO products(category,code,description,price) VALUES('equipment', '53' ,'Nike J Guard Shinguard Red', 9.99);
INSERT INTO products(category,code,description,price) VALUES('equipment', '54' ,'Nike J Guard Shinguard White', 9.99);
INSERT INTO products(category,code,description,price) VALUES('equipment', '55' ,'Hart Futsal Goal', 2085);
INSERT INTO products(category,code,description,price) VALUES('equipment', '56' ,'Hart Futsal Goal Net', 159);
INSERT INTO products(category,code,description,price) VALUES('equipment', '57' ,'Hart International Senior Goal Net', 210);
INSERT INTO products(category,code,description,price) VALUES('equipment', '58' ,'Hart Senior Goal', 3295);
INSERT INTO products(category,code,description,price) VALUES('equipment', '59' ,'Hart Small Sided Football Goals Under 10/11', 2745);
INSERT INTO products(category,code,description,price) VALUES('equipment', '60' ,'Hart Small Sided Football Goals Under 8/9', 2085);
INSERT INTO products(category,code,description,price) VALUES('equipment', '61' ,'Porta Gol Flexi 2.0', 169);
INSERT INTO products(category,code,description,price) VALUES('equipment', '62' ,'Porta Gol Flexi 3.6', 199);
INSERT INTO products(category,code,description,price) VALUES('equipment', '63' ,'Porta Gol Flexi 7.3 Full Size', 299);
INSERT INTO products(category,code,description,price) VALUES('equipment', '64' ,'Porta Gol Square 5 Foot Pop-Up Goals', 169);
INSERT INTO products(category,code,description,price) VALUES('equipment', '65' ,'Porta Gol Square 6 Foot Pop-Up Goals', 199);
INSERT INTO products(category,code,description,price) VALUES('equipment', '66' ,'HART Inground Corner Post (set of 4)', 88.99);
INSERT INTO products(category,code,description,price) VALUES('equipment', '67' ,'HART Spring Loaded Corner Post (set of 4)', 122.99);
INSERT INTO products(category,code,description,price) VALUES('equipment', '68' ,'HART Base Drink Bottle - 750ml', 3.50);
INSERT INTO products(category,code,description,price) VALUES('equipment', '69' ,'HART Teamster Bottle - 800ml', 8.50);
INSERT INTO products(category,code,description,price) VALUES('equipment', '70' ,'HART Training Singlet Fluoro - Large', 4.90);
INSERT INTO products(category,code,description,price) VALUES('equipment', '71' ,'HART Training Singlet Fluoro - Medium', 4.90);
INSERT INTO products(category,code,description,price) VALUES('equipment', '72' ,'HART Training Singlet Fluoro - Small', 4.90);
INSERT INTO products(category,code,description,price) VALUES('equipment', '73' ,'HART Training Vest - Extra Small', 6.90);
INSERT INTO products(category,code,description,price) VALUES('equipment', '74' ,'HART Training Vest - Junior', 5.90);
INSERT INTO products(category,code,description,price) VALUES('equipment', '75' ,'HART Training Vest - Senior', 6.90);
INSERT INTO products(category,code,description,price) VALUES('equipment', '76' ,'HART Team Scrimmage Swoosh Vest', 9.99);
