package id.ac.budiluhur.qrbeta3;

/**
 * Created by saddamnur on 5/26/2017.
 */


public class VigenereCipher {

    public static String vigenere_cipher(String plaintext, String key, boolean encrypt) {

        String alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890 ,.-@"; // including some special chars
        final int alphabetSize = alphabet.length();
        final int textSize = plaintext.length();
        final int keySize = key.length();
        final StringBuilder encryptedText = new StringBuilder(textSize);

        for (int i = 0; i < textSize; i++) {
            final char plainChar = plaintext.charAt(i); // get the current character to be shifted
            final char keyChar = key.charAt(i % keySize); // use key again if the end is reached
            final int plainPos = alphabet.indexOf(plainChar); // plain character's position in alphabet string
            if (plainPos == -1) { // if character not in alphabet just append unshifted one to the result text
                encryptedText.append(plainChar);
            } else { // if character is in alphabet shift it and append the new character to the result text
                final int keyPos = alphabet.indexOf(keyChar); // key character's position in alphabet string
                if (encrypt) { // encrypt the input text
                    encryptedText.append(alphabet.charAt((plainPos + keyPos) % alphabetSize));
                } else { // decrypt the input text
                    int shiftedPos = plainPos - keyPos;
                    if (shiftedPos < 0) { // negative numbers cannot be handled with modulo
                        shiftedPos += alphabetSize;
                    }
                    encryptedText.append(alphabet.charAt(shiftedPos));
                }
            }
        }

        return encryptedText.toString();

    }
}


