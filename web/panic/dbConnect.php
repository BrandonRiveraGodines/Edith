<?php
	define('HOST', 'localhost');
	define('USER', 'root');
	define('PASS', '');
	define('DB', 'edith');
	$con = mysqli_connect(HOST, USER, PASS, DB) or die ('No se puede conectar a la base de datos');
	if($con){
		echo "conexión exitosa";
	}
	
?>