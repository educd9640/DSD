public class PruebaPoligonoIList{
    public static void main(String [] args){
        PoligonoIList poligono = new PoligonoIList();
        poligono.create(10);
        poligono.list();
        poligono.toString();
		System.out.println("");
		poligono.ordenaVertices();
        poligono.list();
    }

}
