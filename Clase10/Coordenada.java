public class Coordenada {

    private double x, y,magnitud;

    public Coordenada(double x, double y) {

        this.x = x;
        this.y = y;
		double suma;
		suma=Math.pow(x, 2) + Math.pow(y, 2);
		this.magnitud=Math.sqrt(suma);

    }

	public void set(double x,double y){
			this.x=x;
			this.y=y;
	}

    //Metodo getter de x

    public double abcisa( ) { return x; }

 

    //Metodo getter de y

    public double ordenada( ) { return y; }

	public double getMagnitud(){ return magnitud; }

    

    //Sobreescritura del método de la superclase objeto para imprimir con System.out.println( )

    @Override

    public String toString( ) {

        return "[" + x + "," + y + "]";

    }

}
