
Make sure git and maven installed and set as envrionment paths.

Step 1: checkout development branch

 git clone -b development https://github.com/abdulrazakmca/emp-portal.git

Step 2: Run below command
 mvn spring-boot:run


Post Operation
==================
http://localhost:8080/emp-portal/registration
    ========RequestBody==========
{
	"lastname":"lastname",
    "firstname":"firstname",
	"dateofbirth":"2015-01-03",
	"gender":"male",
	"department":"police"
}

Get Opeation
===================
http://localhost:8080/emp-portal/employee-list
