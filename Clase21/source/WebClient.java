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

package networking;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpExchange;
import java.util.concurrent.CompletableFuture;

public class WebClient {
    private HttpClient client;  //Objeto del tipo HttpClient
	//private HttpResponse respuesta;

    public WebClient() {  //Crea un objeto HttpClient
        this.client = HttpClient.newBuilder()   //Llamado client
                .version(HttpClient.Version.HTTP_1_1)  //Utiliza el protocolo HttpClient versión 1.1
                .build();
    }

    public CompletableFuture<String> sendTask(String url, byte[] requestPayload) {  //REcibe la dirección con la que establece la conección y los datos a enviar hacia el servidor 

        HttpRequest request = HttpRequest.newBuilder()  //Se crea un objeto HttpRequest, el cual permite construir una solicitul Http 
                .POST(HttpRequest.BodyPublishers.ofByteArray(requestPayload))  //con el metodo POST
                .uri(URI.create(url))  //y la dirección de destino
                .header("X-Debug", "true")
                .build();
		//request -> {return request.body()  ;}
				//client -> {return client.body();}
        return client.sendAsync(request, HttpResponse.BodyHandlers.ofString())  //Se llama al metodo senAsync para enviar la solicitud request de una manera asincrona
                .thenApply(respuesta -> {return respuesta.body() + respuesta.headers();});
    }
}
