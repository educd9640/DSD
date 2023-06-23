import java.nio.charset.Charset;

public class Main{
		public static void main (String[] args){
				
				String palabra=args[0];
				System.out.println(palabra.substring(0,palabra.length()-1 ));
				Fichero ficha=new Fichero(palabra);
				System.out.println(ficha.Busqueda());
				System.out.println(ficha.getCont());
		}
}
