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


public class DesV1 {
	
	final static int BLOCCO = 8;
	final static int PERMUTAZIONE = 64;

	// Initial permutation table
	
	private static final byte[] IP = { 
			58, 50, 42, 34, 26, 18, 10, 2,
			60, 52, 44, 36, 28, 20, 12, 4,
			62, 54, 46, 38, 30, 22, 14, 6,
			64, 56, 48, 40, 32, 24, 16, 8,
			57, 49, 41, 33, 25, 17, 9,  1,
			59, 51, 43, 35, 27, 19, 11, 3,
			61, 53, 45, 37, 29, 21, 13, 5,
			63, 55, 47, 39, 31, 23, 15, 7
		};
	
	// Final inverse permutation table
	private static final byte[] FP = {
			40, 8, 48, 16, 56, 24, 64, 32,
			39, 7, 47, 15, 55, 23, 63, 31,
			38, 6, 46, 14, 54, 22, 62, 30,
			37, 5, 45, 13, 53, 21, 61, 29,
			36, 4, 44, 12, 52, 20, 60, 28,
			35, 3, 43, 11, 51, 19, 59, 27,
			34, 2, 42, 10, 50, 18, 58, 26,
			33, 1, 41, 9, 49, 17, 57, 25
		};
	
	// if modo == 1 the method makes the initial permutation
	// otherwise if modo == 0 it make the inverse permutation
	
	public static byte[] permuteIP(byte[] dati, int modo) throws Exception
	{
	
		System.out.println("\ndati lunghezza " + dati.length);
		if(dati.length == BLOCCO) 
		{
			byte[] TABLE = new byte[PERMUTAZIONE];
			byte[] permutato = new byte[BLOCCO];
		    
			if(modo == 1)
		    {
			    //Copy array by iterating
		        for (int i = 0; i < PERMUTAZIONE; i++) 
		             TABLE[i] = IP[i];
		        
		    }
		    else if(modo == 0)
		    {
		    	//Copy array by iterating
		        for (int i = 0; i < PERMUTAZIONE; i++) 
		             TABLE[i] = FP[i];
		    }
			
			// Takes a 64 bit input and permutes according to the IP or FP table.
            
			for(int i = 0; i < PERMUTAZIONE; i++)
			{
				// Find the corresponding byte and the position within the byte itself. 
				// Reads the value of the bit. Identify the byte in the permutato array 
				// and the position within the byte where to place the previously read bit.
				
			   	int posizioneByte = (TABLE[i] - 1) / 8;
				int posizioneBit = (TABLE[i] - 1) % 8;
				byte valore = Xoring3.readBit(dati[posizioneByte], posizioneBit);
			
				int posizioneByteI = (int)(i / 8);
				int posizioneBitI = (int)(i % 8);
				byte modified = Xoring3.writeBit(permutato[posizioneByteI], posizioneBitI, valore);
				permutato[posizioneByteI] = modified;
					    	
			}
			return permutato;
		}
		else
		{
				throw new Exception("Errore permutazione");
		}
		
		
	}

	public static void main(String[] args) throws Exception 
	{
		long c = 5813889971904273317L;
		System.out.println("chiaro = " + c);
		
		long chiave = 0x690af26cL;
		
		
		byte[] byteLong = Xoring3.longToByte(c);
			
		System.out.println(" ");
				
		System.out.println("printBitsLong");
		Xoring3.printBitsLong(c);
		System.out.println("");
				
		System.out.println("printBitsArray ");
		
		Xoring3.printBitsArray(byteLong);
		
		
		byte[] permutato = permuteIP(byteLong, 1);
		System.out.println(" ");
		System.out.println("permutato");
		Xoring3.printBitsArray(permutato);
		
		byte[] ripermutato = permuteIP(permutato, 0);
		
		System.out.println(" ");
		System.out.println("ripermutato");
		Xoring3.printBitsArray(ripermutato);
		
		long d = Xoring3.arrayByteToLong(ripermutato);
		
		System.out.println("");
		System.out.println("decifrato = " + d);
		
	}



}
