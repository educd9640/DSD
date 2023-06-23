/*PROYECTO 4		EDUARDO CALLEJAS DIAZ
 *					4CM12  */

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
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpClient;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;
import java.util.List;
import java.util.Scanner;
//import networking.WebClient;

import java.net.http.HttpResponse;

import java.io.IOException;
import java.io.OutputStream;
import java.math.BigInteger;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.stream.Stream;

public class WebServer {
    private static final String TASK_ENDPOINT = "/task";
    private static final String STATUS_ENDPOINT = "/status";
	private static final String MULT_ENDPOINT="/mult";
	private static final String ANILLO_ENDPOINT="/anillo";

    private final int port;
    private HttpServer server;
	private static WebClient client=new WebClient();
	private static List<String> direcciones=new ArrayList<>();

    public static void main(String[] args) {
        int serverPort = 8080;
        //if (args.length == 1) {
            serverPort = Integer.parseInt(args[0]);
        //}
		for(int i=1;i<args.length;i++){
				direcciones.add("http://"+args[i].trim()+"/anillo");
				System.out.println(direcciones.get(i-1));
		}

        WebServer webServer = new WebServer(serverPort);
        webServer.startServer();
		
		Scanner sc=new Scanner(System.in);

        System.out.println("Servidor escuchando en el puerto " + serverPort);
		System.out.println("Desea iniciar mandando el token? Y/N");


		if(sc.nextLine().equalsIgnoreCase("Y")){

		System.out.println("Enviando...\n");
				token tk=new token();
				byte[] aux;
				tk.setTime();
				tk.imprime();
				aux=SerializationUtils.serialize(tk);
				client.sendTask(direcciones.get(0),aux);

				
		}else{
				System.out.println("Esperando token...\n");
		}

    }

    public WebServer(int port) {
        this.port = port;
    }

    public void startServer() {
        try {
            this.server = HttpServer.create(new InetSocketAddress(port), 0);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }


        HttpContext statusContext = server.createContext(STATUS_ENDPOINT);
        HttpContext taskContext = server.createContext(TASK_ENDPOINT);
		HttpContext multContext = server.createContext(MULT_ENDPOINT);
		HttpContext anilloContext = server.createContext(ANILLO_ENDPOINT);

        statusContext.setHandler(this::handleStatusCheckRequest);
        taskContext.setHandler(this::handleTaskRequest);
		multContext.setHandler(this:: handleMultRequest);
		anilloContext.setHandler(this::handleAnilloRequest);

        server.setExecutor(Executors.newFixedThreadPool(8));
        server.start();
    }

    private void handleAnilloRequest(HttpExchange exchange) throws IOException {
        if (!exchange.getRequestMethod().equalsIgnoreCase("post")) {
            exchange.close();
            return;
		}


		CompletableFuture<String> future=new CompletableFuture();
				try{
						byte[] requestBytes = exchange.getRequestBody().readAllBytes();
						token obj=(token)SerializationUtils.deserialize(requestBytes);
						obj.setTime();
						obj.imprime();
						System.out.println("Promedio: "+ obj.promedio());

						byte[] sendB=SerializationUtils.serialize(obj);

						for(int i=1;i<=2;i++){
								System.out.println(i+"...");
								Thread.sleep(1000);
						}
						future=client.sendTask(direcciones.get(0), sendB);
						future.join();
						return;

				}catch(Exception e){
						//e.printStackTrace();
						direcciones.remove(0);
						System.out.println("\n\n********Corrigiendo...**********\n");
						for (String str : direcciones) {
								System.out.println(str);
							
						}
						System.out.println("\n");
						return;
				}




	}



    private void handleTaskRequest(HttpExchange exchange) throws IOException {
        if (!exchange.getRequestMethod().equalsIgnoreCase("post")) {
            exchange.close();
            return;
        }

        Headers headers = exchange.getRequestHeaders();
        if (headers.containsKey("X-Test") && headers.get("X-Test").get(0).equalsIgnoreCase("true")) {
            String dummyResponse = "123\n";
            sendResponse(dummyResponse.getBytes(), exchange);
            return;
        }

        boolean isDebugMode = false;
        if (headers.containsKey("X-Debug") && headers.get("X-Debug").get(0).equalsIgnoreCase("true")) {
            isDebugMode = true;
        }

        long startTime = System.nanoTime();

        byte[] requestBytes = exchange.getRequestBody().readAllBytes();
        byte[] responseBytes = calculateResponse(requestBytes);

        long finishTime = System.nanoTime();

        if (isDebugMode) {
            String debugMessage = String.format("La operación tomó %d nanosegundos", finishTime - startTime);
            exchange.getResponseHeaders().put("X-Debug-Info", Arrays.asList(debugMessage));
        }

        sendResponse(responseBytes, exchange);
    }

    private byte[] calculateResponse(byte[] requestBytes) {

		/*System.out.println("Objeto serializado lado del servidor: "+requestBytes);

		Demo object=(Demo)SerializationUtils.deserialize(requestBytes);
		return String.format("A= %d, B= %s", object.a, object.b).getBytes();*/

        String bodyString = new String(requestBytes);
        String[] stringNumbers = bodyString.split(",");

        BigInteger result = BigInteger.ONE;

        for (String number : stringNumbers) {
            BigInteger bigInteger = new BigInteger(number);
            result = result.multiply(bigInteger);
        }
		System.out.println("Resultado: "+result);

        return String.format("El resultado de la multiplicación es %s\n", result).getBytes();
    }


    private void handleMultRequest(HttpExchange exchange) throws IOException {
        if (!exchange.getRequestMethod().equalsIgnoreCase("get")) {
            exchange.close();
            return;
        }
		String uri=exchange.getRequestURI().toString();
		String factor1=uri.substring(uri.indexOf("=")+1,uri.indexOf("&"));
		String factor2=uri.substring(uri.lastIndexOf("=")+1);

		System.out.println("factor1="+factor1+", factor2="+factor2);

		String task=factor1.trim()+","+factor2.trim();
		String url="http://localhost:8081/task";
        byte[] requestPayload = task.trim().getBytes();  //Se almacen las tareas en un formato de Byte

		CompletableFuture<String> cFuture =new CompletableFuture();
		WebClient client=new WebClient();
		try{
			cFuture=client.sendTask(url, requestPayload);
			String result=cFuture.join();

			sendResponse(result.getBytes(),exchange);

		}catch(CompletionException e){
				System.out.println("catch");
				e.printStackTrace();

		}
		//System.out.println("Future:" +cFuture);


	}

	

    private void handleStatusCheckRequest(HttpExchange exchange) throws IOException {
        if (!exchange.getRequestMethod().equalsIgnoreCase("get")) {
            exchange.close();
            return;
        }

        String responseMessage = "El servidor está vivo\n";

		System.out.println("uri "+exchange.getRequestURI());
        sendResponse(responseMessage.getBytes(), exchange);
    }




    private void sendResponse(byte[] responseBytes, HttpExchange exchange) throws IOException {
        exchange.sendResponseHeaders(200, responseBytes.length);
        OutputStream outputStream = exchange.getResponseBody();
        outputStream.write(responseBytes);
        outputStream.flush();
        outputStream.close();
        exchange.close();
    }
}
