# Getting Started

### Reference Documentation
For further reference, please consider the following sections:

## Project Description
Is a project that  manage the card api  through some
API.

### Guides

## Apis
Kindly use swagger for api reference
http://localhost:8080/swagger-ui/index.html#/

Instructions
1. Run mvn clean install to install all the dependency
2. Where to change db connection
   Access application properties and changes url port username and password

Test user credentions 

username **amos@gmail.com** password **1234** role user

username **john@gmail.com** password **1234** role admin

###
To run/build project  cd into project then  mvn spring-boot:run  to package mvn package

### Kindly note
After project start some dummy data are inserted to the in  database as required neccesary data for system user i,e

Users and roles 


Follow the instruction in the Api document to run the api calls

## Entities
1.Card -> manages card crud operations
2.Role -> stores role of users of the system
3.Users -< stores users


CartController is used to manage orders api
1.   **/v1/api/card/all** -> used to get cards 
   2. **/v1/api/card/{cardId}**  -> get specific card
3. **/v1/card/create**  -> used to create card details
4. **/v1/card/update** -> used to update card details
5. **/v1/card/delete** -> used to delete specific card


UserController is used to manage recipes and cart  apis
1. **/api/v1/login** -> authenicate user
2. **/api/v1/token/refresh** -> refresh token for user







