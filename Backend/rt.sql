CREATE TABLE users (
	user_id INT UNSIGNED NOT NULL AUTO_INCREMENT,
	PRIMARY KEY (user_id),
	login VARCHAR(25) NOT NULL,
	password VARCHAR(255) NOT NULL,
	first_name VARCHAR(40) NOT NULL,
	last_name VARCHAR(40) NOT NULL,
	middle_name VARCHAR(40) NOT NULL,
	income DECIMAL(15,2) NOT NULL,
	xp INT UNSIGNED NOT NULL,
	score INT UNSIGNED NOT NULL
);
CREATE TABLE achievements (
	a_id INT UNSIGNED NOT NULL AUTO_INCREMENT,
	a_name TINYTEXT NOT NULL,
	a_description TEXT NOT NULL
);
CREATE TABLE user_achievements (
	action_id INT UNSIGNED NOT NULL AUTO_INCREMENT,
	PRIMARY KEY (action_id),
	action_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
	u_id INT UNSIGNED NOT NULL,
	a_id INT UNSIGNED NOT NULL
);

INSERT INTO users (user_id, login, password, first_name, last_name, middle_name, income, xp, score) 
VALUES ('1', 'root', 'root', 'Иван', 'Петров', 'Сидорович', '20000.00', '228', '1488');
INSERT INTO users (login, password, first_name, last_name, middle_name, income, xp, score) 
VALUES ('hello', 'world', 'John', 'Doe', 'William', '25000.00', '322', '1337');

INSERT INTO achievements (a_id, a_name, a_description)
VALUES 
(1, 'Hello World!', 'This is a test achievement. All hail the king!'),
(2, 'Are you veteran?', 'I am.');

INSERT INTO user_achievements (action_id, u_id, a_id)
VALUES
(1, 1, 1),
(2, 1, 2),
(3, 2, 2);