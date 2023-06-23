/*PROYECTO 2
 * EDUARDO CALLEJAS DIAZ	4CM12
 * */ 
import java.util.Comparator;
public class SortArea implements Comparator<PoligonoReg>{
		public int compare(PoligonoReg c1, PoligonoReg c2){
				return (int)(c1.getA() - c2.getA());
				//c1>c2     return positivo
				//c1=c2		return 0
				//c1<c2		return negativo
		}
}
