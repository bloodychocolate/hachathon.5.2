<?php
$db_host = 'localhost';
$db_login = 'host1512310';
$db_pass = '2618323e';
$db_name = 'host1512310_rt';
////////////////////////////
///
// http://host/api.php?login={LOGIN}&pass={PASS}&method={METHOD_NAME};

function json_err($code, $msg) {
	die("{'error':'".addslashes($msg)."','err_code':'$code'}");
}
function json_return($str) {
	die('{"response":'.$str.'}');
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
$user_info = mysql_fetch_row($check_auth);
$auth_id = $user_info[0];
$is_admin = $user_info[10];


switch ($method) {
	case 'getUsers':
		$query = mysql_query("SELECT * FROM users");
		$rows = array();
		while($r = mysql_fetch_assoc($query)) {
		    $rows[] = $r;
		}
		json_return(json_encode($rows));
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
		json_return(json_encode($rows[0]));
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
		json_return(json_encode($rows));
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
		json_return(json_encode($rows[0]));
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
		json_return(json_encode($rows));
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
		json_return('"0"');
		break;
	case 'getQuest': 
		$idd = $_GET['id'];
		if (!isset($_GET['id'])) 
			$idd = $auth_id; //ЕБУЧАЯ БЫДЛЯЦКАЯ ПЫХА!!!
		$query = mysql_query("SELECT * FROM quests WHERE user_id = '$idd'");
		$rows = array();
		while($r = mysql_fetch_assoc($query)) {
		    $rows[] = $r;
		}
		json_return(json_encode($rows));
	case 'addQuest':
		if (!$is_admin)
			json_err(2,'Permission denied!');
		if (!isset($_GET['user']))
			json_err(3,"$method must have the 'user' argument.");
		if (!isset($_GET['brief']))
			json_err(3,"$method must have the 'brief' argument.");
		$xp = $_GET['xp'];
		if (!isset($_GET['xp']))
			$xp = 100;
		$user = $_GET['user'];
		$brief = mysql_real_escape_string($_GET['brief']);
		$query = mysql_query("INSERT INTO quests (user_id,brief,xp) VALUES ('$user','$brief','$xp')");
		json_return('"0"');
		break;
	case 'finishQuest':
		if (!$is_admin)
			json_err(2,'Permission denied!');
		if (!isset($_GET['id']))
			json_err(3,"$method must have the 'id' argument.");
		$q_id = $_GET['id'];
		$kal = mysql_query("SELECT xp, user_id FROM quests WHERE quest_id = '$q_id'");
		$info = mysql_fetch_row($kal);
		$xp = $info[0];
		$uid = $info[1];
		echo $xp. ' '. $uid;
		$query = mysql_query("UPDATE users SET 'xp' = 'xp' + $xp WHERE user_id = '$uid'");
		$query = mysql_query("DELETE FROM quests WHERE quest_id = '$q_id'");
		json_return('"0"');
		break;
	case 'changeIncome':
		if (!$is_admin)
			json_err(2,'Permission denied!');
		if (!isset($_GET['id']))
			json_err(3,"$method must have the 'id' argument.");
		if (!isset($_GET['income']))
			json_err(3,"$method must have the 'income' argument.");
		$id = $_GET['id'];
		$zp = $_GET['income'];
		$kal = mysql_query("UPDATE users SET income = '$zp' WHERE user_id = '$id'");
		json_return('"0"');
	default:
		json_err(3,"Incorrect method name!");
		break;
}
?>