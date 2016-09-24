<?php
$db_host = 'localhost';
$db_login = 'host1512310';
$db_pass = '2618323e';
$db_name = 'host1512310_rt';
////////////////////////////
///
// http://host/api.php?login={LOGIN}&pass={PASS}&method={METHOD_NAME};

function json_err($msg) {
	die("{'error':'".addslashes($msg)."'}");
}

if (!isset($_GET['login']) || !isset($_GET['pass']) || !isset($_GET['method']))
	json_err("Incorrect request!");

$login = $_GET['login'];
$pass = $_GET['pass'];
$method = $_GET['method'];

if (!mysql_connect($db_host, $db_login, $db_pass) || !mysql_select_db($db_name))
	json_err("Can't connect to DB!");

$check_auth = mysql_query("SELECT * FROM users WHERE login = '$login' AND password = '$pass'");
if (mysql_num_rows($check_auth) == 0)
	json_err("Incorrect login or password!");
$auth_id = mysql_result($check_auth, 0);

switch ($method) {
	case 'getUserInfo':
		$id = $_GET['id'] || $auth_id;
		$query = mysql_query("SELECT * FROM users WHERE user_id = '$id'");
		$rows = array();
		while($r = mysql_fetch_assoc($query)) {
		    $rows[] = $r;
		}
		die(json_encode($rows));
		break;
	case 'getUserAchievements':
		$id = $_GET['id'] || $auth_id;
		$query = mysql_query("SELECT * FROM user_achievements WHERE u_id = '$id'");
		$rows = array();
		while($r = mysql_fetch_assoc($query)) {
		    $rows[] = $r;
		}
		die(json_encode($rows));
		break;
	case 'getAchievementInfo':
		if (!isset($_GET['id']))
			json_err("$method must have the 'id' argument.");
		$id = $_GET['id'];
		$query = mysql_query("SELECT * FROM achievements WHERE a_id = '$id'");
		$rows = array();
		while($r = mysql_fetch_assoc($query)) {
		    $rows[] = $r;
		}
		die(json_encode($rows));
		break;	
	default:
		json_err("Incorrect method name!");
		break;
}
?>