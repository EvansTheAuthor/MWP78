<?php
$conn = pg_connect("host=172.22.144.1 dbname=MWP user=postgres password=si@labOSN");

if (!$conn) {
    die("Koneksi gagal: " . pg_last_error());
}
?>