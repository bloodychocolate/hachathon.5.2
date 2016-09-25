CREATE TABLE users (
	user_id INT UNSIGNED NOT NULL AUTO_INCREMENT,
	PRIMARY KEY (user_id),
	login VARCHAR(25) NOT NULL,
	password VARCHAR(255) NOT NULL,
	first_name VARCHAR(40) NOT NULL,
	last_name VARCHAR(40) NOT NULL,
	middle_name VARCHAR(40) NOT NULL,
	income DECIMAL(15,2) DEFAULT 0.00 NOT NULL,
	xp INT UNSIGNED DEFAULT 0 NOT NULL,
	new_clients INT UNSIGNED DEFAULT 0 NOT NULL,
	in_team INT DEFAULT 1 NOT NULL,
	is_admin BOOLEAN DEFAULT 0 NOT NULL
);
CREATE TABLE achievements (
	a_id INT UNSIGNED NOT NULL AUTO_INCREMENT,
	PRIMARY KEY (a_id),
	a_name TINYTEXT NOT NULL,
	a_description TEXT NOT NULL
);
CREATE TABLE user_achievements (
	action_id INT UNSIGNED NOT NULL AUTO_INCREMENT,
	PRIMARY KEY (action_id),
	action_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
	u_id INT UNSIGNED NOT NULL,
	a_id INT UNSIGNED NOT NULL,
	xp INT UNSIGNED DEFAULT 100 NOT NULL
);
CREATE TABLE teams (
	team_id INT UNSIGNED NOT NULL AUTO_INCREMENT,
	PRIMARY KEY (team_id),
	team_name VARCHAR(30) NOT NULL,
	team_moder INT UNSIGNED NOT NULL
);
CREATE TABLE battles (
	battle_id INT UNSIGNED NOT NULL AUTO_INCREMENT,
	PRIMARY KEY (battle_id),
	sender INT UNSIGNED NOT NULL,
	receiver INT UNSIGNED NOT NULL,
	start_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
	end_date TIMESTAMP NOT NULL
);
CREATE TABLE quests (
	quest_id INT UNSIGNED NOT NULL AUTO_INCREMENT,
	PRIMARY KEY (quest_id),
	user_id INT UNSIGNED NOT NULL,
	brief TEXT NOT NULL,
	xp INT UNSIGNED DEFAULT 100 NOT NULL,
	completed SMALLINT(1) DEFAULT 0 NOT NULL
);

INSERT INTO users (user_id, login, password, first_name, last_name, middle_name, income, xp, new_clients, in_team, is_admin) 
VALUES ('1', 'root', 'root', 'Иван', 'Петров', 'Сидорович', '20000.00', '228', '1488', '1', '1');
INSERT INTO users (login, password, first_name, last_name, middle_name, income, xp, new_clients, in_team) 
VALUES ('hello', 'world', 'John', 'Doe', 'William', '25000.00', '322', '1337', '1');

INSERT INTO achievements (a_id, a_name, a_description)
VALUES 
(1, 'Hello World!', 'This is a test achievement. All hail the king!'),
(2, 'Are you veteran?', 'I am.');

INSERT INTO user_achievements (action_id, u_id, a_id)
VALUES
(1, 1, 1),
(2, 1, 2),
(3, 2, 2);

INSERT INTO teams (team_id, team_name, team_moder)
VALUES ('1','Отдел зверушек','1');