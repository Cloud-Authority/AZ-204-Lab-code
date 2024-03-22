Create network
sudo docker network create mynetwork

Run mysql container
sudo docker run --name mysql-container --network mynetwork -p 3306:3306 -e MYSQL_ROOT_PASSWORD=makarand -e MYSQL_DATABASE=studentdb -e MYSQL_USER=makarand -e MYSQL_PASSWORD=makarand -d mysql
sudo docker run --name mysql-container2 --network mynetwork -p 3306:3306 -e MYSQL_ROOT_PASSWORD=makarand -e MYSQL_DATABASE=studentdb -e MYSQL_USER=makarand -e MYSQL_PASSWORD=makarand -d mysql

Start mysql container
sudo docker start mysql-container

Connect to mysql database of container
sudo docker exec -it mysql-container bash

mysql -u root -p
Enter Password: makarand

mysql> create database studentdb;
mysql> use studentdb;
mysql> select * from student_tbl;

Build application image
sudo docker build -t student-app:v1 .

Create and Run app container
sudo docker run -p 8080:9090 --name studentapp-container  --network mynetwork -d student-app:v1

Run the application:
method - get -->  http://4.246.189.7:8080/student-api/students
method - post --> http://4.246.189.7:8080/student-api/students
    {
        "studentName": "Test",
        "studentScore": 95.0,
        "address": {
        "city": "Mumbai",
        "state": "MH",
        "pin": "400001"
        }
    }
