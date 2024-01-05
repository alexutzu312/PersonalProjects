<?php
$servername = "localhost";
$username = "root";
$password = "";
$dbname = "biblioteca";

// Create connection
$conn = new mysqli($servername, $username, $password, $dbname);

// Check connection
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}
echo "Connected successfully";
?>

<?php
$query = "SELECT p1.id_aut AS id_aut1, p2.id_aut AS id_aut2
		FROM Autor p1
		INNER JOIN Autor p2
		ON p1.id_carte = p2.id_carte
		WHERE p1.id_aut < p2.id_aut";
$result = mysqli_query($conn, $query);
?>


<!DOCTYPE html>
<html>
<head>
<style>
body {
  background-color: lightgray;
  text-align: center;
}

.text {
    margin: 50px;
    font-size: 20px;
}

table {
    position: absolute;
    top: 38%;
    left: 50%;
    transform: translate(-50%,-50%);
    text-align: center;
    display: none;
    font-size: 23px;
    border-collapse: collapse;
}

table, th, td {
    border: 1px solid black;
}

.show-button {
    margin: 50px;
}

.back-button {
    position: fixed;
    bottom: 20px;
    left: 50%;
    transform: translateX(-50%);
    font-size: 20px;
}
</style>
</head>
<body>

<div class="text">
    <p>4.b) Să se găsească perechi de autori (id_aut1, id_aut2) care sunt autori la aceleași cărți. O pereche este unică în rezultat.</p>
    <button class="show-button" id="show-button" onclick="showTable()">Show me</button>
    <table id="table">
        <tr>
            <th>id_aut1</th>
            <th>id_aut2</th>
        </tr>
        <?php while($row = mysqli_fetch_assoc($result)) { ?>
                <td><?php echo $row['id_aut1']; ?></td>
                <td><?php echo $row['id_aut2']; ?></td>
            </tr>
        <?php } ?>
    </table>
</div>

<div class="back-button">
    <button onclick="goBack()">Back</button>
</div>

<script>
    function showTable(){
        var button = document.querySelector("#show-button");
        var table = document.querySelector("#table");
        button.style.display = "none";
        table.style.display = "block";
    }

    function goBack() {
        window.history.back();
    }
</script>

