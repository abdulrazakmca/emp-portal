CREATE TABLE employee_registration (
	id uuid NOT NULL PRIMARY KEY,
	firstname varchar(500),
	lastname varchar(500), 
	gender varchar(50),
	dateofbirth timestamp
);
