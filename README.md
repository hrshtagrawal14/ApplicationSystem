// Post Queries

POST localhost:8080/api/offers/ 
BODY should contain jobTitle and startDate. 
F.e. {"jobTitle" : "Developer", "startDate" : "2017-10-15"} This API will create new offer

POST localhost:8080/api/offers/{offerId}/applications/ 
BODY should contain email and resume text F.e. {"email" : "harshit@email.com", "resumeText" : "text"} will create new appliation for offer with "offerId"

POST localhost:8080/api/offers/{offerId}/applications/{applicationId}/status 
BODY should contain status F.e. {"status" : "APPLIED"}

//Get Queries
GET localhost:8080/api/offers/{offerId}/applications/{applicationId} 
lists specified application for offer

GET localhost:8080/api/offers/{offerId}/applications/ 
list all applications for offer

GET localhost:8080/api/offers/applications/ 
lists all applications

GET localhost:8080/api/offers/ 
lists all offers

GET localhost:8080/api/offers/{id} 
will show offer with specified "id"

GET localhost:8080/api/offers/
lists all offers

GET localhost:8080/api/offers/{id} 
will show offer with specified "id"