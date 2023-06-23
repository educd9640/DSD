import java.util.*;

public class PoligonoIList {
	//Declaración de la Lista
        List<Coordenada> vertices = new  ArrayList<Coordenada>();

    public void create(int ii){
			for(int i=0; i < ii; i++)
					anadeVertice(new Coordenada(rando(),rando()));
    }

	public void ordenaVertices(){
		Collections.sort(vertices,new SortMagnitud());
	}// Sortea los vertices ordenandolos por la magnitud


    public void anadeVertice(Coordenada a){
    	    this.vertices.add(a); //Se agregan las nuevas coordenadas al final de la lista
    }
    
    public void list( ) {
        for (Coordenada v :this.vertices)//Ocupando For Each se imprime la lista
            System.out.println(v+ " M:"+v.getMag());
    }
    
    public String toString(String str) {
        str = "";
        for(Coordenada v :this.vertices)//Ocupando For Each se asignan los elemtos de la lista a una variable string
            str += v + "\n";
        return str;
    }

	public double rando(){
		Random rnd=new Random();
		double aux;
		do{
			aux=rnd.nextDouble()*100;
		}while(aux>100.0);

		if(rnd.nextInt(2)==1){
				return aux;
		}else{
				return (aux)*(-1);
		}
	}
    
}
