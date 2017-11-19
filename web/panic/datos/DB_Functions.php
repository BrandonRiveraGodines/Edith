<?php
/**
 * @author Ravi Tamada
 * @link http://www.androidhive.info/2012/01/android-login-and-registration-with-php-mysql-and-sqlite/ Complete tutorial
 */

class DB_Functions {

    private $conn;

    // constructor
    function __construct() {
        require_once 'DB_Connect.php';
        // connecting to database
        $db = new Db_Connect();
        $this->conn = $db->connect();
    }
    
    /**
     * Get dog by iddueno
     */
    function getDataByIddueno($iddueno) {

        $stmt = $this->conn->prepare("SELECT * FROM users WHERE unique_id = ?");

        $stmt->bind_param("s", $iddueno);

        if ($stmt->execute()) {
            $user = $stmt->get_result()->fetch_assoc();
            $stmt->close();

            return $user;
        } else {
            return NULL;
        }
    }
}

?>