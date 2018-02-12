# banking-counterDetails-maintainer

# Problem​ ​Statement

Create webservice which creates a token for banking system and assign to nearest available counter.


# *How to run?*
     mvn spring-boot:run
    
# End Points
1.Create Token: Creates token and assign to immediate available counter
````
POST: http://localhost:8080/api/token/create
Request Body: 

          {
            "servicePriority":"REGULAR",
            "tokenType":"WITHDRAW",
            "customer":{
                        "name":"abc",
                        "phoneNumber":"123",
                        "address":{
                          "streeName":"streeName",
                          "city":"city",
                          "state":"state",
                          "country":"country",
                          "zipCode":"zipCode"
                        }
            }
          }

````

2. Get Token Status: get the token status

````
GET: http://localhost:8080/api/token/{tokenId}
````

3. Get Counters Status: get the all available counter with tokens in queue

````
GET: http://localhost:8080/api/counter/status
````

# Token Assign Design:
![Token Assigning Design](https://github.com/sravanmedarapu/banking-counter-maintainer/blob/master/doc/BankCounter-Token.png)

# [DB Shema](https://github.com/sravanmedarapu/banking-counter-maintainer/blob/master/src/main/resources/schema.sql):
![DB Schema](https://github.com/sravanmedarapu/banking-counter-maintainer/blob/master/doc/bank-api-db-schmea.png)
    
    
    
