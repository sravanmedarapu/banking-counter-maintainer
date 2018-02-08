CREATE TABLE IF NOT EXISTS customer(
    customerId INT auto_increment NOT NULL,
    name varchar(128) NOT NULL,
    phoneNumber varchar(10),

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

CREATE TABLE IF NOT EXISTS serviceTypes (
    serviceId int auto_increment NOT NULL,
    name varchar(128) not null,
    avgTimeINMIN int(10) not null,
    PRIMARY KEY (name)
);

CREATE TABLE IF NOT EXISTS  token (
    tokenId int auto_increment NOT NULL,
    customerId int NOT NULL,
    serviceID int not null,
     inQ BOOLEAN DEFAULT TRUE,
    comments varchar(256),
    actionItems varchar(256),
    PRIMARY KEY (tokenId),
    FOREIGN KEY (customerId) REFERENCES customer(customerId),
    FOREIGN KEY (serviceID) REFERENCES serviceTypes(serviceID)
);

CREATE TABLE IF NOT EXISTS  employee (
    employeeId int auto_increment NOT NULL,
    name varchar(128) NOT NULL,
    role varchar(128) not null,
);

CREATE TABLE IF NOT EXISTS counterServices (
    id int auto_increment NOT NULL,
    counterId int NOT NULL,
    serviceID int not null,
    active BOOLEAN DEFAULT FALSE,
    PRIMARY KEY (id),
    FOREIGN KEY (serviceID) REFERENCES serviceTypes(serviceID)
);

CREATE TABLE IF NOT EXISTS  counterStatus (
    tokenId int NOT NULL,
    counterId int NOT NULL,
    waitTimeInMin int not null,
    inQ BOOLEAN DEFAULT TRUE,
    active BOOLEAN DEFAULT TRUE,
    FOREIGN KEY (tokenId) REFERENCES token(tokenId)
);
