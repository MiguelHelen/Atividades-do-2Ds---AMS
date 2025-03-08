<?php

echo "Números Primos de 1 a 1000: <br><br>";
for ($t = 2; $t <= 1000; $t++) {
    $m = true;
    for ($i = 2; $i < $t; $i++) {
        if ($t % $i == 0) {
            $m = false;
            break;
        }
    }
    if ($m) 
    echo $t. " <br>";
}
?>
