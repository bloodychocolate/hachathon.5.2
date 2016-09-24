<?php
$db_host = 'localhost';
$db_login = 'host1512310';
$db_pass = 'nope :^)';
$db_name = 'host1512310_rt'
////////////////////////////
///
// http://host/api.php?login={LOGIN}&pass={PASS}&method={METHOD_NAME};

function json_err($msg) {
	die("{'error':'".addslashes($msg)."'");
}

if (!isset($_GET['login']) || !isset($_GET['pass']) || !isset($_GET['method']))
	json_err("Incorrect request!");

$login = mysql_real_escape_string($_GET['login']);
$pass = mysql_real_escape_string($_GET['pass']);
$method = $_GET['method'];

if (!mysql_connect($db_host, $db_login, $db_pass) || !mysql_select_db($db_name))
	json_err("Can't connect to DB!");

$check_auth = mysql_query("SELECT * FROM $db_name WHERE login = '$login' AND pass = '$pass' LIMIT 1");
if (mysql_num_rows($check_auth) == 0)
	json_err("Incorrect login or password!");

switch ($method) {
	case 'getUserInfo':
		if (!isset($_GET['id']))
			json_err("Incorrect request!");
		$id = $_GET['id'];
		$query = mysql_query("SELECT * FROM $db_name WHERE user_id = '$id' ")
}

?>