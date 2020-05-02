package enc;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.util.Base64;

public class Sha {
	public static String getSha512(String plainText) {
		try {
			/*
			 SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
			byte[] bytes = new byte[16];
			random.nextBytes(bytes);
			String salt = new String(Base64.getEncoder().encode(bytes));
			
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			md.update(salt.getBytes());
			md.update(raw.getBytes());
			String hex = String.format("%064x", new BigInteger(1, md.digest()));
			 */
			MessageDigest md = MessageDigest.getInstance("SHA-512");
			byte[] bytes = plainText.getBytes(Charset.forName("UTF-8"));
			md.update(bytes);
			return new String(Base64.getEncoder().encode(md.digest()));
		} catch (Exception e) {
			System.out.println("Sha512 error.");
			e.printStackTrace();
			return null;
		}
	}
}
