public class Ejercicio4{
		public static void main(String[] args){
				int [] num =new int[20];
				for(int i=0;i<20;i++){
						if(i<3){
								System.out.println((i+1)+") "+1);
								num[i]=1;
						}else{
								int fibo=num[i-1]+num[i-2]+num[i-3];
								num[i]=fibo;
								System.out.println((i+1)+") "+ fibo);
						}

				}
		}
}

