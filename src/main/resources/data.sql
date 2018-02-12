
insert into employee(name, role) values ('MANAGER1','MANAGER'),('OPERATOR2','OPERATOR'),('MANAGER2','MANAGER'),('OPERATOR3','OPERATOR'),('MANAGER4','MANAGER'),('OPERATOR4','OPERATOR');



insert into ServiceTypes (name, avgTimeInMin) values ('WITHDRAW',5), ('DEPOSIT',5), ('CHECK_DEPOSIT',10), ('ACCOUNT_CLOSE',5);


insert into counterServices(counterId, serviceID, employeeId, counterType, active) values (1,1,1, 'BOTH','TRUE'), (1,2,2,'PREMIUM','TRUE'),(1,3,3,'REGULAR','TRUE'),(2,4,4,'PREMIUM','TRUE'),(3,1,5,'REGULAR','TRUE');

insert into customer( name ,phoneNumber) values ('ABC', '1234567'),('XYZ', '6868'),('ABsdfdsfC', '07667546'),('link', '09843');
    


insert into  token (customerId , serviceID, inQ) values(1, 1, true), (2, 1,true),(3, 1, true);

insert into counterStatus(tokenId,counterId, waitTimeInMin) values (1,1, 10),(2,2, 10),(3,1, 10);

