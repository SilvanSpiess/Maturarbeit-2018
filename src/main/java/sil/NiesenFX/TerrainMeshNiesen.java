package sil.NiesenFX;

import javafx.geometry.Point3D;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.PointLight;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Cylinder;
import javafx.scene.shape.DrawMode;
import javafx.scene.shape.MeshView;
import javafx.scene.shape.TriangleMesh;
import javafx.stage.Stage;

public class TerrainMeshNiesen 
{
	public Group root = new Group();
	PerspectiveCamera cam = new PerspectiveCamera();
	TriangleMesh Mesh11 = new TriangleMesh();
	MeshView MyMesh1 = new MeshView(Mesh11);
	PointLight PL = new PointLight();

	Point3D p1 = new Point3D(1, 0, 0);
	Point3D p2 = new Point3D(0, 1, 0);
	Point3D p3 = new Point3D(0, 0, 1);
	Point3D p4 = new Point3D(1, 0, 1);
	
	Cylinder ZyX = new Cylinder();		//X-Achse
	Cylinder ZyY = new Cylinder();		//Y-Achse
	Cylinder ZyZ = new Cylinder();		//Z-Achse
	
	int a1 = -4;
	int a2 = -1;
	int a3 = -3;
	int b1 = -4;
	int b2 = -2;
	int b3 = -1;									

	int nColums = 0;
	int nRows = 0;
	int[][]mapHeights;
	
	public TerrainMeshNiesen (int cols, int rows, int[][] heights)
	{
		nColums = cols;
		nRows = rows;
		mapHeights = heights;
	}
	
	public void init () throws Exception 
	{		
		root.getChildren().add(MyMesh1);

		//Y-Achse
		ZyX.setRadius(1);
		ZyX.setHeight(10000);
		ZyX.setMaterial(new PhongMaterial(Color.BLACK));
		ZyX.setRotationAxis(p1);
		//ZyX.setRotate(90);

		//X-Achse
		ZyY.setRadius(1);
		ZyY.setHeight(10000);
		ZyY.setMaterial(new PhongMaterial(Color.BLUE));
		ZyY.setRotationAxis(p3);
		ZyY.setRotate(90);

		//Z-Achse
		ZyZ.setRadius(1);
		ZyZ.setHeight(10000);
		ZyZ.setMaterial(new PhongMaterial(Color.GREEN));
		ZyZ.setRotationAxis(p1);
		ZyZ.setRotate(90);
		
		PL.setTranslateX(8000);
		PL.setTranslateY(-15000);
		PL.setTranslateZ(10000);
		
		root.getChildren().add(ZyX);
		root.getChildren().add(ZyY);
		root.getChildren().add(ZyZ);
		root.getChildren().add(PL);
		
		cam.setTranslateX(8000);
		cam.setTranslateY(-10500);
		cam.setTranslateZ(-18000);
		cam.setRotationAxis(p1);
		cam.setRotate(-20);	
		}
	
	public void MyMesh1(Stage Bildschirm) throws Exception
	{
		int nPoints1 = (((nColums*2+1)-200)*((nRows*2+1)-200));
		int yPoints1P1 [] = new int [nPoints1];
		int yPoints1P2 [] = new int [nPoints1];
		int yPoints1P3 [] = new int [nPoints1];
		int yPoints1P4 [] = new int [nPoints1];
		
		//1. Loop f�r jede Zeile
		for(int z11 = 0; z11 <= (nRows*2+1)-200; z11++)
		{
			//2. Loop f�r jede Spalte (Punkt auf Zeile)
			for(int x11 = 0; x11 <= (nColums*2+1)-200; x11++)
			{
				if((x11 % 100 == 0)&&(z11 % 100 == 0))
				{
					a1 += 4;
					a2 += 4;
					a3 += 4;
					b1 += 4;
					b2 += 4;
					b3 += 4;
					
					yPoints1P1[x11] = (mapHeights[x11][z11]);
					yPoints1P2[x11] = (mapHeights[x11 + 100][z11]);
					yPoints1P3[x11] = (mapHeights[x11][z11 + 100]);
					yPoints1P4[x11] = (mapHeights[x11 + 100][z11 + 100]);
					
					Mesh11.getTexCoords().addAll(0,0);
					
					if ((yPoints1P1[x11]) <= 0){
						yPoints1P1[x11] = (mapHeights[x11-200][z11-200]);
					}
					if ((yPoints1P2[x11]) <= 0){
						yPoints1P2[x11] = (mapHeights[x11-100][z11-200]);
					}
					if ((yPoints1P3[x11]) <= 0){
						yPoints1P3[x11] = (mapHeights[x11-200][z11-100]);
					}
					if ((yPoints1P4[x11]) <= 0){
						yPoints1P4[x11] = (mapHeights[x11-100][z11-100]);
					}
					//Alle Punkte eines Quadrats
					Mesh11.getPoints().addAll(
							(x11),  			(-yPoints1P1[x11])*2,	(z11),       	//Oberer linker Punkt   P1
							((x11) + 100),   	(-yPoints1P2[x11])*2,   (z11),    		//Oberer rechter Punkt  P2
							(x11),    			(-yPoints1P3[x11])*2,  	((z11) + 100), 	//Unterer linker Punkt  P3
							((x11) + 100),  	(-yPoints1P4[x11])*2, 	((z11) + 100)   //Unterer rechter Punkt P4
							);
					
					//Fl�che der beiden Dreiecke in einem Quadrat
					Mesh11.getFaces().addAll(
							a1,0, a2,0, a3,0,
							b1,0, b2,0, b3,0,
							a3,0, a2,0, a1,0,
							b3,0, b2,0, b1,0
							);
					
					PhongMaterial mat = new PhongMaterial(Color.FORESTGREEN);
					MyMesh1.setDrawMode(DrawMode.FILL);
					MyMesh1.setMaterial(mat);
				}
			}
		}
	}
	
	public void show (Stage Bildschirm) throws Exception
	{
		Scene scene = new Scene(root, 1200, 800, Color.LIGHTBLUE);
		scene.setCamera(cam);
		
		Bildschirm.setTitle("Bildschirm Niesen");
		Bildschirm.setScene(scene);
		Bildschirm.show();
	}
}
	