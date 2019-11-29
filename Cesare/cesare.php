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

$password = $_POST["password"];
$chiave = $_POST["chiave"];

echo $password . " " .$chiave."\n" ;

$lunghezza=strlen($password);
echo "lunghezza= ".$lunghezza;
$lunghezzachiave=strlen($chiave);
echo "lunghezzachiave ".$lunghezzachiave;
$y=0;
$stringacriptata="";

for($x=0;$x<=$lunghezza;$x++)
{
	$lettera=substr($password,$x,1);
	$numeroascii=ord($lettera);
	if($y==$lunghezzachiave)
	{
		$y=0;
		
	}
	
	$letterachiave=substr($chiave,$y,1);
	$numerochiave=ord($letterachiave);
	if($numerochiave>=65 && $numerochiave<=90){
		
		$numerochiave=$numerochiave-65;
		
	}
	
	if($numerochiave>=97 && $numerochiave<=122){
		
		$numerochiave=$numerochiave-97;
		
	}
	
	if($numerochiave>=48 && $numerochiave<=57){
		
		$numerochiave=$numerochiave-48;
		
	}
	
	echo"postazione ".$numerochiave;
	$stringacriptata=$stringacriptata . chr($numeroascii+$numerochiave);
	$y++;
}
  echo "<h1>".$stringacriptata."</h1>";
?>
