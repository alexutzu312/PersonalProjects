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
$query = "SELECT DATEDIFF(SYSDATE(), Imprumut.datai) AS Intarziere, Persoana.nume 
		FROM Imprumut 
		INNER JOIN Persoana ON Imprumut.id_imp = Persoana.id_pers
		WHERE Imprumut.datar IS NULL AND Imprumut.datai = (SELECT MIN(Imprumut.datai) FROM Imprumut);";
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
    font-size: 30px;
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
    <p>6.a) Să se găsească numele persoanelor și întârzierea, cu întârzierea cea mai mare pentru cărțile împrumutate și nerestituite.</p>
    <button class="show-button" id="show-button" onclick="showTable()">Show me</button>
    <table id="table">
        <tr>
            <th>Intarziere</th>
            <th>nume</th>
        </tr>
        <?php while($row = mysqli_fetch_assoc($result)) { ?>
                <td><?php echo $row['Intarziere']; ?></td>
				<td><?php echo $row['nume']; ?></td>
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

