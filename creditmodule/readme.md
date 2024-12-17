Credit Module Case Study

You can view H2 in memory DB with http://localhost:8080/h2/login.do url.

You may need to create empty test.mv file in order to H2 Db to use.
under your user folder.(C:\Users\inghubuser)

One Test Customer will be created with Bootstrap through DataLoader.(creditmodule>config>initalzer)

InMemory Spring Security Authentication is used for basic Auth.
(creditmodule>config>security) Username and password is already set on Sample Postman Collection.

You need to import /Create Loan Case.postman_collection.json/ Postman Collection.

You need to Create Loan Before Pay Loan since it is not automatically generated as Sample Customer.

Missing Best Practices :
Dockerized Container,
Keycloak For Authentication and Authorization,
Swagger,
Separate Modules for bussiness, connector, core, web,
Enum validation for creating loan request,
Pagination,
More Unit Tests.

