<?php
function calculateDiscount($price, $discount) {
    if ($discount > 0) {
        return $price * (1 - $discount / 100);
    }
    return $price;
}

$price = 200;
$discount = "20";

$newPrice = calculateDiscount($price, $discount);
echo "Discounted price: " . $newPrice . "\n";

$a = "10";
$b = 10;
if ($a == $b) {
    echo "Equal\n";
} else {
    echo "Not equal\n";
}

$number = "10";
$number += 5;
echo $number;
