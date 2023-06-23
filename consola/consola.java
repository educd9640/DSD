public class consola{
		public static void main(String[] args) {
				System.out.println("Tamaño"+ args.length);

				try{
						for (int i = 0; i < args.length; i++) {
								Thread.sleep(500);
								System.out.println(args[i]);
					
						}
				}catch(InterruptedException e){
						e.printStackTrace();
				}
				
				

		}
}

