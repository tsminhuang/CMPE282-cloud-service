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
 
 ```  
 use cmpe282tsungmin146
 ```

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

[PR.CLEAN]: img/PR.0.png
[PR.MULTI]: img/PR.3.png
[EM.CLEAN]: img/EM.0.png
[EM.MULTI]: img/EM.3.png

[01.S.RS]: img/01.S.RS.png
[01.E.RS]: img/01.E.RS.png

##GET /.../rest/employee/m

###Success: Query with exist Id=1
 
|         | Screen Shot      |
|---------|:----------------:|
| MongoDB | ![][EM.MULTI]    |
| MongoDB | ![][01.S.RS]     |
 
<div style="page-break-after: always;"></div>

###Error: Query with non exist Id=5

|         | Screen Shot      |
|---------|:----------------:|
| MongoDB | ![][EM.MULTI]    |
| MongoDB | ![][01.E.RS]     |

<div style="page-break-after: always;"></div>
 
[02.S.RS]: img/02.S.RS.png
[02.E.RS]: img/02.E.RS.png

##GET /.../rest/project/n

###Success: Query with exist Id=4
 
|         | Screen Shot      |
|---------|:----------------:|
| MongoDB | ![][PR.MULTI]    |
| MongoDB | ![][02.S.RS]     |

<div style="page-break-after: always;"></div>

###Error: Query with non exist Id=999

|         | Screen Shot      |
|---------|:----------------:|
| MongoDB | ![][PR.MULTI]    |
| MongoDB | ![][02.E.RS]     |

<div style="page-break-after: always;"></div>

[03.S.RS]: img/03.S.RS.png
[03.S.DB]: img/03.S.DB.png
[03.E.RS]: img/03.E.RS.png
[03.E.DB]: img/03.E.DB.png

##POST /.../rest/employee

###Success: Create employee based JSON context
 
|         | Screen Shot      |
|---------|:----------------:|
| MongoDB | ![][EM.CLEAN]    |
| Create  | ![][03.S.RS]     |
| MongoDB | ![][03.S.DB]     |

<div style="page-break-after: always;"></div>
 
###Error: Create employee with the same Id=99
 
|          | Screen Shot   |
|----------|:-------------:|
| MongoDB  | ![][03.S.DB]  |
| Conflict | ![][03.E.RS]  |    
| MongoDB  | ![][03.E.DB]  |
 
<div style="page-break-after: always;"></div>
 
[04.S.RS]: img/04.S.RS.png
[04.S.DB]: img/04.S.DB.png
[04.E.RS]: img/04.E.RS.png
[04.E.DB]: img/04.E.DB.png

##POST /.../rest/project

###Success: Create project based JSON context
 
|         | Screen Shot      |
|---------|:----------------:|
| MongoDB | ![][PR.CLEAN]    |
| Create  | ![][04.S.RS]     |
| MongoDB | ![][04.S.DB]     |

<div style="page-break-after: always;"></div>
 
###Error: Create project with the same Id=1
 
|         | Screen Shot   |
|---------|:-------------:|
| MongoDB | ![][04.S.DB]  |
| Create  | ![][04.E.RS]  |       
| MongoDB | ![][04.E.DB]  |
 
<div style="page-break-after: always;"></div>
 
[05.S.RS.1]: img/05.S.RS.1.png
[05.S.DB.1]: img/05.S.DB.1.png
[05.S.RS.2]: img/05.S.RS.2.png
[05.S.DB.2]: img/05.S.DB.2.png
[05.S.RS.3]: img/05.S.RS.3.png
[05.S.DB.3]: img/05.S.DB.3.png
[05.E.RS.1]: img/05.E.RS.1.png
[05.E.DB.1]: img/05.E.DB.1.png
[05.E.RS.2]: img/05.E.RS.2.png
[05.E.DB.2]: img/05.E.DB.2.png

##PUT /.../rest/employee/n

###Success: Demo employee Id=99 partial update
 
|                   | Screen Shot     |
|-------------------|:---------------:|
| MongoDB           | ![][03.E.DB]    |
| Update fisrt name | ![][05.S.RS.1]  |
| MongoDB           | ![][05.S.DB.1]  |
| Update last name  | ![][05.S.RS.2]  |
| MongoDB           | ![][05.S.DB.2]  |
| Update all        | ![][05.S.RS.3]  |
| MongoDB           | ![][05.S.DB.3]  |
 
<div style="page-break-after: always;"></div>
 
###Error: Employee Id=4 does not exist error, employ in JSON Id=10 does not match request URL
 
|                            | Screen Shot    |
|----------------------------|:--------------:|
| MongoDB                    | ![][05.S.DB.3] |
| Id not exist               | ![][05.E.RS.1] |
| MongoDB                    | ![][05.E.DB.1] |
| URL and JSON id not match  | ![][05.E.RS.2] |
| MongoDB                    | ![][05.E.DB.2] |

[06.S.RS.1]: img/06.S.RS.1.png
[06.S.DB.1]: img/06.S.DB.1.png
[06.S.RS.2]: img/06.S.RS.2.png
[06.S.DB.2]: img/06.S.DB.2.png
[06.S.RS.3]: img/06.S.RS.3.png
[06.S.DB.3]: img/06.S.DB.3.png
[06.E.RS.1]: img/06.E.RS.1.png
[06.E.DB.1]: img/06.E.DB.1.png
[06.E.RS.2]: img/06.E.RS.2.png
[06.E.DB.2]: img/06.E.DB.2.png

##PUT /.../rest/project/n

###Success: Demo project Id=1 partial update
 
|               | Screen Shot     |
|---------------|:---------------:|
| MongoDB       | ![][04.E.DB]    |
| Update name   | ![][06.S.RS.1]  |
| MongoDB       | ![][06.S.DB.1]  |
| Update budget | ![][06.S.RS.2]  |
| MongoDB       | ![][06.S.DB.2]  |
| Update all    | ![][06.S.RS.3]  |
| MongoDB       | ![][06.S.DB.3]  |

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

