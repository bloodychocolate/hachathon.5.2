CREATE TABLE users (
	user_id INT UNSIGNED NOT NULL,
	first_name VARCHAR(40) NOT NULL,
	last_name VARCHAR(40) NOT NULL,
	middle_name VARCHAR(40) NOT NULL,
	income DECIMAL(15,2) NOT NULL,
	xp INT UNSIGNED NOT NULL,
	score INT UNSIGNED NOT NULL,
	achievements
);
CREATE TABLE achievements (
	a_id INT UNSIGNED NOT NULL,
	a_name TINYTEXT NOT NULL,
	a_description TEXT NOT NULL
);
CREATE TABLE user_achievements (
	action_id INT UNSIGNED NOT NULL,
	action_date TIMESTAMP NOT NULL,
	u_id INT UNSIGNED NOT NULL,
	a_id INT UNSIGNED NOT NULL
);