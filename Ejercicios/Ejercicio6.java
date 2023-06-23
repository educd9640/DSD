import java.util.*;
public class Ejercicio6{
		public static void main(String[] args){
				
				int n=Integer.parseInt(args[0]);//recibe el args
				long inicio=System.nanoTime();
				StringBuilder sb=new StringBuilder();
				byte ck=0;//check ipn
				int[] wrd=new int[n];
				int cont=0,p,i;
				Random rnd =new Random();

				for(i=0;i<n;i++){
						for(int j=0;j<3;j++){
								sb.append((char)(rnd.nextInt(26)+65));
								//System.out.printf("%c",cadena[cont]);
						}
						sb.append((char)32);
						//System.out.printf("%c",cadena[cont]);
				}
				p=sb.indexOf("IPN");
				while(p!=-1){
						wrd[cont]=p;
						cont++;
						p=sb.indexOf("IPN",p+1);
				}
				//System.out.println(sb);
				long fin=System.nanoTime();
				long nano=fin-inicio;
				System.out.println("Tiempo de ejecución en nanosegundos "+nano);
				if(cont>0){
						System.out.println("Se encontro "+cont+" veces la palabra IPN\n");
						System.out.println("Se encontrola palabra IPN en la(s) posicion(es)");
						for(i=0;i<cont;i++){
								System.out.print(" "+wrd[i]);
						}
						System.out.println();
				}
		}
}
