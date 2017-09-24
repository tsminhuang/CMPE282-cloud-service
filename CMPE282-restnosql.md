#CMPE282 Lab1: Restful NoSQL
####Supervised by Prof Kong Li

## Q1. 
####a. List technologies, softwares (including version), and platforms for dev tools, REST client, REST server, and NoSQL database.
* OS: Mac OSX 10.12.6
* Dev tools: IntelliJ IDEA ULTIMATE 2017.2
* REST Client: Rested 2.7
* RSST Server: Spring-boot 1.5.7, Spring 4.3.11, Tomcat 8.5.20 (Spring embeded)
* NoSQL database: MongoDB (3.4.9)

####b. How did you build your REST server?
Use Spring build into standalone jar

####c. Is the format of the data object based on XML or JSON?
JSON

####d. A sample HTTP request URI and request body for POST to create a new employee based on the XML format or JSON format (depending on your answer to c). Also indicate if there is any additional setup (e.g., HTTP header, etc.).
Request URI: 
POST http://localhost:8080/cmpe282tsungmin146/rest/employee
Add addtional header to indicate this is JSON format: 
```Content-Type: application/json```
Request body: 
```{ "id": 1, "firstName" : "John", "lastName:" "Doe"}```

<div style="page-break-after: always;"></div>
 
####e. If the NoSQL needs to create DB objects beforehand
Frist, make user mongod daemon is startup, then use MongoDB shell to declare DB name.
 
No need, Spring automatiically 

#Q2. Functionality matrix:

|     | HTTP method + URI           | HTTP Success  | HTTP Error      | Staus |   
|----:|:----------------------------|:--------------|:----------------|:------|
|   1 | GET /.../rest/employee/m    | OK (200)      | Not found (404) | Done  |
|   2 | GET /.../rest/project/n     | OK (200)      | Not found (404) | Done  |
|   3 | POST /.../rest/employee     | Created (201) | Conflict (409)  | Done  |
|   4 | POST /.../rest/project      | Created (201) | Conflict (409)  | Done  |
|   5 | PUT /.../rest/employee/m    | OK (200)      | Not found (404) | Done  |
|   6 | PUT /.../rest/project/n     | OK (200)      | Not found (404) | Done  |
|   7 | DELETE /.../rest/employee/m | OK (200)      | Not found (404) | Done  |
|   8 | DELETE /.../rest/project/n  | OK (200)      | Not found (404) | Done  |
|   9 | GET /.../rest/employee      | OK (200)      | Not found (404) | Done  |
|  10 | GET /.../rest/project       | OK (200)      | Not found (404) | Done  |

<div style="page-break-after: always;"></div>

#List known issues

##1. Exception handle and parameter validation
For current implementation, use annotation **@Validted** and **@Valid** to check URL parameter check and JSON input check.
It avoids a lot of condition statement in business logic. However, the exception just direct throw by validation may cause hard to figure out the error message.

##2. Code redundancy 
A lot of code logic is the almost the same. For example:
**PUT API for employee and project** 
    * Check JSON content is valid for object 
    * Retrieve form NoSQL DB test object exists
    * Update object data
    * Save object back
The part of business code should be encapsulated to achieve code reuse.

<div style="page-break-after: always;"></div>


##GET /.../rest/employee/m

###Success: Get exist Id=1

[GET_EMPLOY_DB_ID1]: img/GET_EMPLOY_DB_ID1.png
[GET_EMPLOY_RS_ID1]: img/GET_EMPLOY_RS_ID1.png

|         | Screen Shot            |
|---------|:----------------------:|
| MongoDB | ![][GET_EMPLOY_DB_ID1] |
| Clent   | ![][GET_EMPLOY_RS_ID1] |
 
<div style="page-break-after: always;"></div>

###Error: Get not exist Id=2

[GET_EMPLOY_DB_ID2]: img/GET_EMPLOY_DB_ID2.png
[GET_EMPLOY_RS_ID2]: img/GET_EMPLOY_RS_ID2.png

