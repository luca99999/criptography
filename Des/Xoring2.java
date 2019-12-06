/*
 * Copyright (c) 2019 - Luca Bernardini
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

public class Xoring2 {
 
	// char is 16 bits unsigned integers representing UTF-16 code units
	// from '\u0000' to '\uffff' inclusive, that is, from 0 to 65535
	
	public static void printBitsChar(char a)
	{
		byte bit;
		for(int x = 15; x >= 0; x--)
		{
			// >>> the logical shift to the right shifts the bit to the right
			// the number of positions specified by x and fills from the left with 0s
			
			bit = (byte)((a >>> x) & 0x0001);
			System.out.print(bit);
		}
		System.out.println("");
		
		
	}
	
	// print the byte number a in binary base
	
	public static void printBitsByte(byte a)
	{
		byte bit;
		for(int x = 7; x >= 0; x--)
		{

			// Signed right shift (>>).
			// Shifts the bits of the first operand over by the number of positions 
			// indicated by the second operand.
			// The operator ‘>>’ uses the sign bit (the left most one) to fill the trailing positions after shift. 
			// If the number is negative, then 1 is used to fill the remaining spaces and 
			// if the number is positive, then 0 is used as a filler.
			
			bit = (byte)((a >> x) & 0x01);
			System.out.print(bit);
		}
		System.out.println("");
		
		
	}
	
	// it returns the value of the bit at the given position 
	public static byte readBit(byte dato, int position) 
	{
		  
		  return (byte)(dato >> (8 - (position + 1)) & 0x01);
	    
	}
	
	// writes value 0 or 1 at the position in the given byte
	public static byte writeBit(byte dato, int position, byte value) 
	{
		  // 0xFF7F = 1111111101111111
		  // builds a mask where the most significant bit of the byte
		  // is shifted to the position of the bit to be written.
		  // The byte is then put in (and) with the mask so as to have the bit to be modified equal to zero. 
		  // Subsequently, the bit to be written is moved to the left to the desired position 
		  // at which the bit to be modified is at zero. 
		  // The operator (or) between the byte to be modified and the value to be set allows the modification.
		  byte mask = (byte) (0xFF7F >> position);
		  byte byteMasked = (byte)((mask & dato) & 0x00FF);
	      return (byte)(value << (8 - (position + 1)) | byteMasked);
	}
	
	public static char xoringChar(char car, char key)
	{
		// xoring unsigned 16 bits 
		char xoredChar = (char)(car ^ key);
		return xoredChar;
	}
	
	public static byte[] xoringArrayBytes(byte[] data, byte[] key)
	{
		// xor function on two byte array
		byte[] xoredByte = (byte[]) new byte[data.length];
		int j = 0;
		for(int i = 0; i < data.length; i++)
		{
			j = i % key.length;
			xoredByte[i] = (byte) (data[i] ^ key[j]);
		}
		return xoredByte;
	}
	
	
	
	public static String toHex(byte[] arrayBytes) {
		// It creates a BigInteger object by passing a signum and a byte array,
		// signum 1 means positive, otherwise if the first byte is negative 
		// it returns a negative BigInteger
		// with the toString(16) base 16 method the output isn't padded with leading zeros
		
	    return new BigInteger(1, arrayBytes).toString(16);
	}
		
	
	public static void main(String[] args) {
			
		String testo = "pippop我lu你to";
		String chiave = "jd你好gki";
		
		// Encodes this String into a sequence of bytes using the platform's default 
		// charset, storing the result into a new byte array.
		
		byte[] testoBytes = testo.getBytes(StandardCharsets.UTF_8);
		byte[] chiaveBytes = chiave.getBytes(StandardCharsets.UTF_8);
		
		byte[] cifrato = xoringArrayBytes(testoBytes, chiaveBytes);
		
	
		System.out.println("Testo cifrato rappresentato in esadecimale " + toHex(cifrato));
		
		byte[] chiaro = xoringArrayBytes(cifrato, chiaveBytes);
		
		// Constructs a new String by decoding the specified 
		// array of bytes using the specified charset.
		String s = new String(chiaro, StandardCharsets.UTF_8);
		
		System.out.println("Testo decifrato " + s);
		
		// the value of the byte is represented in two's complement
		byte dato = 98;
		printBitsByte(dato);
		
		dato = -100;
		printBitsByte(dato);
		System.out.println("");
		byte b = 99;
		printBitsByte(b);
		System.out.println("");
		System.out.println(readBit(b,4));
		
		byte modified = writeBit(b, 4, (byte)1);
		System.out.println("");
		printBitsByte(modified);
		
	}

}