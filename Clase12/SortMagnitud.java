import java.util.Comparator;
public class SortMagnitud implements Comparator<Coordenada>{
		public int compare(Coordenada c1, Coordenada c2){
				return (int)(c1.getMag() - c2.getMag());
				//c1>c2     return positivo
				//c1=c2		return 0
				//c1<c2		return negativo
		}
}
