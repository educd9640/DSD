/*
// PROYECTO 3				EDUARDO CALLEJAS DIAZ			4CM12


 *  MIT License
 *
 *  Copyright (c) 2019 Michael Pogrebinsky - Distributed Systems & Cloud Computing with Java
 *
 *  Permission is hereby granted, free of charge, to any person obtaining a copy
 *  of this software and associated documentation files (the "Software"), to deal
 *  in the Software without restriction, including without limitation the rights
 *  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  copies of the Software, and to permit persons to whom the Software is
 *  furnished to do so, subject to the following conditions:
 *
 *  The above copyright notice and this permission notice shall be included in all
 *  copies or substantial portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 *  SOFTWARE.
 */

import java.util.ArrayList;
import java.util.Arrays; 
import java.util.List;

public class Application {
    //private static String WORKER_ADDRESS_1 = "http://localhost:8081/task"; //cadenas correspondientes a los endpoints de los servidores
    //private static String WORKER_ADDRESS_2 = "http://localhost:8082/task";

    public static void main(String[] args) {

		ArrayList<String> direcciones=new ArrayList<String>();
		//direcciones.add(args[0]);
		//direcciones.add(args[1]);
		//direcciones.add(args[2]);
		String dir1="http://"+ args[0]+"/task";
		String dir2= "http://"+args[1]+"/task";
		String dir3= "http://"+args[2]+"/task";

		String[] aux=new String[args.length - 3];
		int i;
		for(i=3;i<args.length;i++)
				aux[i-3]=args[i];
		

		ArrayList<String> array1=new ArrayList<String>();
		ArrayList<String> array2=new ArrayList<String>();
		ArrayList<String> array3=new ArrayList<String>();
		
		int len=(int)(aux.length/3);

		for(i=0;i<len;i++){
				array1.add(aux[i]);
				array2.add(aux[i+len]);
		}
		for(i=(2*len);i<aux.length;i++)
				array3.add(aux[i]);
		
		Runnable r1=new hilos(dir1,array1);
		Runnable r2=new hilos(dir2,array2);
		Runnable r3=new hilos(dir3,array3);

		Thread t1=new Thread(r1);
		Thread t2=new Thread(r2);
		Thread t3=new Thread(r3);

		t1.start();
		t2.start();
		t3.start();

        /*Aggregator aggregator = new Aggregator();  //objeto Aggregator
        //String task1 = "2022,Prueba numero 1";  //Se inicializan las cadenas correspondientes a los factores que se multiplicaran
        //String task2 = "12345,Prueba numero 2";

        List<String> results = aggregator.sendTasksToWorkers(direcciones, //Metodo de la clase Aggregator encargado de neviar todas las tareas hacia los trabajadores
                Arrays.asList(task1, task2)); //ocupa dos arreglos, uno para los trabajadores y otro para las tareas

        for (String result : results) {  //recibe los resultados
            System.out.println(result);  //imprime los resultados
        }*/
    }
}

class hilos implements Runnable{
		private String direccion;
		private List<String> palabras;
		public hilos(String direccion, List<String> palabras){
				this.direccion=direccion;
				this.palabras=palabras;
		}
		public void run(){
				Aggregator aggregator=new Aggregator();
				for(String word: palabras){
					System.out.println("Palabra enviada: "+word+" direccion: "+direccion);
					List<String> results = aggregator.sendTasksToWorkers(Arrays.asList(direccion), //Metodo de la clase Aggregator encargado de neviar todas las tareas hacia los trabajadoresA
									Arrays.asList(word)); //ocupa dos arreglos, uno para los trabajadores y otro para las tareas

					for (String result : results) {  //recibe los resultados
						System.out.println(result);  //imprime los resultados
					}
				}
					
				



		}

}