|         | Screen Shot            |
|---------|:----------------------:|
| MongoDB | ![][GET_EMPLOY_DB_ID2] |
| Clent   | ![][GET_EMPLOY_RS_ID2] |

<div style="page-break-after: always;"></div>
 

##GET /.../rest/project/n

###Success: Query with exist Id=1

[GET_PROJECT_DB_ID1]: img/GET_PROJECT_DB_ID1.png
[GET_PROJECT_RS_ID1]: img/GET_PROJECT_RS_ID1.png
 
|         | Screen Shot             |
|---------|:-----------------------:|
| MongoDB | ![][GET_PROJECT_DB_ID1] |
| Clent   | ![][GET_PROJECT_RS_ID1] |

<div style="page-break-after: always;"></div>

###Error: Query with non exist Id=2

[GET_PROJECT_DB_ID2]: img/GET_PROJECT_DB_ID2.png
[GET_PROJECT_RS_ID2]: img/GET_PROJECT_RS_ID2.png

|         | Screen Shot             |
|---------|:-----------------------:|
| MongoDB | ![][GET_PROJECT_DB_ID2] |
| Clent   | ![][GET_PROJECT_RS_ID2] |

<div style="page-break-after: always;"></div>



##POST /.../rest/employee

###Success: Create employee based JSON: {"id":1", "firstName": "John1", "lastName": "Doe1"}

[POST_EMPLOY_DB_ID]: img/POST_EMPLOY_DB_ID.png
[POST_EMPLOY_RS_ID1]: img/POST_EMPLOY_RS_ID1.png
[POST_EMPLOY_DB_ID1]: img/POST_EMPLOY_DB_ID1.png
 
|         | Screen Shot             |
|---------|:-----------------------:|
| MongoDB | ![][POST_EMPLOY_DB_ID]  |
| Client  | ![][POST_EMPLOY_RS_ID1] |
| MongoDB | ![][POST_EMPLOY_DB_ID1] |

<div style="page-break-after: always;"></div>
 
###Error: Create employee with the same Id=1

[POST_EMPLOY_DB_ID1]: img/POST_EMPLOY_DB_ID1.png
[POST_EMPLOY_RS_ID1_1]: img/POST_EMPLOY_RS_ID1_1.png
[POST_EMPLOY_DB_ID1_1]: img/POST_EMPLOY_DB_ID1_1.png
 
|         | Screen Shot                |
|---------|:--------------------------:|
| MongoDB | ![][POST_EMPLOY_DB_ID1]    |
| Client  | ![][POST_EMPLOY_RS_ID1_1]  |    
| MongoDB | ![][POST_EMPLOY_DB_ID1_1]  |
 
<div style="page-break-after: always;"></div>

##POST /.../rest/project

###Success: Create project based JSON: {"id":1", "name": "project1", budget: 100}
 
[POST_PROJECT_DB_ID]: img/POST_PROJECT_DB_ID.png
[POST_PROJECT_RS_ID1]: img/POST_PROJECT_RS_ID1.png
[POST_PROJECT_DB_ID1]: img/POST_PROJECT_DB_ID1.png
 
|         | Screen Shot              |
|---------|:------------------------:|
| MongoDB | ![][POST_PROJECT_DB_ID]  |
| Client  | ![][POST_PROJECT_RS_ID1] |
| MongoDB | ![][POST_PROJECT_DB_ID1] |

<div style="page-break-after: always;"></div>
 
###Error: Create project with the same Id=1
 
[POST_PROJECT_DB_ID]: img/POST_PROJECT_DB_ID.png
[POST_PROJECT_RS_ID1_1]: img/POST_PROJECT_RS_ID1_1.png
[POST_PROJECT_DB_ID1_1]: img/POST_PROJECT_DB_ID1_1.png
 
|         | Screen Shot              |
|---------|:------------------------:|
| MongoDB | ![][POST_PROJECT_DB_ID]  |
| Client  | ![][POST_PROJECT_RS_ID1_1] |
| MongoDB | ![][POST_PROJECT_DB_ID1_1] |
 
