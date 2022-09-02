--<ScriptOptions statementTerminator=";"/>

CREATE TABLE users (
	USER_ID INTEGER UNSIGNED NOT NULL,
	EMAIL_ID VARCHAR(50) NOT NULL,
	FIRST_NAME VARCHAR(45),
	LAST_NAME VARCHAR(45),
	INITIALS VARCHAR(15),
	SUPERVISOR_ID INT,
	ACTIVE_IND BIT NOT NULL,
	Dob DATETIME,
	Last_Login_Date DATETIME,
	MO_ID INT,
	pwd VARCHAR(40),
	PRIMARY KEY (USER_ID)
) ENGINE=InnoDB;
