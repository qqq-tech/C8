package enc;

import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;

public class GBase64 {
	public static String encoder(String text) {
		byte[] targetBytes = text.getBytes();
		Encoder encoder = Base64.getEncoder();
		byte[] encodedBytes = encoder.encode(targetBytes);
		System.out.println("인코딩 전 : " + text);
		System.out.println("인코딩 text : " + new String(encodedBytes));
		
		return new String(encodedBytes);
	}

	public static String decoder(String text) {
		byte[] encodedBytes = text.getBytes();
		Decoder decoder = Base64.getDecoder();
		byte[] decodedBytes = decoder.decode(encodedBytes);
		System.out.println("디코딩 text : " + new String(decodedBytes));
		
		return new String(decodedBytes);
	}

}
