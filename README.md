# parking-slot
find parking slot

#URLS

GARAGE

http://localhost:8080/nike-rest/api/services/1.0/garage/1/parkingSlots

PARKED SLOTS

http://localhost:8080/nike-rest/api/services/1.0/garage/1/parkingSlot/11

http://localhost:8080/nike-rest/api/services/1.0/garage/1/parkingSlot/12

http://localhost:8080/nike-rest/api/services/1.0/garage/2/parkingSlot/21

http://localhost:8080/nike-rest/api/services/1.0/garage/2/parkingSlot/22

VACANT SPOTS

http://localhost:8080/nike-rest/api/services/1.0/garage/1/parkingSlot/13

FEE CALCULATOR

http://localhost:8080/nike-rest/api/services/1.0/parkingfee/garageID/1/parkingSlot/11

http://localhost:8080/nike-rest/api/services/1.0/parkingfee/garageID/1/parkingSlot/12

http://localhost:8080/nike-rest/api/services/1.0/parkingfee/garageID/2/parkingSlot/21

http://localhost:8080/nike-rest/api/services/1.0/parkingfee/garageID/2/parkingSlot/22

FEE CALCULATOR ERROR
http://localhost:8080/nike-rest/api/services/1.0/parkingfee/garageID/1/parkingSlot/13

#DEPLOY
- Make sure that you have JAVA and MAVEN Install
- Using CMD, go to root of project i.e pom.xml
- mvn -q clean package spring-boot:run
