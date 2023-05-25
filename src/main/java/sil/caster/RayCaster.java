package sil.caster;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.util.ArrayList;

import javax.swing.JComponent;

public class RayCaster extends JComponent{
	
	private static final long serialVersionUID = 1L;
	
	public double posx;		//x Pos. des Vektors
	public double posz;		//z Pos. des Vektors
	
	double s;				//Faktor des Vektors
	int width = 800;		//Breite des Bildschirms 
	int height = 800;		//Höhe des Bildschirm 
	
	int newMap[][];			//Neue grosse Map mit 0 am Rand
	int x;					//Schlaufe
	int z;					//Schlaufe
	int a;					//Schlaufe
	int b;					//Schlaufe
	
	int r1;					//Rot
	int g1;					//Grün
	int b1;					//Blau
	
	MyMap MMp = new MyMap();
	
	//private double viewingangle = 60.0;		//Blickwinkel
	public double angle;						//Winkel, zwischen Lot (auf Bildschirm) und Vektor
	public double DistanceToCamera = 519.6;		//Distanz Kamera-Bildschirm
	public double playerwalldist;				//Distanz Kamera-Hit
	public double correctplayerwalldist;		//Korrigierte Distanz, zur vermeidung vom Fish-eye Effekt
	public double LengthRayScreen;				//Länge des Vektors von p-s
	
	public double xs = 10000.0;						//Startposition Sichtpunkt
	public double zs = 0.0;					//Startposition Sichtpunkt
	
	public double xp;							//Startposition Pixel
	public double zp;							//Startposition Pixel
	public double deltaX;						//delta X Komponente des Vektors xp-xs
	public double deltaZ;						//delta Z Komponente des Vektors zp-zs
	public double FakX;							//|1| Normierter Vektor
	public double FakZ;							//deltaZ/deltaX (kleiner Faktor um Vektor zu verlängern)
	
	double drawStart;							//Oberster Pnkt der Lienie auf dem Bildschirm
	double drawEnd;								//Unterster Pnkt der Lienie auf dem Bildschirm
	double lineHeight;							//Höhe des zu zeichnenden Objektes
	
	int xSize = 20000;
	int ySize = 20000;

	//public ArrayList<Polygon> polygons = new ArrayList<Polygon>();
	
	public void Init() throws Exception	{
		MMp.Init();
		MMp.Heights();
		
		newMap = new int[xSize][ySize];
		
		//Neue Map erstellen (20000x20000, mit Werten 0)
		for(x = 0; x < xSize; x++) {
			for (z = 0; z < ySize; z++)	{
				newMap[x][z] = 0;
			}
		}
		
		//Neu erstellte NewMap mit den Werten von MyMap abfüllen (MyMap in Mitte von NewMap platzieren)
		for (a = 0; a < xSize; a++)	{
			for (b = 0; b < ySize; b++)	{
				if ((a >= 1250 && a < 18750) && (b >= 4000 && b < 16000)) {
					newMap[a][b] = MMp.MapHeights()[a-1250][b-4000];
				}
			}
		}
		System.out.println("Init works!");
	}
	
	public void Casten (Graphics g) throws Exception {

		Graphics2D g2 = (Graphics2D)g;
		zp = zs - DistanceToCamera;					
		deltaZ = DistanceToCamera;	
		
		//Iteriert durch jede Bildschirmspalte
		for (int u = 1; u < width + 2; u++)	{
			if(u % 10 == 0) System.out.println("frame " + u);
			xp = xs + ((double)u - ((double)width/2.0));			
			deltaX = xp - xs;

			if(deltaX < 0) {
				FakX = 1;
			}
			if(deltaX > 0) {
				FakX = -1;
			}	
			if(deltaX == 0) {
				FakZ = 0;
			} else FakZ = deltaZ / deltaX;

			if (FakZ < 0) {
				FakZ *= (-1);
			}
			//System.out.println(" FakX= " + FakX + " FakZ= " +  FakZ);
			
			LengthRayScreen = Math.sqrt((deltaX)*(deltaX)+(deltaZ)*(deltaZ));
			
			s = xSize/3;
			//Erhöht Faktor (s) des "Strahls"
			while(s > 0) {
				posx = xs - (s * FakX);
				posz = zs + (s * FakZ);
				
				//System.out.println(s);
				//System.out.println(" posx= " + posx + ", posz= " + posz);
				if (posz < 1.0 || posz > 20000) {
					s--;
					continue;
				}
				if((posx < 1.0) || (posx >= xSize+1)) {
					s--;
					continue;
				}				
				if(newMap[(int)posx][((int)posz)-1] <= 0) {
					s--;
					continue;
				}				
				if(newMap[(int)posx][((int)posz)-1] > 0) {
					
					//System.out.println(" Map= " + newMap[(int)posx][((int)posz)-1]);
					playerwalldist = Math.cbrt(((posx - xs)*(posx - xs))+((posz - zs)*(posz - zs)));
					if(playerwalldist < 0) {
						playerwalldist *= (-1);
					}
					lineHeight = (newMap[(int)posx][(int)posz] * LengthRayScreen / 10 / playerwalldist); //Integer für Kompensation der Entfernung
					if(lineHeight < 0) {
						lineHeight *= (-1);
					}
					if(s % 100 == 0) System.out.println("Lineheight");
					drawStart = height - lineHeight;
						if(drawStart < 0)
						drawStart = 0;
						drawEnd = height-1;
					
					if((newMap[(int)posx][((int)posz)-1] >    0)&&(newMap[(int)posx][((int)posz)-1] <= 1200)){r1 = 0; g1 = 102; b1 = 0;}
					else if((newMap[(int)posx][((int)posz)-1] > 1200)&&(newMap[(int)posx][((int)posz)-1] <= 1500)){r1 = 102; g1 = 102; b1 = 0;}
					else if((newMap[(int)posx][((int)posz)-1] > 1500)&&(newMap[(int)posx][((int)posz)-1] <= 2000)){r1 = 205; g1 = 205; b1 = 0;}
					else if((newMap[(int)posx][((int)posz)-1] > 2000)&&(newMap[(int)posx][((int)posz)-1] <= 2500)){r1 = 190; g1 = 190; b1 = 190;}
					else if((newMap[(int)posx][((int)posz)-1] > 2500)&&(newMap[(int)posx][((int)posz)-1] <= 3500)){r1 = 255; g1 = 255; b1 = 204;}					
					
					Polygon poly = new Polygon();
					poly.addPoint(u, (int)drawStart);
					poly.addPoint(u+1, (int)drawStart);
					poly.addPoint(u+1, (int)drawEnd);
					poly.addPoint(u, (int)drawEnd);
					
					//Shape line = new Line2D.Double(u, drawStart, u, drawEnd);
					Color color = new Color(r1, g1, b1);
					
			        g2.setColor(color);
					g2.draw(poly);
					
		            s--;
					continue;
					
					}
					else {
						s--;
						continue;
					}
				}		
			}
		}
	}
