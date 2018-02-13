DROP TABLE IF EXISTS Address;
CREATE TABLE IF NOT EXISTS Address (
    addressId int auto_increment NOT NULL,
    city varchar(128),
    state varchar(128),
    country varchar(128),
    zipcode varchar(10),
    PRIMARY KEY (addressId)
);

DROP TABLE IF EXISTS Customer;
CREATE TABLE IF NOT EXISTS Customer(
    customerId INT auto_increment NOT NULL,
    name varchar(128) NOT NULL,
    phoneNumber varchar(10),
    addressId INT NOT NULL,
    PRIMARY KEY (customerId),
    FOREIGN KEY (addressId) REFERENCES Address(addressId)
);

DROP TABLE IF EXISTS ServiceTypes;
CREATE TABLE IF NOT EXISTS ServiceTypes (
    serviceId int auto_increment NOT NULL,
    name varchar(128) not null,
    PRIMARY KEY (serviceId)
);

DROP TABLE IF EXISTS Token;
CREATE TABLE IF NOT EXISTS  Token (
    tokenId int auto_increment NOT NULL,
    customerId int NOT NULL,
    serviceID int not null,
    inQ BOOLEAN DEFAULT TRUE,
    comments varchar(512) DEFAULT '',
    actionItems varchar(256),
    status varchar(128),
    servicePriority varchar(128),
    PRIMARY KEY (tokenId),
    FOREIGN KEY (customerId) REFERENCES Customer(customerId),
    FOREIGN KEY (serviceID) REFERENCES ServiceTypes(serviceID)
);

DROP TABLE IF EXISTS Employee;
CREATE TABLE IF NOT EXISTS  Employee (
    employeeId int auto_increment NOT NULL,
    name varchar(128) NOT NULL,
    role varchar(128) not null,
     PRIMARY KEY (employeeId)
);

DROP TABLE IF EXISTS Counter;
CREATE TABLE IF NOT EXISTS Counter (
    id int auto_increment NOT NULL,
    counterId int NOT NULL,
    serviceID int not null,
    active BOOLEAN DEFAULT FALSE,
    employeeId int NOT NULL,
    counterType varchar(128) not null,
    PRIMARY KEY (id, employeeId),
    FOREIGN KEY (serviceID) REFERENCES ServiceTypes(serviceID),
    FOREIGN KEY (employeeId) REFERENCES Employee(employeeId)
);

DROP TABLE IF EXISTS TokenStatus;
CREATE TABLE IF NOT EXISTS  TokenStatus (
    id int auto_increment NOT NULL,
    tokenId int NOT NULL,
    counterId int NOT NULL,
    inQ BOOLEAN DEFAULT TRUE,
    PRIMARY KEY (id),
    FOREIGN KEY (tokenId) REFERENCES Token(tokenId)
);
