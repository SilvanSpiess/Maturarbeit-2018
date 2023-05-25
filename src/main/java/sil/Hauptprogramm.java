package sil;

import sil.NiesenFX.TerrainMeshNiesen;
import sil.caster.MyMap;
import sil.caster.RayCaster;
import sil.javaFX.BoxZeichner;
import sil.javaFX.PyramidMesh;

import java.awt.Color;
import javax.swing.JComponent;
import javax.swing.JFrame;

import javafx.application.Application;
import javafx.stage.Stage;

//Box
/* public class Hauptprogramm extends Application {
	public static void main (String [] args) {
		launch(args);
	}

	public void start (Stage Bildschirm) throws Exception {
		BoxZeichner bz = new BoxZeichner();
		bz.init();
		bz.BoxBewegen(Bildschirm);	
	}
} */

//PyramidMesh
/* public class Hauptprogramm extends Application
{
	public static void main(String [] args) {
		launch(args);
	}
	
	public void start(Stage Bildschirm) throws Exception {
		PyramidMesh PM = new PyramidMesh();
		
		try{
			PM.init();
			PM.pyramid(Bildschirm);
		}
		catch (Exception e){
			System.out.print(e.toString());
		}
	}
}*/
//TerrainMesh
/* public class Hauptprogramm extends Application {
	public static void main(String[] args) {
		launch(args);
	}
	
	public void start (Stage Bildschirm) throws Exception {
		MyMap MMp = new MyMap();
		MMp.Init();
		MMp.Heights();
		
		TerrainMeshNiesen TMN = new TerrainMeshNiesen(MMp.nColums, MMp.nRows, MMp.MapHeights());
		try{
			TMN.init();
		}
		catch (Exception e){
			System.out.print(e.toString());
		}
		
		TMN.MyMesh1(Bildschirm);
		TMN.show(Bildschirm);
	}
}
*/	
//RayCaster
public class Hauptprogramm  extends JComponent {

	public static void main (String[]args) {
		JFrame fenster = new JFrame ("Bildschirm");
		fenster.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fenster.setSize(800,800);
		fenster.setBackground(Color.LIGHT_GRAY);
		fenster.setVisible(true);
		
		RayCaster RCr = new RayCaster();
		try{
			RCr.Init();
			RCr.Casten(fenster.getGraphics());
		} 
		catch (Exception e) {
			System.out.print(e.toString());
		}		
		//fenster.add(RCr);
		//fenster.paintAll(fenster.getGraphics());
	}
}
