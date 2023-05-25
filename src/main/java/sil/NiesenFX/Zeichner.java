package sil.NiesenFX;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;

import javax.swing.JComponent;
/*
https://books.google.ch/books?id=hKRPAgAAQBAJ&pg=PA118&lpg=PA118&dq=graphikbibliotheken+in+java&source=bl&ots=k
CGpXhI8hI&sig=8lt2_VArVkrQKa00hjBvOmkje1g&hl=de&sa=X&ved=0ahUKEwjq0qftoKPaAhWusaQKHdsbAFAQ6AEIKTAA#v=onep
age&q=graphikbibliotheken%20in%20java&f=false
Link für Graphikbibliothekmaterial
*/
//https://www.youtube.com/watch?v=n51zY4SXjs0
//Link zu Video über Transitionen von 3D-Objekten

//https://www.youtube.com/watch?v=CNbV2_md6rw
//https://www.youtube.com/watch?v=G8oti18k08s (Folge 11)

//Youtube Channel für 3D-Graphics
//https://www.youtube.com/channel/UCUkRj4qoT1bsWpE_C8lZYoQ

//Netlamp, 3D auf javaFX
//https://www.youtube.com/watch?v=K8mdqk66SBQ&index=124&list=PLm84WV9Yq4HOTInJk7m98jHler0UtrcxG
public class Zeichner extends JComponent
{
	int nColums = 0;
	int nRows = 0;
	int [][] arrHeights;
	//Konstruktor
	public Zeichner (int cols, int rows, int[][] heights) { 
		nColums = cols;
		nRows = rows;
		arrHeights = heights;
	}
	
		//paint Klasse
		private static final long serialVersionUID = 1L;

		public void paintComponent(Graphics g) {
				Graphics2D g2 = (Graphics2D) g;
				
				//super.paintComponent(g);
				//this.setBackground(Color.WHITE)
				System.out.print("paint");
				
				int nPoints = (nColums / 10)+2;
				int xPoints [] = new int [nPoints];
				int zPoints [] = new int [nPoints];
				
				//Initailisieren der Farben bzw. additions-, subtraktionsfaktor
				int r1 = 255;
				int g1 = 255;
				int b1 = 255;
				int u1 = 20;
				
				
				//Gantrisch benennen
				Color black = new Color (0, 0, 0);
				g2.setColor(black);
				g2.drawString(("Gantrisch?! =>"), 600, 290);
				
				//Schwarze Linie auf H�he 300
				/*
				Color black = new Color (0, 0, 0);
				g2.setColor(black);
				g2.drawLine(0, 501, nPoints, 501);
				*/
				
				//2 Punkte f�r geeignetes Darstellen des Polygons
				xPoints[0] = 0;
				zPoints[0] = 800;
				xPoints[nPoints-1] = nPoints-1;
				zPoints[nPoints-1] = 800;
				
				//Erste Schlaufe, bestimmt wie viele Polygons gezeichnet werden.
				for (int b = nRows-1; b>0; b--){
					if ( b % 600 == 0 )
				//for (int b = 0; b<1000; b++){
					//if ( b % 100 == 0 )		
					{
						System.out.println(" b=" + b);
						r1 = r1 - u1;
						g1 = g1 - u1;
						b1 = b1 - u1;
						Color red = new Color(r1, g1, b1);
							
							//Zweite Schlaufe, zeichnet Polygon
							for ( int a = 0; a<nColums-2; a++ ){
							if ( a % 10 == 0 )
							{									
								int index = a/10+1;
								xPoints[index] = a/10;
								//zPoints[index] = (1800-arrHeights[index][b]);
								zPoints[index] = (2400-arrHeights[index][b]);
								
								System.out.print(" x=" + index + " z=" + zPoints[index]);
								Polygon poly1 = new Polygon (xPoints, zPoints, nPoints);
								g2.setColor(red);
								g2.fillPolygon(poly1);
								g2.draw(poly1);								
							}
						}					
					}
				}	
			}		
	}
