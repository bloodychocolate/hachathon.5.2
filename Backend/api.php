<?php
$db_host = 'localhost';
$db_login = 'host1512310';
$db_pass = 'nope :^)';
$db_name = 'host1512310_rt'
////////////////////////////
function json_err($msg) {
	die("{'error':'".addslashes($msg)."'");
}

if (!isset($_GET['login']) || !isset($_GET['pass']) || !isset($_GET['query']))
	json_err("Incorrect request!");

$login = mysql_real_escape_string($_GET['login']);
$pass = mysql_real_escape_string($_GET['pass']);
$query = $_GET['pass'];

if (!mysql_connect($db_host, $db_login, $db_pass) || !mysql_select_db($db_name))
	json_err("Can't connect to DB!");

$check_auth = mysql_query("SELECT * FROM $db_name WHERE login = '$login' AND pass = '$pass' LIMIT 1");
if (mysql_num_rows($result) == 0)
	json_err("Incorrect login or password!");

switch ($query) {
	case 'getUserInfo':
		if (!isset($_GET['id']))
			json_err("Incorrect request!");
		
}

?>