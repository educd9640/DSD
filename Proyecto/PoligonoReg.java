/*PROYECTO 2
 * EDUARDO CALLEJAS DIAZ	4CM12
 * */ 
import java.util.*;

public class PoligonoReg {

	private int[] xs;
	private int[] ys;
	private int nvert;
	private int centrox;
	private int centroy;
	private int radio;
	private double area;
	private double angulo;
	double PI= Math.PI;

    public PoligonoReg(int nvert){

			this.nvert=nvert;
			xs=new int[nvert+1];
			ys=new int[nvert+1];
			Random rnd=new Random();
			centrox=rnd.nextInt(1000)+200;
			centroy=rnd.nextInt(500)+200;
			radio=rnd.nextInt(100)+25;

			Coordenadas();
			
			calculaArea();
			calculaAngulo();
    }
	public void Coordenadas(){
			for(int i=0; i <= nvert; i++){
					if(i==0){
							xs[i]=(int)(centrox + radio);
							ys[i]=(int)(centroy);
					}else{
							xs[i]=(int)(centrox + radio * Math.cos(i * 2 * PI / nvert));
							ys[i]=(int)(centroy - radio * Math.sin(i * 2 * PI / nvert));
					}
					//System.out.println(xs[i]+" " +ys[i] + " "+i + "radio-"+ radio);

			}

	}

	public void calculaArea(){
		double lado,perimetro,apotema;
		lado=Math.sqrt( Math.pow((double)(xs[1] - xs[0]), 2) + Math.pow((double)(ys[1] - ys[0]),2 ));
		perimetro=lado*nvert;
		
		apotema=Math.sqrt( Math.pow((double)(radio), 2) - Math.pow( lado / 2 , 2 ));
		
		this.area=(perimetro * apotema)/2;

	}

	public void calculaAngulo(){
			angulo=360/nvert;
	}

	public void setCentro(int cenX, int cenY){
			this.centrox=cenX;
			this.centroy=cenY;
			Coordenadas();

	}

	public int getNv(){return nvert;}
	
	public double getA(){return area;}

	public int[] getX(){return xs;	}

	public int[] getY(){return ys;  }

	public double getAng() {return angulo;}    

    
}
