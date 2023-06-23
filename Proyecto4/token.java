/*PROYECTO 4		EDUARDO CALLEJAS DIAZ
 *					4CM12  */

import java.util.List;
import java.util.ArrayList;
import java.io.*;
public class token implements java.io.Serializable{

		private List<Long> tiempos=new ArrayList<>();


		public void setTime(){
				tiempos.add(System.nanoTime());

		}

		public void imprime(){
				System.out.println("Tiempos contenidos :\n");

				for (Long t : tiempos) {
					System.out.println(t);
				}
		}

		public double promedio(){
				long prom=0;
				for (Long n : this.tiempos) {
					prom=n+prom;	
				}

				return (double)(prom/this.tiempos.size());
		}
}
