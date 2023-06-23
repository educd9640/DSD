import java.util.*;
public class PruebaPoli{
    public static void main (String[] args) {
			long inicio=System.nanoTime();
			double a,b;
			PoligonoIrregular poli=new PoligonoIrregular(10000000);
			Coordenada c=new Coordenada(0,0);
			for(int i=0;i<10000000;i++){
					a=Math.random();
					b=Math.random();
					c.set(a,b);
					poli.anadeVertice(c);
			}
			long fin=System.nanoTime();
			System.out.println("\nPoligono irregular\nTiempo de ejecucion usando set en nanosegundos "+(fin-inicio));
		}
}
