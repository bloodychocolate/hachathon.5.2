<?php
$db_host = 'localhost';
$db_login = 'host1512310';
$db_pass = '2618323e';
$db_name = 'host1512310_rt';
////////////////////////////
///
// http://host/api.php?login={LOGIN}&pass={PASS}&method={METHOD_NAME};

function json_err($code, $msg) {
	die("[{'error':'".addslashes($msg)."','err_code':'$code'}]");
}

if (!isset($_GET['login']) || !isset($_GET['pass']) || !isset($_GET['method']))
	json_err(1,"Incorrect request!");

$login = $_GET['login'];
$pass = $_GET['pass'];
$method = $_GET['method'];

if (!mysql_connect($db_host, $db_login, $db_pass) || !mysql_select_db($db_name))
	json_err(1,"Can't connect to DB!");

$check_auth = mysql_query("SELECT * FROM users WHERE login = '$login' AND password = '$pass'");
if (mysql_num_rows($check_auth) == 0)
	json_err(2,"Incorrect login or password!");
$auth_id = mysql_result($check_auth, 0);

switch ($method) {
	case 'getUsers':
		$query = mysql_query("SELECT * FROM users");
		$rows = array();
		while($r = mysql_fetch_assoc($query)) {
		    $rows[] = $r;
		}
		die(json_encode($rows));
		break;
	case 'getUserInfo':
		$idd = $_GET['id'];
		if (!isset($_GET['id'])) 
			$idd = $auth_id; //ЕБУЧАЯ БЫДЛЯЦКАЯ ПЫХА!!!
		$query = mysql_query("SELECT * FROM users WHERE user_id = '$idd'");
		$rows = array();
		while($r = mysql_fetch_assoc($query)) {
		    $rows[] = $r;
		}
		die(json_encode($rows));
		break;
	case 'getUserAchievements':
		$idd = $_GET['id'];
		if (!isset($_GET['id'])) 
			$idd = $auth_id; //ЕБУЧАЯ БЫДЛЯЦКАЯ ПЫХА!!!
		$query = mysql_query("SELECT * FROM user_achievements WHERE u_id = '$idd'");
		$rows = array();
		while($r = mysql_fetch_assoc($query)) {
		    $rows[] = $r;
		}
		die(json_encode($rows));
		break;
	case 'getAchievementInfo':
		if (!isset($_GET['id']))
			json_err(3,"$method must have the 'id' argument.");
		$idd = $_GET['id'];
		$query = mysql_query("SELECT * FROM achievements WHERE a_id = '$idd'");
		$rows = array();
		while($r = mysql_fetch_assoc($query)) {
		    $rows[] = $r;
		}
		die(json_encode($rows));
		break;	
	case 'getUserBattles':
		$idd = $_GET['id'];
		if (!isset($_GET['id'])) 
			$idd = $auth_id; //ЕБУЧАЯ БЫДЛЯЦКАЯ ПЫХА!!!
		$query = mysql_query("SELECT * FROM battles WHERE sender = '$idd' OR receiver = '$idd'");
		$rows = array();
		while($r = mysql_fetch_assoc($query)) {
		    $rows[] = $r;
		}
		die(json_encode($rows));
		break;
	case 'sendBattle':
		if (!isset($_GET['id']))
			json_err(3,"$method must have the 'id' argument.");
		$idd = $_GET['id'];
		$check1 = mysql_query("SELECT * FROM battles WHERE sender = '$auth_id' OR receiver = '$auth_id'");
		$check2 = mysql_query("SELECT * FROM battles WHERE sender = '$idd' OR receiver = '$idd'");
		if (mysql_num_rows($check1)+mysql_num_rows($check2) > 0)
			json_err(4,"Can't start the battle, you or your opponent is already in battle.");
		$query = mysql_query("INSERT INTO battles (sender, receiver, end_date) VALUES ('$auth_id','$idd',now() + interval 1 month)");
		die("[{'result':'success'}]");
		break;

	default:
		json_err(3,"Incorrect method name!");
		break;
}
?>