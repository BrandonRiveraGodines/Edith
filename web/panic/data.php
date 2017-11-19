<?php
	if($_SERVER['REQUEST_METHOD'] == 'POST'){
		$contacto_1 = $_POST['contacto_1'];
		$telefono_1 = $_POST['telefono_1'];
		$contacto_2 = $_POST['contacto_2'];
		$telefono_2 = $_POST['telefono_2'];
		$contacto_3 = $_POST['contacto_3'];
		$telefono_3 = $_POST['telefono_3'];
		$contacto_mensaje = $_POST['contacto_mensaje'];
		$telefono_mensaje = $_POST['telefono_mensaje'];
		$mensaje = $_POST['mensaje'];
		$iduser = $_POST['iduser'];

		echo $contacto_1;
		echo $telefono_1;
		echo $contacto_2;
		echo $telefono_2;
		echo $contacto_3;
		echo $telefono_3;
		echo $contacto_mensaje;
		echo $telefono_mensaje;
		echo $mensaje;
		echo $iduser;

		require_once('dbConnect.php');
		$sql = "SELECT id FROM datos ORDER BY id ASC";
		echo $sql;

		$res = mysqli_query($con, $sql);

		if($res){
			echo "Funcionó";
		}
		

		$id = 1;

		while($row = mysqli_fetch_array($res)){
			$id = $row['id'];
			echo "Funcionó";
		}
		$sql = "INSERT INTO datos (id, contacto_1, telefono_1, contacto_2, telefono_2, contacto_3, telefono_3, contacto_mensaje, telefono_mensaje, mensaje, iduser) VALUES ('$id', '$contacto_1', '$telefono_1','$contacto_2','$telefono_2','$contacto_3','$telefono_3,'$contacto_mensaje','$telefono_mensaje','$mensaje','$iduser')";
		echo $sql;

		$done = mysqli_query($con, $sql);

		if($done){
			echo "Exito alv";
		}
		mysqli_close($con);
	} else {
		echo "Error";
	}
?>