<?php
function calculateDiscount($price, $discount) {
    if ($discount > 0) {
        return $price * (1 - $discount / 100);
    }
    return $price;
}

$price = 200;
$discount = "20";  // Erro: string ao invés de número

$newPrice = calculateDiscount($price, $discount);
echo "Discounted price: " . $newPrice . "\n";

// Comparação confusa
$a = "10";
$b = 10;
if ($a == $b) {
    echo "Equal\n";  // Resultado pode ser inesperado devido à coerção de tipos
} else {
    echo "Not equal\n";
}

// Manipulação de tipos
$number = "10";
$number += 5;  // Coerção implícita
echo $number;  // Resultado é 15, mas pode ser confuso
