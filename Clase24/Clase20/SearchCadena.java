import java.util.*;
public class SearchCadena{
	private int n;
	private String palabra;

	public SearchCadena(byte[] requestBytes){
		String bodyString = new String(requestBytes);
		String[] stringdata = bodyString.split(",");
		
		this.n = Integer.parseInt(stringdata[0]);
		this.palabra = stringdata[1];
			
		}
	
	public byte[] run(){
			StringBuilder sb=new StringBuilder();

			int[] wrd=new int[this.n];
			int cont=0,p,i;
			Random rnd =new Random();

			for(i=0;i<this.n;i++){
					for(int j=0;j<3;j++){
							sb.append((char)(rnd.nextInt(26)+65));
							//System.out.printf("%c",cadena[cont]);
					}
					sb.append((char)32);
					//System.out.printf("%c",cadena[cont]);
			}
			p=sb.indexOf(this.palabra);
			while(p!=-1){
					wrd[cont]=p;
					cont++;
					p=sb.indexOf("IPN",p+1);
			}
			
			return String.format("Se encontró la palabra %s , %s veces" ,this.palabra ,cont).getBytes();

	}
				

		
}
