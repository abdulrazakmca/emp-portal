
Make sure git and maven installed and set as envrionment paths.

Step 1: checkout development branch

 git clone -b development https://github.com/abdulrazakmca/emp-portal.git

Step 2: Run scripts
using postgres - add username/password as in your local installation

create database emp-portal;

check table creation scripts in resource folder.

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
