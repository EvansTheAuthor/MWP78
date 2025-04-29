<?
session_start();

if (!isset($_SESSION['username'])) {
    header("Location: main.php");
    exit();
}

include 'connection.php';

$username = $_SESSION['username'];
$content = isset($_POST['content']) ? trim($_POST['isi']) : '';
$postId = isset($_POST['id']) ? $_POST['postId'] : '';

if (!empty($content) && !empty($postId)) {
    $query = "INSERT INTO reply (postId, username, content) VALUES ($1, $2, $3)";
    $result = pg_query_params($conn, $query, array($postId, $username, $content));

    if ($result) {
        echo 'success';
    } else {
        echo 'failed';
    }
    
} else {
    echo 'empty';
}
?>