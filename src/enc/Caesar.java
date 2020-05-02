package enc;

public class Caesar {
	final char alphaNum = 26;
/*
	public String caesar(String string, int i) {
		char c[] = string.replace(" ", "").toCharArray();
		StringBuilder sb = new StringBuilder();
		char t = 0;

		for (char fc : c) {
			t = Character.isUpperCase(fc) ? 'A' : 'a';
			t = (char) ((char) ((fc - t) + ((char) i)) % alphaNum + t);
			sb.append(Character.toString(t) + " ");
		}

		return sb.toString();
	}
*/
	public static String decipher(String message, int offset) {
		return cipher(message, 26 - (offset % 26));
	}

	public static String cipher(String message, int offset) {
		StringBuilder result = new StringBuilder();
		char t = 0;
		for (char character : message.toCharArray()) {
			if (character != ' ') {
				t = Character.isUpperCase(character) ? 'A' : 'a';
				int originalAlphabetPosition = character - t;
				int newAlphabetPosition = (originalAlphabetPosition + offset) % 26;
				char newCharacter = (char) (t + newAlphabetPosition);
				result.append(newCharacter);
			} else {
				result.append(character);
			}
		}
		return result.toString();
	}

}
