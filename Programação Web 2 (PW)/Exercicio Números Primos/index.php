<?php

echo "Números Primos de 1 a 1000: <br><br>";
for ($t = 2; $t <= 1000; $t++) {
    $primo = true;
    for ($i = 2; $i < $t; $i++) {
        if ($t % $i == 0) {
            $primo = false;
            break;
        }
    }
    if ($primo) 
    echo $t. " <br>";
}
?>