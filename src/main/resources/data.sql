
insert into customer( name ,phoneNumber) values ('ABC', '1234567'),('XYZ', '6868'),('ABsdfdsfC', '07667546'),('link', '09843');

insert into TokenType (name) values ('WITHDRAW'), ('DEPOSIT'), ('CHECK_DEPOSIT'), ('ACCOUNT_CLOSE'),('ACCOUNT_OPEN');

insert into Branch (branchName) values ('MidTown'),('RURAL');

insert into  token (customerId , tokenTypeId, branchId) values(1, 1,1), (2, 1,1),(3, 1,1);


insert into employee(name, role) values ('OPERATOR','OPERATOR'),('OPERATOR2','OPERATOR'),('MANAGER2','MANAGER'),('OPERATOR3','OPERATOR'),('MANAGER4','MANAGER'),('OPERATOR4','OPERATOR'),('ATTENDER1','ATTENDER');

insert into Counter(counterId, employeeId, counterType, branchId) values (1,1, 'BOTH', 1), (2,2,'PREMIUM', 1),(3,3,'REGULAR', 1),(4,4,'PREMIUM', 1),(5,1,'REGULAR', 1);

INSERT INTO Service(serviceName) VALUES ('ACC_VERIFICATION'),('BALANCE_ENQUIRY'),('CASH_WITHDRAW'),('CASH_DEPOSIT'),('CHECK_DEPOSIT'),('ACC_CLOSE'),('ACC_OPEN'),('MANAGER_APPROVAL'),('DOC_VERIFICATION');

insert into CounterService(counterId, serviceId) VALUES (1, 1),(1, 2),(1, 3),(2, 1),(2, 4),(3, 1),(3, 5),(5, 1),(5, 2),(5, 6),(5, 7),(5,8),(5,9);


