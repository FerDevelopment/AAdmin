
package comun.ejemplo;

public class Main
{

	public static void main(
			String[] args)
	{
		String textoOriginal = "Hola Mundo!";
		String textoModificado = aumentarAscii(textoOriginal);
		String textoOri = Disminuir(textoModificado);
		System.out.println("Texto original: " + textoOriginal);
		System.out.println("Texto modificado: " + textoModificado);
		System.out.println("Texto original2: " + textoOri);
	}




	public static String aumentarAscii(
			String texto)
	{
		StringBuilder resultado = new StringBuilder();


		for (int i = 0; i < texto.length(); i++)
		{
			char caracter = texto.charAt(i);
			int ascii = (int) caracter;
			ascii = ((ascii - 32 + 3) % 95) + 32;

			resultado.append((char) ascii);
		}

		return resultado.toString();
	}




	public static String Disminuir(
			String texto)
	{
		StringBuilder resultado = new StringBuilder();


		for (int i = 0; i < texto.length(); i++)
		{
			char caracter = texto.charAt(i);
			int ascii = (int) caracter;
			ascii = ((ascii - 32 - 3 + 95) % 95) + 32;

			resultado.append((char) ascii);
		}

		return resultado.toString();
	}

}
