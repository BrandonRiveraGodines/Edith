<?php
	//session_start();
	if($_SERVER['REQUEST_METHOD']=='POST'){
	
		$con = mysqli_connect("localhost", "root", "", "edith");
		$unique_id = $_POST['unique_id'];
		$contacto_1 = $_POST['contacto_1'];
		$telefono_1 = $_POST['telefono_1'];
		$contacto_2 = $_POST['contacto_2'];
		$telefono_2 = $_POST['telefono_2'];
		$contacto_3 = $_POST['contacto_3'];
		$telefono_3 = $_POST['telefono_3'];
		$contacto_mensaje = $_POST['contacto_mensaje'];
		$telefono_mensaje = $_POST['telefono_mensaje'];
		$mensaje = $_POST['mensaje'];

		$sql_query = "UPDATE users SET contacto_1 = '$contacto_1', telefono_1 = '$telefono_1', contacto_2 = '$contacto_2', telefono_2 = '$telefono_2', contacto_3 = '$contacto_3', telefono_3 = '$telefono_3', contacto_mensaje = '$contacto_mensaje', telefono_mensaje = '$telefono_mensaje', mensaje = '$mensaje' WHERE unique_id ='$unique_id'";

		if(mysqli_query($con, $sql_query)){
			echo 'Actualización realizada';
		} else {
			echo "Error, quien sabe porque";
		}
	}
	mysqli_close($con);
?>