
package comun.ejemplo;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import javax.crypto.Cipher;

public class RSAEncryption
{

	public static void main(
			String[] args) throws Exception
	{
		KeyPair keyPair = generateKeyPair();

		String originalString = "Hello, world!";
		byte[] encryptedString = encrypt(originalString, keyPair.getPublic());
		String decryptedString = decrypt(encryptedString, keyPair.getPrivate());

		System.out.println("Original: " + originalString);
		System.out.println("Encrypted: " + new String(encryptedString));
		System.out.println("Decrypted: " + decryptedString);
	}




	public static KeyPair generateKeyPair() throws Exception
	{
		KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
		keyPairGenerator.initialize(2048);
		return keyPairGenerator.generateKeyPair();
	}




	public static byte[] encrypt(
			String text,
			PublicKey publicKey) throws Exception
	{
		Cipher cipher = Cipher.getInstance("RSA");
		cipher.init(Cipher.ENCRYPT_MODE, publicKey);
		return cipher.doFinal(text.getBytes());
	}




	public static String decrypt(
			byte[] text,
			PrivateKey privateKey) throws Exception
	{
		Cipher cipher = Cipher.getInstance("RSA");
		cipher.init(Cipher.DECRYPT_MODE, privateKey);
		return new String(cipher.doFinal(text));
	}

}
