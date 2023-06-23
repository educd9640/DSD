import java.util.*;
public class Ejercicio5{
		public static void main(String[] args){

				int n=Integer.parseInt(args[0]);//recibe el args
				long inicio=System.nanoTime();
				int cont=0;//posicion del array
				byte[] cadena =new byte[n*4];
				byte ck=0;//check ipn
				int i;
				int[] wrd=new int[n];
				Random rnd =new Random();

				for(i=0;i<n;i++){
						for(byte j=0;j<3;j++,cont++){
								cadena[cont]=(byte)(rnd.nextInt(26)+65);
								//System.out.printf("%c",cadena[cont]);
						}

						cadena[cont]=(byte) (32);
						//System.out.printf("%c",cadena[cont]);
				}
				for(i=0;i<(n*4)-2;i++){

						if(cadena[i]==73 && cadena[i+1]==80 && cadena[i+2]==78){//IPN
							wrd[ck]=i;
							ck++;
						}
				}
				long fin=System.nanoTime();
				long nano=fin-inicio;
				System.out.println("Tiempo de ejecución en nanosegundos "+nano);

				if(ck!=0){
						System.out.println("\n"+ "Se encontro "+ck+" veces la palabra IPN");
						for(i=0;i<ck;i++){
								System.out.println("Se encontro la palabra IPN en la posicion "+wrd[i]);
						}
				}
		}
}
