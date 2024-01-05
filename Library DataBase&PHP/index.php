<!DOCTYPE html>
<html>
<head>
<style>
body {
  background-color: lightgray;
  text-align: center;
}

h2 {
  font-size: 50px;
  margin: 40px 0;
}

form {
  text-align: center;
}

.button-pair {
  display: flex;
  justify-content: center;
}

button {
  font-size: 30px;
  width: 150px;
  height: 50px;
  margin: 15px;
  padding: 15px 32px;
  background-color: lightblue;
  display: flex;
  align-items: center;
  justify-content: center;
}

</style>
</head>
<body>

<h2>Biblioteca</h2>

<form>
<?php
$labels = array("1(3a)", "2(3b)", "3(4a)", "4(4b)", "5(5a)", "6(5b)", "7(6a)", "8(6b)");
for ($i = 0; $i < 8; $i+=2) {
    echo "<div class='button-pair'>
    <button type='button' accesskey='".($i+1)."' onclick='buttonClick(".($i+1).")'>".$labels[$i]."</button>
    <button type='button' accesskey='".($i+2)."' onclick='buttonClick(".($i+2).")'>".$labels[$i+1]."</button>
    </div>";
}
?>
</form> 



<script>
function buttonClick(button) {
    if(button == 1){
        window.location.href = "3a.php";
    }
	if(button == 2){
        window.location.href = "3b.php";
    }
	if(button == 3){
        window.location.href = "4a.php";
    }
	if(button == 4){
        window.location.href = "4b.php";
    }
	if(button == 5){
        window.location.href = "5a.php";
    }
	if(button == 6){
        window.location.href = "5b.php";
    }
	if(button == 7){
        window.location.href = "6a.php";
    }
	if(button == 8){
        window.location.href = "6b.php";
    }
}

document.addEventListener('keydown', function(event) {
    if (event.keyCode >= 49 && event.keyCode <= 56) {
      buttonClick(event.keyCode - 48);
    }
  });

  function buttonClick(button) {
    if(button == 1){
        window.location.href = "3a.php";
    }
    if(button == 2){
        window.location.href = "3b.php";
    }
    if(button == 3){
        window.location.href = "4a.php";
    }
    if(button == 4){
        window.location.href = "4b.php";
    }
    if(button == 5){
        window.location.href = "5a.php";
    }
    if(button == 6){
        window.location.href = "5b.php";
    }
    if(button == 7){
        window.location.href = "6a.php";
    }
    if(button == 8){
        window.location.href = "6b.php";
    }
  }

</script>
</body>
</html>