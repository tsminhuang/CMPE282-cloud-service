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
No need. Spring automatiically generate DB with assigned name. Just make sure MongoDB daemon is running.

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
For current implementation, use annotation **@Validated** and **@Valid** to check URL parameter and JSON content. It avoids a lot of condition statement in business logic. However, the exception just direct throw by validation may cause hard to figure out the error message. 
The field check in JSON content does not proper implement to check which field is optional or mandatory.

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

###Success: Get employee Id=1

[GET_EMPLOY_DB_ID1]: img/GET_EMPLOY_DB_ID1.png
[GET_EMPLOY_RS_ID1]: img/GET_EMPLOY_RS_ID1.png

|         | Screen Shot            |
|---------|:----------------------:|
| MongoDB | ![][GET_EMPLOY_DB_ID1] |
| Clent   | ![][GET_EMPLOY_RS_ID1] |
 
<div style="page-break-after: always;"></div>

###Error: Get employee Id=2

[GET_EMPLOY_DB_ID2]: img/GET_EMPLOY_DB_ID2.png
[GET_EMPLOY_RS_ID2]: img/GET_EMPLOY_RS_ID2.png

|         | Screen Shot            |
|---------|:----------------------:|
| MongoDB | ![][GET_EMPLOY_DB_ID2] |
| Clent   | ![][GET_EMPLOY_RS_ID2] |

<div style="page-break-after: always;"></div>
 
##GET /.../rest/project/n

###Success: Get project Id=1

[GET_PROJECT_DB_ID1]: img/GET_PROJECT_DB_ID1.png
[GET_PROJECT_RS_ID1]: img/GET_PROJECT_RS_ID1.png
 
|         | Screen Shot             |
|---------|:-----------------------:|
| MongoDB | ![][GET_PROJECT_DB_ID1] |
| Clent   | ![][GET_PROJECT_RS_ID1] |

<div style="page-break-after: always;"></div>

###Error: Get project Id=2

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

###Success: Update employee Id=1 field

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
 
###Error: Updte employee Id=2, employee Id does not match Id in JSON content
 
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

###Success: Update project Id=1 field

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
 
###Error: Update project Id=2, project Id does not match Id in JSON content
 
[PUT_PROJECT_DB_ID2]: img/PUT_PROJECT_DB_ID2.png
[PUT_PROJECT_RS_ID2_1]: img/PUT_PROJECT_RS_ID2_1.png
[PUT_PROJECT_DB_ID2_1]: img/PUT_PROJECT_DB_ID2_1.png
[PUT_PROJECT_DB_ID2_2]: img/PUT_PROJECT_DB_ID2_2.png
[PUT_PROJECT_RS_ID2_2]: img/PUT_PROJECT_RS_ID2_2.png
 
|         | Screen Shot                |
|---------|:--------------------------:|
| MongoDB | ![][PUT_PROJECT_DB_ID2]    |
| Client  | ![][PUT_PROJECT_RS_ID2_1]  |
| MongoDB | ![][PUT_PROJECT_DB_ID2_1]  |
| Client  | ![][PUT_PROJECT_DB_ID2_2]  |
| MongoDB | ![][PUT_PROJECT_RS_ID2_2]  |

<div style="page-break-after: always;"></div>
 
##DELETE /.../rest/employee/n

###Success: Delete emplyee Id=1

[DEL_EMPLOY_DB_ID1]: img/DEL_EMPLOY_DB_ID1.png
[DEL_EMPLOY_RS_ID1_1]: img/DEL_EMPLOY_RS_ID1_1.png
[DEL_EMPLOY_DB_ID1_1]: img/DEL_EMPLOY_DB_ID1_1.png
 
|         | Screen Shot              |
|---------|:------------------------:|
| MongoDB | ![][DEL_EMPLOY_DB_ID1]   |
| Client  | ![][DEL_EMPLOY_RS_ID1_1] |
| MongoDB | ![][DEL_EMPLOY_DB_ID1_1] |

<div style="page-break-after: always;"></div>

###Error: Delete employee Id=2
 
