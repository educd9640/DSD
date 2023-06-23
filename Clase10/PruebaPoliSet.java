import java.util.*;
public class PruebaPoliSet{
    public static void main (String[] args) {
			long inicio=System.nanoTime();
			double a,b;
			PoligonoIrregular poli=new PoligonoIrregular(10000000);
			for(int i=0;i<10000000;i++){
					a=Math.random();
					b=Math.random();
					Coordenada c=new Coordenada(a,b);
					poli.anadeVertice(c);
			}
			long fin=System.nanoTime();
			System.out.println("\nPoligono irregular\nTiempo de ejecucion usando new en nanosegundos "+(fin-inicio));
		}
}
