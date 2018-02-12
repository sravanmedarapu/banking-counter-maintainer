
DROP TABLE IF EXISTS customer;
CREATE TABLE IF NOT EXISTS customer(
    customerId INT auto_increment NOT NULL,
    name varchar(128) NOT NULL,
    phoneNumber varchar(10),

    PRIMARY KEY (customerId)
);

DROP TABLE IF EXISTS address;
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

DROP TABLE IF EXISTS ServiceTypes;
CREATE TABLE IF NOT EXISTS ServiceTypes (
    serviceId int auto_increment NOT NULL,
    name varchar(128) not null,
    avgTimeINMIN int(10) not null,
    PRIMARY KEY (name)
);

DROP TABLE IF EXISTS token;
CREATE TABLE IF NOT EXISTS  token (
    tokenId int auto_increment NOT NULL,
    customerId int NOT NULL,
    serviceID int not null,
    inQ BOOLEAN DEFAULT TRUE,
    comments varchar(512) DEFAULT '',
    actionItems varchar(256),
    status varchar(128),
    servicePriority varchar(128),
    PRIMARY KEY (tokenId),
    FOREIGN KEY (customerId) REFERENCES customer(customerId),
    FOREIGN KEY (serviceID) REFERENCES ServiceTypes(serviceID)
);

DROP TABLE IF EXISTS employee;
CREATE TABLE IF NOT EXISTS  employee (
    employeeId int auto_increment NOT NULL,
    name varchar(128) NOT NULL,
    role varchar(128) not null,
);

DROP TABLE IF EXISTS counterServices;
CREATE TABLE IF NOT EXISTS counterServices (
    id int auto_increment NOT NULL,
    counterId int NOT NULL,
    serviceID int not null,
    active BOOLEAN DEFAULT FALSE,
    employeeId int NOT NULL,
    counterType varchar(128) not null,
    PRIMARY KEY (id, employeeId),
    FOREIGN KEY (serviceID) REFERENCES ServiceTypes(serviceID),
    FOREIGN KEY (employeeId) REFERENCES employee(employeeId)
);

DROP TABLE IF EXISTS counterStatus;
CREATE TABLE IF NOT EXISTS  counterStatus (
    tokenId int NOT NULL,
    counterId int NOT NULL,
    waitTimeInMin int,
    inQ BOOLEAN DEFAULT TRUE,
    active BOOLEAN DEFAULT TRUE,
    FOREIGN KEY (tokenId) REFERENCES token(tokenId)
);

