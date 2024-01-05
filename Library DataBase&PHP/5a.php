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
$query = "SELECT *
		FROM Carte
		WHERE titlu IN ('Shogun')";
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
    font-size: 22px;
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
    <p>5.a) Să se găsească detaliile cărților care au genul cărții cu titlul ’Shogun’.</p>
    <button class="show-button" id="show-button" onclick="showTable()">Show me</button>
    <table id="table">
        <tr>
            <th>id_carte</th>
            <th>titlu</th>
			<th>nr_pagini</th>
			<th>nr_exemplare</th>
			<th>gen</th>
        </tr>
        <?php while($row = mysqli_fetch_assoc($result)) { ?>
                <td><?php echo $row['id_carte']; ?></td>
				<td><?php echo $row['titlu']; ?></td>
				<td><?php echo $row['nr_pagini']; ?></td>
				<td><?php echo $row['nr_exemplare']; ?></td>
                <td><?php echo $row['gen']; ?></td>
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

