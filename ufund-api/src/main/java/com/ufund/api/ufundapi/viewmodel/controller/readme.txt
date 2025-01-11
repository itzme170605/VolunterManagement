DEMO SPRINT 1:

RUN MAVEN:
cd .\ufund-api\
mvn clean compile exec:java

GET ENTIRE CUPBOARD:
curl -X GET "http://localhost:8080/api/needs"

CREATE:
curl -X POST \
     -H "Content-Type: application/json" \
     -d '{
           "id": 2,
           "name": "Community Health Drive",
           "facility": "Community Center",
           "description": "Organizing a health check-up for the community.",
           "datetime": "270424 090000",
           "status": true,
           "required": 10,
           "volunteers": []
         }' \
     http://localhost:8080/api/needs

GET ENTIRE CUPBOARD:
curl -X GET "http://localhost:8080/api/needs"

UPDATE:
curl -X PUT \
     -H "Content-Type: application/json" \
     -d '{
           "id": 7,
           "name": "Community Health Drive",
           "facility": "Community Center in NY",
           "description": "Organizing a health check-up for the community.",
           "datetime": "270424 090000",
           "status": true,
           "required": 10,
           "volunteers": []
         }' \
     http://localhost:8080/api/needs

GET ENTIRE CUPBOARD:
curl -X GET "http://localhost:8080/api/needs"

SEARCH FOR NEEDS:
curl -X GET "http://localhost:8080/api/needs/?name=Community"

GET A NEED:
curl -X GET "http://localhost:8080/api/needs/7"

DELETE:
curl -X DELETE "http://localhost:8080/api/needs/7"

GET ENTIRE CUPBOARD:
curl -X GET "http://localhost:8080/api/needs"
