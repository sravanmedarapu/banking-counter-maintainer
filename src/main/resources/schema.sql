DROP TABLE IF EXISTS Customer;
CREATE TABLE IF NOT EXISTS Customer(
    customerId INT auto_increment NOT NULL,
    name varchar(128) NOT NULL,
    phoneNumber varchar(10),
    PRIMARY KEY (customerId),
);

DROP TABLE IF EXISTS Address;
CREATE TABLE IF NOT EXISTS Address (
    addressId int auto_increment NOT NULL,
    customerId int NOT NULL ,
    city varchar(128),
    state varchar(128),
    country varchar(128),
    zipcode varchar(10),
    PRIMARY KEY (addressId, customerId),
    FOREIGN KEY (customerId) REFERENCES Customer(customerId)
);

DROP TABLE IF EXISTS Branch;
CREATE TABLE IF NOT EXISTS Branch (
    branchId int AUTO_INCREMENT NOT NULL,
    branchName VARCHAR(128) NOT NULL,
    PRIMARY KEY (branchId, branchName)
);

DROP TABLE IF EXISTS TokenType;
CREATE TABLE IF NOT EXISTS TokenType (
    tokenTypeId int auto_increment NOT NULL,
    name varchar(128) not null,
    PRIMARY KEY (tokenTypeId)
);

DROP TABLE IF EXISTS Token;
CREATE TABLE IF NOT EXISTS  Token (
    tokenId int auto_increment NOT NULL,
    customerId int NOT NULL,
    tokenTypeId int not null,
    comments varchar(512) DEFAULT '',
    status varchar(128),
    tokenPriority varchar(128) not null default 'REGULAR',
    branchId int NOT NULL ,
    PRIMARY KEY (tokenId),
    FOREIGN KEY (customerId) REFERENCES Customer(customerId),
    FOREIGN KEY (tokenTypeId) REFERENCES TokenType(tokenTypeId),
    FOREIGN KEY (branchId) REFERENCES Branch(branchId)
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
    active BOOLEAN DEFAULT TRUE,
    employeeId int NOT NULL,
    counterType varchar(128) not null,
    branchId int not null,
    CONSTRAINT PK_COUNTER PRIMARY KEY (id, counterId, employeeId, active),
    FOREIGN KEY (employeeId) REFERENCES Employee(employeeId),
    FOREIGN KEY(branchId) REFERENCES Branch(branchId)
);

DROP TABLE IF EXISTS Service;
CREATE TABLE IF NOT EXISTS Service (
    serviceId int auto_increment NOT NULL,
    serviceName VARCHAR (128) not null,
    PRIMARY KEY (serviceId),
);


DROP TABLE IF EXISTS CounterService;
CREATE TABLE IF NOT EXISTS CounterService (
    id int auto_increment NOT NULL,
    counterId int NOT NULL,
    serviceId int NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (counterId) REFERENCES Counter(counterId),
    FOREIGN KEY (serviceId) REFERENCES Service(serviceId)
);

DROP TABLE IF EXISTS TokenSubTasks;
CREATE TABLE IF NOT EXISTS TokenSubTasks(
  id INT auto_increment NOT  NULL,
  tokenTypeId INT NOT NULL,
  serviceId int NOT NULL,
  FOREIGN KEY (serviceId) REFERENCES Service(serviceId),
  FOREIGN KEY (tokenTypeId) REFERENCES TokenType(tokenTypeId),
  PRIMARY KEY (id)

);

DROP TABLE IF EXISTS TokenStatus;
CREATE TABLE IF NOT EXISTS  TokenStatus (
    id int auto_increment NOT NULL,
    tokenId int NOT NULL,
    counterId int NOT NULL,
    inQ BOOLEAN DEFAULT TRUE,
    PRIMARY KEY (id),
    FOREIGN KEY (tokenId) REFERENCES Token(tokenId),
    FOREIGN KEY (counterId) REFERENCES Counter(counterId)
);
