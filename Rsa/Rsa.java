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
 
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.Random;
 
public class Rsa
{
    private static final BigInteger BigInteger = null;
	private static BigInteger p;
    private static BigInteger q;
    private static BigInteger N;
    private static BigInteger z;
    private static BigInteger e;
    private static BigInteger d;
    private static int  bitlength = 1024;
    private static Random     r, r1;
	
    @SuppressWarnings("static-access")
	public Rsa()
    {
        r = new Random();
        // Find large 1024 bits primes numbers p and q
        p = BigInteger.probablePrime(bitlength, r);
        q = BigInteger.probablePrime(bitlength, r);
        // n = p * q
        N = p.multiply(q);
        // z = (p - 1) * (q - 1)
        z = (p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE));
        
        // we choose a number e (e = encrypt) which is used to encrypt less than n 
        // that has no factors in common with z if not 1 (e and z are prime among them). 
        // We must find a number d (d = decrypt) such that is inverse of e modulo z
        
        e = BigInteger.probablePrime(bitlength, r);
        
        while (true)
        {
        	if(e.compareTo(z) < 0 && z.gcd(e).compareTo(BigInteger.ONE) == 0)
        	{
        	  // the gcd between z and e exists. 
        	  // This means that the multiplicative inverse of e modulo z exists
        	  d = e.modInverse(z);
          	  break;
           	}
        	
        	r = new Random();
        	
        	// bit lenght between (bitlenght / 2) and (bitlenght - 1) 
        	r1 = new Random();
   		    int lengthBitsd = r1.nextInt(bitlength/2) + (bitlength / 2 - 1);
        	e = BigInteger.probablePrime(lengthBitsd, r);
        }
     
    }
 
    // Encrypt message
    public byte[] encrypt(byte[] message)
    {
        return (new BigInteger(message)).modPow(e, N).toByteArray();
    }
 
    // Decrypt message
    public byte[] decrypt(byte[] message)
    {
        return (new BigInteger(message)).modPow(d, N).toByteArray();
    }
    
    
    public String toHex(byte[] arrayBytes) {
		// It creates a BigInteger object by passing a signum and a byte array,
		// signum 1 means positive, otherwise if the first byte is negative 
		// it returns a negative BigInteger
		// with the toString(16) base 16 method the output isn't padded with leading zeros
		
	    return new BigInteger(1, arrayBytes).toString(16);
	}

 
    public static void main(String[] args) throws IOException
    {
    	Rsa rsa = new Rsa();
        BufferedReader tastiera = new BufferedReader(
        		new InputStreamReader(System.in));
        String stringa;
        System.out.println("Inserisci il testo in chiaro:");
        stringa = tastiera.readLine();
   
        
        System.out.println("Stringa in Bytes: "
                + rsa.toHex(stringa.getBytes()));
        
        System.out.println("Public key (n, e)");
        System.out.println("n = " + N.toString());
        System.out.println("e = " + e.toString());
        System.out.println("Private key (n, d)");
        System.out.println("n = " + N.toString());
        System.out.println("d = " + d.toString());
        
        // encrypt
        byte[] encrypted = rsa.encrypt(stringa.getBytes(StandardCharsets.UTF_8));
        
        String s = new String(encrypted, StandardCharsets.UTF_8);
       
        System.out.println("Testo criptato " + s);
        
        byte[] decrypted = rsa.decrypt(encrypted);
        System.out.println("Bytes Decriptati: " + rsa.toHex(decrypted));
        System.out.println("String decriptata: " + new String(decrypted, StandardCharsets.UTF_8));
        
    }
}
 
     
 