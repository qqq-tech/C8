package example;

import enc.Caesar;
import enc.GBase64;
import enc.Sha;

public class EncEx {

	public static void main(String[] args) {

		System.out.println(Caesar.cipher("ABCD", 5)); //result: FGHI
		System.out.println(Caesar.decipher("FGHI", 5)); //resut: ABCD
		
		System.out.println(GBase64.encoder("ABCD")); //result: QUJDRA==
		System.out.println(GBase64.decoder("QUJDRA==")); //ABCD
		
		System.out.println(Sha.getSha512("ABCD")); //result SexVvYP81ng449OFzoMWaeP4Faf0S3ql+NUrXUI1TEbYnIudBuR6eXrk+9Iikb4VvMNbB3NcSm+SNX+T1aM9mw==		
	}

}
