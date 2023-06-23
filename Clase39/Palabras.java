
// PROYECTO 3				EDUARDO CALLEJAS DIAZ			4CM12


import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.charset.Charset;

public class Palabras{
	private File libro;
	private String palabra;
	private String ret;
	private int cont;
	
	public Palabras(String palabra, String nlibro){
		this.palabra=palabra;
		libro=new File("LIBROS_TXT/"+nlibro);
	}


	public int Busqueda(){

		cont=0;
		
		Charset latin=StandardCharsets.ISO_8859_1;
		try( BufferedReader buff=new BufferedReader(new FileReader(libro,latin)) ){	//try-with-resources
				
				while((ret=buff.readLine())!=null){
					String array[]=ret.split(" ");
					String aux;

					for(String word:array){
						
						if(word.equalsIgnoreCase(palabra)){
								cont++;
						}else if(word.endsWith(",") || word.endsWith(".")|| word.endsWith(":") || word.endsWith(";") ||word.endsWith("?") || word.endsWith("!")  ){
								aux=word.substring(0,word.length()-1);
							if(aux.equalsIgnoreCase(palabra))
								cont++;	
						}else if(word.startsWith("(") || word.startsWith("¿") || word.startsWith("¡") || word.startsWith("-")){
								aux=word.substring(1);
							if(aux.equalsIgnoreCase(palabra))
								cont++;	

						}else if(word.endsWith("/")){
								if(word.toLowerCase().contains(palabra.toLowerCase()))
										cont++;
						}
							if(word.toLowerCase().contains(palabra.toLowerCase())){
									cont++;
							}
					}
				}
				


		}catch(IOException e){
			System.out.println("Error: "+e);

		}
		return cont;



	}
	public String resultado(){
			return String.format("La palabra se encontró %d veces\n\n", Busqueda());
	}

	public int getCont(){	return cont;}


	
}
