/*PROYECTO 2
 * EDUARDO CALLEJAS DIAZ	4CM12
 * */ 
import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Random;
import java.util.ArrayList;
import java.util.Collections;
public class SimpleGui2 extends JFrame{
	public static List<PoligonoReg> poligonos=new ArrayList<PoligonoReg>();
	private static int n,ck=0,lt=0;
	private static int centX,centY;
	Panel p = new Panel();

	public static void main(String[] args){ 
	n=Integer.parseInt(args[0]);
	SimpleGui2 gui = new SimpleGui2(); 
	gui.setVisible(true);

		try{
				Thread.sleep(3000);
				Collections.sort(poligonos,new SortArea());
				ck=1;
				System.out.println(poligonos.size());
				for(int i=0;i<poligonos.size();i++){
						gui.repaint();
						lt=i;
						Thread.sleep(500);
				}


		}catch(InterruptedException e){
				e.printStackTrace();
				System.out.println(e);
		}
	}
 
	public SimpleGui2(){
		setSize(1900, 900);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		centX=getWidth()/2;
		centY=getHeight()/2;
		add(p);
	 }
	

	private class Panel extends JPanel {
			
		public void paintComponent(Graphics g){
				if(ck==1){
						System.out.println("Angulo del poligono "+poligonos.get(lt).getAng() );
						poligonos.get(lt).setCentro(centX, centY);
						g.drawPolygon(poligonos.get(lt).getX(),poligonos.get(lt).getY(),poligonos.get(lt).getNv()+1);
				}else{
					g.setColor(Color.blue);
					Random rnd=new Random();
					poligonos.clear();
					for (int i = 0; i < n; i++) {
					poligonos.add(new PoligonoReg(rnd.nextInt(6)+3));
					}
					for (PoligonoReg poli : poligonos) {
							g.drawPolygon(poli.getX(),poli.getY(),poli.getNv()+1);
					}
				}


		}
	}
}