[DEL_EMPLOY_DB_ID2]: img/DEL_EMPLOY_DB_ID2.png
[DEL_EMPLOY_RS_ID2_1]: img/DEL_EMPLOY_RS_ID2_1.png
[DEL_EMPLOY_DB_ID2_1]: img/DEL_EMPLOY_DB_ID2_1.png
 
|         | Screen Shot              |
|---------|:------------------------:|
| MongoDB | ![][DEL_EMPLOY_DB_ID2]   |
| Client  | ![][DEL_EMPLOY_RS_ID2_1] |
| MongoDB | ![][DEL_EMPLOY_DB_ID2_1] |

<div style="page-break-after: always;"></div>
 
##DELETE /.../rest/project/n

###Success: Delete project Id=1
 
[DEL_PROJECT_DB_ID1]: img/DEL_PROJECT_DB_ID1.png
[DEL_PROJECT_RS_ID1_1]: img/DEL_PROJECT_RS_ID1_1.png
[DEL_PROJECT_DB_ID1_1]: img/DEL_PROJECT_DB_ID1_1.png
 
|         | Screen Shot               |
|---------|:-------------------------:|
| MongoDB | ![][DEL_PROJECT_DB_ID1]   |
| Client  | ![][DEL_PROJECT_RS_ID1_1] |
| MongoDB | ![][DEL_PROJECT_DB_ID1_1] |
 
<div style="page-break-after: always;"></div>
 
###Error: Delete project Id=2
 
[DEL_PROJECT_DB_ID2]: img/DEL_PROJECT_DB_ID2.png
[DEL_PROJECT_RS_ID2_1]: img/DEL_PROJECT_RS_ID2_1.png
[DEL_PROJECT_DB_ID2_1]: img/DEL_PROJECT_DB_ID2_1.png
 
|         | Screen Shot               |
|---------|:-------------------------:|
| MongoDB | ![][DEL_PROJECT_DB_ID2]   |
| Client  | ![][DEL_PROJECT_RS_ID2_1] |
| MongoDB | ![][DEL_PROJECT_DB_ID2_1] |

<div style="page-break-after: always;"></div>

##GET /.../rest/employee

###Success: Get all employee

[GET_EMPLOY_DB_ALL]: img/GET_EMPLOY_DB_ALL.png
[GET_EMPLOY_RS_ALL]: img/GET_EMPLOY_RS_ALL.png
 
|         | Screen Shot            |
|---------|:----------------------:|
| MongoDB | ![][GET_EMPLOY_DB_ALL] |
| Client  | ![][GET_EMPLOY_RS_ALL] |
 
<div style="page-break-after: always;"></div>

###Error: Not any employee data

[GET_EMPLOY_DB_EMPTY]: img/GET_EMPLOY_DB_EMPTY.png
[GET_EMPLOY_RS_EMPTY]: img/GET_EMPLOY_RS_EMPTY.png
 
|         | Screen Shot            |
|---------|:----------------------:|
| MongoDB | ![][GET_EMPLOY_DB_EMPTY] |
| Client  | ![][GET_EMPLOY_RS_EMPTY] |

<div style="page-break-after: always;"></div>

##GET /.../rest/project

###Success: Get all project
 
[GET_PROJECT_DB_ALL]: img/GET_PROJECT_DB_ALL.png
[GET_PROJECT_RS_ALL]: img/GET_PROJECT_RS_ALL.png
 
|         | Screen Shot             |
|---------|:-----------------------:|
| MongoDB | ![][GET_PROJECT_DB_ALL] |
| Client  | ![][GET_PROJECT_RS_ALL] |

<div style="page-break-after: always;"></div>
 
###Error: Not any project data
 
[GET_PROJECT_DB_EMPTY]: img/GET_PROJECT_DB_EMPTY.png
[GET_PROJECT_RS_EMPTY]: img/GET_PROJECT_RS_EMPTY.png
 
|         | Screen Shot               |
|---------|:-------------------------:|
| MongoDB | ![][GET_PROJECT_DB_EMPTY] |
| Client  | ![][GET_PROJECT_RS_EMPTY] |

