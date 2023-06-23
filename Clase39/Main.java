import java.io.File;

public class Main{
		public static void main(String[] args){
			File ruta=new File("LIBROS_TXT");
			String[] lista=ruta.list();
			System.out.println("----- "+lista.length);
			Palabras p;
			for (String archivo : lista) {
				System.out.print(archivo);
				p=new Palabras("la",archivo );
				System.out.print(p.resultado());
				
				
			}

			String str1="Hola";
			String str2="-<¿Holaadiosascac?>";
			System.out.println(str2.contains(str1));

			System.out.println(str2.toLowerCase().contains(str1.toLowerCase()));

		}
}
