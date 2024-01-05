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
              FROM Imprumut
              WHERE datar IS NULL
              AND CURDATE() - datai > nr_zile + 14
              ORDER BY datai ASC, nr_zile DESC";
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
    <p>3b) Să se găsească detaliile împrumuturilor ce nu au fost restituite cu întârziere peste 14 zile în ordine crescătoare după datai și descrescătoare după nr_zile.</p>
    <button class="show-button" id="show-button" onclick="showTable()">Show me</button>
    <table id="table">
        <tr>
            <th>id_carte</th>
            <th>id_imp</th>
            <th>datai</th>
            <th>datar</th>
            <th>nr_zile</th>
        </tr>
        <?php while($row = mysqli_fetch_assoc($result)) { ?>
                <td><?php echo $row['id_carte']; ?></td>
                <td><?php echo $row['id_imp']; ?></td>
                <td><?php echo $row['datai']; ?></td>
                <td><?php echo $row['datar']; ?></td>
                <td><?php echo $row['nr_zile']; ?></td>
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

