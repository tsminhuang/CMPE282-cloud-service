#CMPE282 Lab2: Docker
####Supervised by Prof Kong Li
####Student: Tsung-Min Huang

##Q1.
####a.List technologies, softwares (including version), and platform (include version) for the REST client, REST server, and NoSQL.
* REST Client: Rested 2.7 on Mac OSX Sierra
* REST Server: Spring-boot 1.5.7, Spring 4.3.11, Java8 container  on vCenter server VM Ubuntu 16.04
* NoSQL databas: MongoDB 3.4.9 container on vCenter server VM Ubuntu 16.04

####b.For host1 and host2, list their OS (and version), and IP address.

* Host1: Ubuntu 16.04 130.65.159.116 (tsungmin\_ub1604\_146\_1)

* Host2: Mac OSX Sierra 10.250.70.244 (school wifi)

####c.A sample entire HTTP URL (including actual IP address of host1), URI, and request body for POST to create a new employee based on the XML format or JSON format (depending on your implementation). Also indicate if there is any additional setup (e.g., HTTP header, etc.).

Entire URL: http://130.65.159.116/cmpe282tsungmin146/rest/employee

URI: POST /cmpe282tsungmin146/rest/employee

Request Header:

```
Content-Type: application/json
```

Request body:

```
{  
   "id": 1,
   "firstName": "John",
   "lastName": "Doe"
}
```

##Q2. Describe detailed steps to build webapp and db docker containers with screenshots. (You can pull docker images directly, utilize Dockerfile to build one, or build on your own, or combination of the above.)

Both webapp and db used docker hub official image.

REST server: java:8

[DOCKER_PULL_REST]: img/DOCKER_PULL_REST.png

```
docker image pull java:8
```

![DOCKER_PULL_REST]

MongoDB server: mongo:3.4.9

[DOCKER_PULL_DB]: img/DOCKER_PULL_DB.png

```
docker image pull mongo:3.4.9
```

![DOCKER_PULL_DB]

##Q3. Describe detailed steps to deploy webapp and db docker containers with screenshots.

All the needed file put into cmperoot/Desktop/docker

<pre>
cmperoot/Desktop/docker
├── docker-compose.yml
└── restapp
    ├── application.properties
    ├── cmpe282tsungmin146.jar
</pre>

####Deploy MongoDB container 

Command to deploy MongoDB

[DOCKER_RUN_DB]: img/DOCKER_RUN_DB.png

```
docker run -d --name dbTsungMin146 mongo:3.4.9
```

1. Assign MongoDB container name: ***dbTsungMin146*** 
2. The default command run in MongoDB docker image is starting MongoDB service, so there is no need to change.

![DOCKER_RUN_DB]

#### Deploy REST Server container

Command to deploy REST server

[DOCKER_RUN_REST]: img/DOCKER_RUN_REST.png

```
docker run -d --name appTsungMin146 --link dbTsungMin146 \
           -p 80:8080 \
           -v "$PWD"/restapp:/rest \
           -w "/rest" \
           java:8 \
           java -jar cmpe282tsungmin146.jar \
           --spring.config.location="file:/application.properties"
```

1. Assign REST server container name: ***appTsungMin146***
2. Provide MongoDB server information to REST server
3. Mapping REST server web port to host
4. Mount volume to docker to make debugging and modify server config more easily
5. Run rest app with external config

![DOCKER_RUN_REST]

REST server external config: application.properties

```
# Web
server.contextPath=/cmpe282tsungmin146/rest
# MongoDB
spring.data.mongodb.database=cmpe282tsungmin146
spring.data.mongodb.uri=mongodb://dbTsungMin146:27017/cmpe282tsungmin146
```

##Q4. While both containers are running on host1, include the screenshots of the followings on host1

[DOCKER_VER_PS]: img/DOCKER_VER_PS.png

* Docker version: 17.03.2-ce
* Docker ps: appTsungMin146 and dbTsungMin146 are running

![DOCKER_VER_PS]

[DOCKER_NET]: img/DOCKER_NET.png

* Docker network inspect: Both container connected to default bridge

![DOCKER_NET]


##Q5. On host2, use REST client to issue the following requests and include screenshots of REST request and response (method, URL, HTTP headers) - success cases only:

[REST_POST_ID_10]: img/REST_POST_ID_10.png
[REST_POST_ID_20]: img/REST_POST_ID_20.png

#### issue a “POST /.../rest/employee” request to create two employees with id 10 and 20

![REST_POST_ID_10]

![REST_POST_ID_20]#### issue a “GET /.../rest/employee“ request to retrieve all employees[REST_GET_ALL]: img/REST_GET_ALL.png

![REST_GET_ALL]#### issue a “PUT /.../rest/employee/10“ request to update employee 10’s first name only

[REST_PUT_ID_10]: img/REST_PUT_ID_10.png

![REST_PUT_ID_10]#### issue a “DELETE /.../rest/employee/20“ request to delete employee 20 o screenshots:requestURI,responseheader(status200)

[REST_DEL_ID_20]: img/REST_DEL_ID_20.png

![REST_DEL_ID_20]#### issue a “GET /.../rest/employee“ request to retrieve all employees

[REST_GET_ALL_UPDATE]: img/REST_GET_ALL_UPDATE

![REST_GET_ALL_UPDATE]##Q6. In addition to the original homework, use docker compose to build and deploy containers in Q2 and Q3.

docker-compose.yml:

```
```

