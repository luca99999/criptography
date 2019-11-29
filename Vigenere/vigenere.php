<?php
/*
    Copyright (c) 2019 - Luca Bernardini
 
    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.
    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.
    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */



function stampascii() {
	echo "<br>caratteri ascii<br>";
	for($x = 48; $x < 123; $x++)
		echo " " . chr($x);
}


function cifraCesare($stringa, $chiave) {
	$numele = strlen($stringa);
	echo "<br>";
	echo "Stringa cifrata <br>";
	for($x = 0; $x < $numele; $x++)
	{
		//$y = $x % lunghezzaverme; Vigenere
		if(ord(substr($stringa,$x,1) + $chiave) <= 122) {     
			echo chr(ord(substr($stringa,$x,1)) + $chiave);
		}
		else
		{
		$indice = ($chiave % 122) - (122 - ord($stringa[$x])); 
		 
		 echo chr($indice + 47);
		}
	}
}


function Vigenere($stringa, $verme) {
	$numele = strlen($stringa);
	$lunghezzaverme = strlen($verme);
	if($numele >= $lunghezzaverme)
	{
		echo "<br>";
		echo "Stringa cifrata <br>";
		// scorriamo un carattere per volta della stringa
		// della stringa da cifrare
		
		for($x = 0; $x < $numele; $x++)
		{
			// $x è l'indice della stringa
			// $y è l'indice del verme
			$y = $x % $lunghezzaverme; 
			echo "<br>resto = $y " . substr($stringa,$x,1) . " " . substr($verme, $y, 1);
			$somma = ord(substr($stringa,$x,1)) + ord(substr($verme, $y, 1));
			echo " $somma ";
			if((ord(substr($stringa,$x,1)) + ord(substr($verme, $y, 1))) <= 122) {     
				echo chr(ord(substr($stringa,$x,1)) + ord(substr($verme, $y, 1)));
			}
			else
			{
				$differenza = 122 - ord(substr($stringa,$x,1));
				
				
				$diff = (ord(substr($verme, $y, 1)) - $differenza) % 75;
				echo " differenza = $diff ";
				echo chr(($diff + 47));
			}
			
		}
	}
	else
	{
			echo "<h1>Attenzione lunghezza del verme > lunghezza stringa</h1>";
	}
}


function ordSemplice($lettera, $alfabeto) 
{
	$comodo = -99;
	 
	for($x = 0; $x < count($alfabeto); $x++)
	{
		if($alfabeto[$x] == $lettera) {
			$comodo = $x;
		    return $comodo;
		}
	}
	return $comodo;
}	

function chrSemplice($indice, $alfabeto)
{
	if($indice >= 0 && $indice < count($alfabeto))
			return $alfabeto[$indice];
	else
	{
		echo "chrSemplice: errore indice sbagliato";
		return -99;
	}
	
}

function VigenereSemplice($alfabeto, $stringa, $verme)
{
	$lunghezzaStringa = strlen($stringa);
	$lunghezzaVerme = strlen($verme);
	
	for($x = 0; $x < $lunghezzaStringa; $x++)
	{
		$y = $x % $lunghezzaVerme;
		if((ordSemplice(substr($stringa,$x,1), $alfabeto) + ordSemplice(substr($verme, $y, 1), $alfabeto)) < count($alfabeto))
		{
			$indice = ordSemplice(substr($stringa,$x,1), $alfabeto) + ordSemplice(substr($verme, $y, 1), $alfabeto);
			echo chrSemplice($indice, $alfabeto);
		}
		else
		{
			$indice = ordSemplice(substr($stringa,$x,1), $alfabeto) + ordSemplice(substr($verme, $y, 1), $alfabeto);
			$indice = $indice % count($alfabeto);
			echo chrSemplice($indice, $alfabeto);
		}
	}
}




function cifraCesare1($stringa, $chiave) {
	$numele = count($stringa);
	echo "<br>";
	echo "Stringa cifrata <br>";
	for($x = 0; $x < $numele; $x++)
	{
		// 122 è la lettera z (minuscolo)
		if((ord($stringa[$x]) + $chiave) <= 122) {
			echo chr(ord($stringa[$x]) + $chiave);
		}
		else
		{
		     
		}
	}
}

?>






	



 