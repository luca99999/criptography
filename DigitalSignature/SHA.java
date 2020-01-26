/*
 * Copyright (c) 2020 - Luca Bernardini
 * 
 *  This program is free software: you can redistribute it and/or modify
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


import java.math.BigInteger; 
import java.nio.charset.StandardCharsets; 
import java.security.MessageDigest; 
import java.security.NoSuchAlgorithmException; 

// Java program to calculate SHA hash value 


public class SHA { 
	
	public static String encryptSHA(String s, String category ) throws NoSuchAlgorithmException 
	{ 
	    // Java Criptographic Library 
		MessageDigest md = MessageDigest.getInstance(category); 
		// A message digest is a criptographic hash function containing a string of 
		// digit created by a one-way hashing formula.
		// md.digest returns an array of bytes. 
		// We have to turn it into an object String
		byte[] messageDigest = md.digest(s.getBytes(StandardCharsets.UTF_8));
		
		
		// It creates a BigInteger object by passing a signum and a byte array,
		// signum 1 means positive, otherwise if the first byte is negative 
		// it returns a negative BigInteger
		// with the toString(16) base 16 method the output isn't padded with leading zeros
				
		BigInteger bigInt = new BigInteger(1, messageDigest);
		String outputHex = bigInt.toString(16);
		
		// it adds leading zeros on the left
       
		if(category.contentEquals("SHA-256")) 
		{
			while ( outputHex.length() < 64 ) {
				outputHex = "0"+ outputHex;
			}
		}
		
		// it adds leading zeros on the left
        
		if(category.contentEquals("SHA-512")) 
		{
			while ( outputHex.length() < 128 ) {
				outputHex = "0"+ outputHex;
			}
		}
		
		return outputHex;

	} 

	
	public static void main(String args[]) throws NoSuchAlgorithmException 
	{ 
		
			
			String stringa = "Hello World!"; 
			System.out.println("\n" + stringa + " : " + encryptSHA(stringa, "SHA-256") + " Lunghezza: " + encryptSHA(stringa, "SHA-256").length()); 
			System.out.println("\n" + stringa + " : " + encryptSHA(stringa, "SHA-512") + " Lunghezza: " + encryptSHA(stringa, "SHA-512").length());
		
		
	} 
} 
