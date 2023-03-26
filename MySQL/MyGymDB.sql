DROP DATABASE IF EXISTS MyGymDB;
CREATE DATABASE MyGymDB;
USE MyGymDB;

Create Table Users (
    userID INTEGER PRIMARY KEY AUTO_INCREMENT,
    user_name VARCHAR(250) NOT NULL,
    surname  VARCHAR(250) NOT NULL,
    email    VARCHAR (250) NOT NULL UNIQUE,
    pass VARCHAR (250) NOT NULL,    
    is_admin BOOLEAN DEFAULT FALSE
);
  
Create Table Courses (
    courseID INTEGER PRIMARY KEY AUTO_INCREMENT,
    course_name VARCHAR(250) NOT NULL,
    minutes_length  INTEGER  NOT NULL,
    course_date   DATETIME  DEFAULT CURRENT_TIMESTAMP,
    course_level VARCHAR (250) NOT NULL,
    max_users INTEGER  NOT NULL,
    unique_code VARCHAR (64) NOT NULL UNIQUE
);

CREATE TABLE Users_Courses(
	userRIF INTEGER NOT NULL,
    courseRIF INTEGER NOT NULL,
    FOREIGN KEY (userRIF) REFERENCES Users(userID) ON DELETE CASCADE,
    FOREIGN KEY (courseRIF) REFERENCES Courses(courseID) ON DELETE CASCADE,
    PRIMARY KEY(userRIF, courseRIF)
);

  INSERT INTO Users (user_name,surname,email,pass,is_admin) VALUES
  ("Valerio","Lucarelli","valerio@gmail.com","11234",1),
  ("Matteo","Benvenuti","matteo@gmail.com","11234",0);
  
  INSERT INTO Courses (course_name,minutes_length,course_level,max_users,unique_code) VALUES
  ("Zumba",120,"avanzato",25,"abcdefgh"),
  ("danza",120,"base",25,"hahdhahahh");

INSERT INTO Users_Courses(userRIF,courseRIF) VALUES
(1, 1),
(1, 2),
(2, 1),
(2, 2);

SELECT * FROM Users;
SELECT * FROM Courses;
 
 SELECT * 
	FROM Users
    JOIN Users_Courses ON Users.userID = Users_Courses.userRIF
    LEFT JOIN Courses ON Users_Courses.courseRIF = Courses.courseID;