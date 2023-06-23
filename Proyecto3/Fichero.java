
// PROYECTO 3				EDUARDO CALLEJAS DIAZ			4CM12


import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.charset.Charset;

public class Fichero{
	private File biblia=new File("BIBLIA_COMPLETA.txt");
	private String palabra;
	private String ret;
	private int cont;
	
	public Fichero(String palabra){
		this.palabra=palabra;
	}


	public String Busqueda(){

		cont=0;
		
		Charset latin=StandardCharsets.ISO_8859_1;
		try( BufferedReader buff=new BufferedReader(new FileReader(biblia,latin)) ){	//try-with-resources
				//ret=buff.readLine();
				//System.out.println(ret+";a");
				while((ret=buff.readLine())!=null){
					String array[]=ret.split(" ");
					String aux;

					for(String word:array){
						//System.out.println(word);
						if(word.equalsIgnoreCase(palabra)){
								cont++;
						}else if(word.endsWith(",") || word.endsWith(".")|| word.endsWith(":") || word.endsWith(";") ||word.endsWith("?") || word.endsWith("!")  ){
								aux=word.substring(0,word.length()-1);
							if(aux.equalsIgnoreCase(palabra))
								cont++;	
						}else if(word.startsWith("(") || word.startsWith("¿") || word.startsWith("¡") ){
								aux=word.substring(1);
							if(aux.equalsIgnoreCase(palabra))
								cont++;	

						}else if(word.endsWith("/")){
								if(word.contains(palabra))
										cont++;
						}
					}
				}
				


		}catch(IOException e){
			System.out.println("Error: "+e);

		}
		ret="Se encontró la palbra "+palabra+ " "+ cont+ " veces";
		return ret;



	}

	public int getCont(){	return cont;}


	
}
