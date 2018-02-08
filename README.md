# banking-counterDetails-maintainer
Banking token based counterDetails management POC


#Problem​ ​Statement

A bank, ABC Bank, has many branches and customers. Each branch has a limited number of
teller counterDetails. ABC Bank would like to improve their customer experience at each branch.
Managing long queues of customers at the bank during business hours is one of the most
challenging tasks. ABC Bank also provides a better customer experience to their premium
customers by providing priority over non premium customers (less waiting time at branches).
In order to achieve this, ABC Bank has decided to introduce new machines to manage queues
at each of their branches.

To achieve this, please develop a tokening system which can be configured to do the following;

    ● Issue tokens based on the following condition

        ○ Check if a customer is
            ■ existing customer
            ■ new customer
        ○ If he/she is a new customer, then collect his personal details
            ■ Name
            ■ Phone number
            ■ Address
            ■ Type of service (premium/regular)
        ○ If he/she is a new customer, then collect his personal details
            ■ Name
            ■ Phone number
            ■ Address
            ■ Type of service (premium/regular)
        
        ○ Issue a token number
        
        ○ If they are an existing customer, collect customer identification details such as
        the type of service they’re looking for (premium/regular)
        
        ○ Set the priority on the token based on the customer type while issuing tokens
        
        ○ If a customer is a valued customer, send them to a counterDetails which serves priority
        customers by setting the token to high priority
        
        
    ● Expose a service that displays a list of service counterDetails and token numbers under each
    service counterDetails, which customers can use to to check the counterDetails assigned to their
    token.
    
    ● Create services to serve customers using their tokens.
    
    
    ● Multi-counterDetails services: Some services may require a customer to traverse multiple
    counterDetails to complete the process. This system should support such functions. If a
    service is so configured, after customers are served at one service desk, they will
    automatically be queued up at the next service desk for that service. This feature can be
    used in addition to or in place of Multi-service selection by the customer.
    
    
    
    ● Provide functionality that lets operators add action items or comment on tokens at each
    service desk, while serving a customer.
    
    ● A token can be marked completed or cancelled. Tokens will not be valid after they are
    marked completed or cancelled. Only Operators and managers can mark tokens as
    completed or cancelled.
