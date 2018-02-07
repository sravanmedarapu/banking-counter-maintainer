 CREATE TABLE IF NOT EXISTS customer(
    customerId INT auto_increment NOT NULL,
    name varchar(128) NOT NULL,
    phoneNumber varchar(10),
    typeOfService varchar(10) NOT NULL,

    PRIMARY KEY (customerId)
);

 CREATE TABLE IF NOT EXISTS  address (
    id int auto_increment NOT NULL,
    customerId int,
    city varchar(128),
    state varchar(128),
    country varchar(128),
    zipcode varchar(10),
    PRIMARY KEY (id),
    FOREIGN KEY (customerId) REFERENCES customer(customerId)
);

 
CREATE TABLE IF NOT EXISTS  token (
    tokenId int auto_increment NOT NULL,
    customerId int NOT NULL,
    counterId int not null,
    typeOfService varchar(10) NOT NULL,
    comments varchar(256),
    actionItems varchar(256),
    PRIMARY KEY (tokenId),
    FOREIGN KEY (customerId) REFERENCES customer(customerId)
);

 CREATE TABLE IF NOT EXISTS  counter (
    tokenId int NOT NULL,
    counterId int NOT NULL,
    status varchar(128) not null,
    FOREIGN KEY (tokenId) REFERENCES token(tokenId)
);


CREATE TABLE IF NOT EXISTS  employee (
    employeeId int auto_increment NOT NULL,
    name int NOT NULL,
    role varchar(128) not null,
);



