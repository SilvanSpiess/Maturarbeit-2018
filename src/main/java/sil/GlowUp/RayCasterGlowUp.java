package sil.GlowUp;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.scene.paint.Color;

public class RayCasterGlowUp {
	
	public double currentPosX;		//x Pos. des Vektors
	public double currentPosZ;		//z Pos. des Vektors
	
	double beamLength;			//Faktor des Vektors
	int width = 800;		//Breite des Bildschirms 
	int height = 800;		//Höhe des Bildschirm 
	
	int newMap[][];			//Neue grosse Map mit 0 am Rand
	int heightData[][];

	int nColums;
	int nRows;

	public double angle;						//Winkel, zwischen Lot (auf Bildschirm) und Vektor
	public double DistanceToCamera = 30;		//Distanz Kamera-Bildschirm 519.6
	public double playerwalldist;				//Distanz Kamera-Hit
	public double correctplayerwalldist;		//Korrigierte Distanz, zur vermeidung vom Fish-eye Effekt
	public double LengthRayScreen;				//Länge des Vektors von p-s
	
	public double cameraPosX = 440.0;			//Startposition Sichtpunkt
	public double cameraPosZ = 635.0;			//Startposition Sichtpunkt
	
	public double screenPixelX;					//Startposition Pixel
	public double screenPixelZ;					//Startposition Pixel
	public double deltaX;						//delta X Komponente des Vektors xp-xs
	public double deltaZ;						//delta Z Komponente des Vektors zp-zs
	public double FakX;							//|1| Normierter Vektor
	public double FakZ;							//deltaZ/deltaX (kleiner Faktor um Vektor zu verlängern)
	
	double drawStart;							//Oberster Pnkt der Lienie auf dem Bildschirm
	double drawEnd;								//Unterster Pnkt der Lienie auf dem Bildschirm
	double lineHeight;							//Höhe des zu zeichnenden Objektes

	BorderPane pane;

	public RayCasterGlowUp (int cols, int rows, int[][] heights) {
		nColums = cols;
		nRows = rows;
		heightData = heights;
		this.pane = new BorderPane();
	}
	
	public void Casten () throws Exception {

		//screenPixelZ = cameraPosZ - DistanceToCamera;					
		deltaZ = DistanceToCamera;	
		
		//Iteriert durch jede Bildschirmspalte
		for (int screenIterator = 0; screenIterator <= width; screenIterator++)	{
			if(screenIterator % 10 == 0) System.out.println("frame " + screenIterator);
			screenPixelX = cameraPosX + (double)screenIterator - ((double)width/2.0);			
			deltaX = cameraPosX - screenPixelX;

			if(deltaX < 0) FakX = 1;			
			if(deltaX > 0) FakX = -1;				
			if(deltaX == 0) FakX = 0;			
			FakZ = deltaZ / deltaX;
			if(FakZ > 0) FakZ = -FakZ;
			
			LengthRayScreen = Math.sqrt((deltaX)*(deltaX)+(deltaZ)*(deltaZ));
			
			beamLength = cameraPosZ;
			//Erhöht Faktor (s) des "Strahls"
			while(beamLength > 0) {
				currentPosX = cameraPosX - (beamLength * FakX);
				currentPosZ = cameraPosZ + (beamLength * FakZ);
				
				if(currentPosX < 0 || currentPosX >= 875 || currentPosZ < 0) beamLength--;

				else if(currentPosZ >= 600) break;

				else if(heightData[(int)currentPosX][((int)currentPosZ)] > 0) {
					
					playerwalldist = Math.sqrt(((currentPosX - cameraPosX)*(currentPosX - cameraPosX))+((currentPosZ - cameraPosZ)*(currentPosZ - cameraPosZ)));

					lineHeight = ((heightData[(int)currentPosX][(int)currentPosZ]/4) * LengthRayScreen / playerwalldist); //Integer für Kompensation der Entfernung
					if(lineHeight < 0) lineHeight *= (-1);

					drawStart = height - lineHeight;
					if(drawStart < 0) drawStart = 0;

					drawEnd = height-1;

					Line line = new Line(screenIterator, drawStart, screenIterator, drawEnd);
					if((heightData[(int)currentPosX][((int)currentPosZ)] > 0)&&(heightData[(int)currentPosX][((int)currentPosZ)] <= 1200)){line.setStroke(Color.LIMEGREEN);}
					else if((heightData[(int)currentPosX][((int)currentPosZ)] > 1200)&&(heightData[(int)currentPosX][((int)currentPosZ)] <= 1500)){line.setStroke(Color.YELLOWGREEN);}
					else if((heightData[(int)currentPosX][((int)currentPosZ)] > 1500)&&(heightData[(int)currentPosX][((int)currentPosZ)] <= 2000)){line.setStroke(Color.LIGHTGRAY);}
					else if((heightData[(int)currentPosX][((int)currentPosZ)] > 2000)&&(heightData[(int)currentPosX][((int)currentPosZ)] <= 2500)){line.setStroke(Color.FLORALWHITE);}
					else if((heightData[(int)currentPosX][((int)currentPosZ)] > 2500)&&(heightData[(int)currentPosX][((int)currentPosZ)] <= 3500)){line.setStroke(Color.RED);}					
					
					pane.getChildren().add(line);
					
		            beamLength--;					
				}
				else {
					beamLength--;
				}
			}		
		}
	}
	public void show(Stage stage) throws Exception {
	
		Scene scene = new Scene(pane, 800, 800, Color.LIGHTBLUE);
		stage.setTitle("Bildschirm Niesen");
		stage.setScene(scene);
		stage.show();

	}
}