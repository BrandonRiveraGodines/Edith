<?php
session_start();
require_once 'datos/DB_Functions.php';
$db = new DB_Functions();

// json response array
$response = array("error" => FALSE);

if(isset($_POST['iddueno'])){
	// receiving the post params
	$iddueno = $_POST['iddueno'];

	// Get the user by email and password
	$user = $db->getDataByIddueno($iddueno);

	if($user != false){
		$response["error"] = FALSE;
        $response["user"]["contacto_1"] = $user["contacto_1"];
        $response["user"]["telefono_1"] = $user["telefono_1"];
        $response["user"]["contacto_2"] = $user["contacto_2"];
        $response["user"]["telefono_2"] = $user["telefono_2"];
        $response["user"]["contacto_3"] = $user["contacto_3"];
        $response["user"]["telefono_3"] = $user["telefono_3"];
        $response["user"]["contacto_mensaje"] = $user["contacto_mensaje"];
        $response["user"]["telefono_mensaje"] = $user["telefono_mensaje"];
        $response["user"]["mensaje"] = $user["mensaje"];
        echo json_encode($response);
	} else {
		$response["error"] = TRUE;
        $response["error_msg"] = "Login credentials are wrong. Please try again!";
        echo json_encode($response);
	}
} else {
	$response["error"] = TRUE;
    $response["error_msg"] = "Required parameters email or password is missing!";
    echo json_encode($response);
}
?>