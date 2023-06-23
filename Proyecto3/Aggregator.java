// PROYECTO 3				EDUARDO CALLEJAS DIAZ			4CM12


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

import networking.WebClient;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Aggregator {
    private WebClient webClient;

    public Aggregator() {
        this.webClient = new WebClient(); //Objeto del tipo WebClient
    }

    public List<String> sendTasksToWorkers(List<String> workersAddresses, List<String> tasks) {  //ESte método recibe la lista de direcciones de los trabajadores y la lista de las tareas a desarrollar
        CompletableFuture<String>[] futures = new CompletableFuture[workersAddresses.size()];
        for (int i = 0; i < workersAddresses.size(); i++) { //Se itera sobre todos los elementos de la lista
            String workerAddress = workersAddresses.get(i);  // Se van obteniendo las direcciones de cada uno de los trabajadores 
            String task = tasks.get(i);  //Así como cada una de las tareas que esta en la lista

            byte[] requestPayload = task.getBytes();  //Se almacen las tareas en un formato de Byte
            futures[i] = webClient.sendTask(workerAddress, requestPayload);  //Se envían las tareas asincronas con el metodo senTask y se asocían las tareas a cada uno de los futures
        }

        List<String> results = Stream.of(futures).map(CompletableFuture::join).collect(Collectors.toList());  //Se declara una lista de resultados

        return results;  //Se regresa la lista de todas las respuestas asincronas
    }
}
