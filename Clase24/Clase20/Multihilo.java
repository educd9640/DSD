import java.io.*;
class Multithilodemo extends Thread {
	private String ip;

	public Multithilodemo(String dirip){
			this.ip=dirip;
	}
	
    public void run()
    {
        try {
            
            Runtime rt = Runtime.getRuntime();

			String[] commands = {"curl", "-v", "--header", "X-Debug:true", "--data", "1757600,IPN", ip};

            Process proc = rt.exec(commands);
			
			BufferedReader stdInput = new BufferedReader(new InputStreamReader(proc.getInputStream()));
			String s=null;
			while( (s=stdInput.readLine()) != null){
					System.out.println(s);
			}

			//stdInput.lines().forEach(System.out::println);
        }
        catch (Exception e) {
            
            System.out.println("Exception is caught");
        }
    }
}
 

public class Multihilo {
    public static void main(String[] args)
    {

		long inicio=System.nanoTime();
        Multithilodemo object = new Multithilodemo(args[0]);
        Multithilodemo object2 = new Multithilodemo(args[0]);
        Multithilodemo object3 = new Multithilodemo(args[0]);
        Multithilodemo object4 = new Multithilodemo(args[0]);
        
		
		
		object.start();
		object2.start();
		object3.start();
		object4.start();

		try{
				object.join();
				object2.join();
				object3.join();
				object4.join();

		} catch (Exception e){
				e.printStackTrace();
		}

        
		long fin =System.nanoTime();
		long tiempo=fin-inicio;
		System.out.println("Tiempo en segundos " + tiempo );
    }
}
