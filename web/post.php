<?
session_start();

if(!isset($_SESSION['username'])){
    header("Location: index.php");
    exit();
}

include 'connection.php';

if($_SERVER['REQUEST_METHOD'] === 'POST'){
    $username = $_SESSION['username'];
    $content = trim($_POST['content']);

    if (!empty($content)) {
        $stmtName = "insert_post";
        $sql = "INSERT INTO post (username, content) VALUES ($1, $2)";
    
        pg_prepare($conn, $stmtName, $sql);
        $result = pg_execute($conn, $stmtName, array($username, $content));
    
        if ($result) {
            echo 'succes';
        } else {
            echo 'failed';
        }
    } else {
        echo 'empty';
    }
}
?>