<div style="page-break-after: always;"></div>
 

##PUT /.../rest/employee/n

###Success: update employee Id=1 field

[PUT_EMPLOY_DB_ID1]: img/PUT_EMPLOY_DB_ID1.png
[PUT_EMPLOY_RS_ID1_FIRST]: img/PUT_EMPLOY_RS_ID1_FIRST.png
[PUT_EMPLOY_DB_ID1_FIRST]: img/PUT_EMPLOY_DB_ID1_FIRST.png
[PUT_EMPLOY_RS_ID1_LAST]: img/PUT_EMPLOY_RS_ID1_LAST.png
[PUT_EMPLOY_DB_ID1_LAST]: img/PUT_EMPLOY_DB_ID1_LAST.png
[PUT_EMPLOY_RS_ID1_ALL]: img/PUT_EMPLOY_RS_ID1_ALL.png
[PUT_EMPLOY_DB_ID1_ALL]: img/PUT_EMPLOY_DB_ID1_ALL.png
 
|         | Screen Shot     |
|---------|:---------------:|
| MongoDB | ![][PUT_EMPLOY_DB_ID1]    |
| Client  | ![][PUT_EMPLOY_RS_ID1_FIRST]  |
| MongoDB | ![][PUT_EMPLOY_DB_ID1_FIRST]  |
| Client  | ![][PUT_EMPLOY_RS_ID1_LAST]  |
| MongoDB | ![][PUT_EMPLOY_DB_ID1_LAST]  |
| Client  | ![][PUT_EMPLOY_RS_ID1_ALL]  |
| MongoDB | ![][PUT_EMPLOY_DB_ID1_ALL]  |
 
<div style="page-break-after: always;"></div>
 
###Error: Employee Id=2 does not exist, Employ Id not match JSON Id
 
[PUT_EMPLOY_DB_ID2]: img/PUT_EMPLOY_DB_ID2.png
[PUT_EMPLOY_RS_ID2_1]: img/PUT_EMPLOY_RS_ID2_1.png
[PUT_EMPLOY_DB_ID2_1]: img/PUT_EMPLOY_DB_ID2_1.png
[PUT_EMPLOY_DB_ID2_2]: img/PUT_EMPLOY_DB_ID2_2.png
[PUT_EMPLOY_RS_ID2_2]: img/PUT_EMPLOY_RS_ID2_2.png
 
|         | Screen Shot               |
|---------|:-------------------------:|
| MongoDB | ![][PUT_EMPLOY_DB_ID2]    |
| Client  | ![][PUT_EMPLOY_RS_ID2_1]  |
| MongoDB | ![][PUT_EMPLOY_DB_ID2_1]  |
| Client  | ![][PUT_EMPLOY_RS_ID2_2]  |
| MongoDB | ![][PUT_EMPLOY_DB_ID2_2]  |

<div style="page-break-after: always;"></div>

##PUT /.../rest/project/n

###Success: update project Id=1 field

[PUT_PROJECT_DB_ID1]: img/PUT_PROJECT_DB_ID1.png
[PUT_PROJECT_RS_ID1_NAME]: img/PUT_PROJECT_RS_ID1_NAME.png
[PUT_PROJECT_DB_ID1_NAME]: img/PUT_PROJECT_DB_ID1_NAME.png
[PUT_PROJECT_RS_ID1_BUDGET]: img/PUT_PROJECT_RS_ID1_BUDGET.png
[PUT_PROJECT_DB_ID1_BUDGET]: img/PUT_PROJECT_DB_ID1_BUDGET.png
[PUT_PROJECT_RS_ID1_ALL]: img/PUT_PROJECT_RS_ID1_ALL.png
[PUT_PROJECT_DB_ID1_ALL]: img/PUT_PROJECT_DB_ID1_ALL.png
 
