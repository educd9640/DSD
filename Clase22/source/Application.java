/*
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

import java.util.Arrays; 
import java.util.List;

public class Application {
    private static  String WORKER_ADDRESS_1= "http://34.125.116.142/searchipn"; //cadenas correspondientes a los endpoints de los servidores
    private static  String WORKER_ADDRESS_2= "http://34.125.115.83/searchipn";
    private static  String WORKER_ADDRESS_3= "http://34.125.65.83/searchipn";
    private static  String WORKER_ADDRESS_4= "http://34.125.151.31/searchipn";

    public static void main(String[] args) {
        Aggregator aggregator = new Aggregator();  //objeto Aggregator
        String search = "1757600,IPN";  //Se inicializan las cadenas correspondientes a los factores que se multiplicaran
        //String task2 = "123456789,100000000000000,700000002342343";

        List<String> results = aggregator.sendTasksToWorkers(Arrays.asList(WORKER_ADDRESS_1, WORKER_ADDRESS_2, WORKER_ADDRESS_3, WORKER_ADDRESS_4), //Metodo de la clase Aggregator encargado de neviar todas las tareas hacia los trabajadores
                Arrays.asList(search,search,search,search)); //ocupa dos arreglos, uno para los trabajadores y otro para las tareas

        for (String result : results) {  //recibe los resultados
            System.out.println(result);  //imprime los resultados
        }
    }
}
