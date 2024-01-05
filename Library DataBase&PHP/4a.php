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
$query = "SELECT Persoana.nume, Persoana.adresa, Persoana.telefon
		FROM Imprumut
		INNER JOIN Persoana
		ON Persoana.id_pers = Imprumut.id_imp 
		INNER JOIN Carte
		ON Imprumut.id_carte = Carte.id_carte 
		WHERE Carte.gen = 'Copii' AND CURDATE() - Imprumut.datai < 14";
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
    font-size: 25px;
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
    <p>4.a) Să se găsească numele, adresa și numărul de telefon pentru persoanele ce au împrumutat cărți din genul ’Copii’ în ultimele 14 zile.</p>
    <button class="show-button" id="show-button" onclick="showTable()">Show me</button>
    <table id="table">
        <tr>
            <th>nume</th>
            <th>adresa</th>
            <th>telefon</th>
        </tr>
        <?php while($row = mysqli_fetch_assoc($result)) { ?>
                <td><?php echo $row['nume']; ?></td>
                <td><?php echo $row['adresa']; ?></td>
                <td><?php echo $row['telefon']; ?></td>
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