|         | Screen Shot     |
|---------|:---------------:|
| MongoDB | ![][PUT_PROJECT_DB_ID1]    |
| Client  | ![][PUT_PROJECT_RS_ID1_NAME]  |
| MongoDB | ![][PUT_PROJECT_DB_ID1_NAME]  |
| Client  | ![][PUT_PROJECT_RS_ID1_BUDGET]  |
| MongoDB | ![][PUT_PROJECT_DB_ID1_BUDGET]  |
| Client  | ![][PUT_PROJECT_RS_ID1_ALL]  |
| MongoDB | ![][PUT_PROJECT_DB_ID1_ALL]  |
 
<div style="page-break-after: always;"></div>
 
###Error: Project Id=4 does not exist error, project in JSON Id=1 does not match request URL
 
|                            | Screen Shot    |
|----------------------------|:--------------:|
| MongoDB                    | ![][06.S.DB.3] |
| Id not exist               | ![][06.E.RS.1] |
| MongoDB                    | ![][06.E.DB.2] |
| URL and JSON id not match  | ![][06.E.RS.2] |
| MongoDB                    | ![][06.E.DB.2] |

<div style="page-break-after: always;"></div>
 
[07.S.RS]: img/07.S.RS.png
[07.S.DB]: img/07.S.DB.png
[07.E.RS]: img/07.E.RS.png
[07.E.DB]: img/07.E.DB.png
 
##DELETE /.../rest/employee/n

###Success: Delete with Id=1
 
|            | Screen Shot      |
|------------|:----------------:|
| MongoDB    | ![][EM.MULTI]    |
| Delete     | ![][07.S.RS]     |
| MongoDB    | ![][07.S.DB]     |

###Error: Delete with non exist Id=9
 
|            | Screen Shot      |
|------------|:----------------:|
| MongoDB    | ![][07.S.DB]     |
| No records | ![][07.E.RS]     |
| MongoDB    | ![][07.E.DB]     |

<div style="page-break-after: always;"></div>
 
[08.S.RS]: img/08.S.RS.png
[08.S.DB]: img/08.S.DB.png
[08.E.RS]: img/08.E.RS.png
[08.E.DB]: img/08.E.DB.png
 
##DELETE /.../rest/project/n

###Success: Delete with Id=4
 
|            | Screen Shot      |
|------------|:----------------:|
| MongoDB    | ![][PR.MULTI]    |
| Delete     | ![][08.S.RS]     |
| MongoDB    | ![][08.S.DB]     |
 
<div style="page-break-after: always;"></div>
 
###Error: Delete with non exist Id=88
 
|            | Screen Shot      |
|------------|:----------------:|
| MongoDB    | ![][08.S.DB]     |
| No records | ![][08.E.RS]     |
| MongoDB    | ![][08.E.DB]     |

<div style="page-break-after: always;"></div>
 
[09.S.RS]: img/09.S.RS.png
[09.E.RS]: img/09.E.RS.png

##GET /.../rest/employee

###Success: Get all employee
 
|            | Screen Shot      |
|------------|:----------------:|
| MongoDB    | ![][EM.MULTI]    |
| No records | ![][09.S.RS]     |
 
<div style="page-break-after: always;"></div>

###Error: Not collection data
 
|            | Screen Shot      |
|------------|:----------------:|
| MongoDB    | ![][EM.CLEAN]    |
| No records | ![][09.E.RS]     |

<div style="page-break-after: always;"></div>

[10.S.RS]: img/10.S.RS.png
[10.E.RS]: img/10.E.RS.png

##GET /.../rest/project

###Success: Get all project
 
|            | Screen Shot      |
|------------|:----------------:|
| MongoDB    | ![][PR.MULTI]    |
| No records | ![][10.S.RS]     |

<div style="page-break-after: always;"></div>
 
###Error: Not collection data
 
|            | Screen Shot      |
|------------|:----------------:|
| MongoDB    | ![][PR.CLEAN]    |
| No records | ![][10.E.RS]     |

