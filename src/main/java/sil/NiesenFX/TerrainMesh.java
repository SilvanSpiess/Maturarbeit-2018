package sil.NiesenFX;

import javafx.geometry.Point3D;
import javafx.scene.Camera;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.DrawMode;
import javafx.scene.shape.MeshView;
import javafx.scene.shape.TriangleMesh;
import javafx.stage.Stage;
import javafx.scene.paint.Color;


public class TerrainMesh {
	
	public TriangleMesh Mesh11 = new TriangleMesh();
	public Group root = new Group();
	
	MeshView Mesh1 = new MeshView(Mesh11);

	Point3D p3 = new Point3D(1, 1, 0);
	BorderPane pane = new BorderPane();
	Camera cam = new PerspectiveCamera();
	
	int nColums = 0;
	int nRows = 0;
	int[][]arrHeights;
	
	public TerrainMesh (int cols, int rows, int[][] heights) {
		nColums = cols;
		nRows = rows;
		arrHeights = heights;
	}
	public void init () throws Exception {
		Mesh1.setMaterial(new PhongMaterial(Color.GREY));
		Mesh1.setDrawMode(DrawMode.FILL);
		Mesh1.setVisible(true);
		
		root.getChildren().add(Mesh1);
		
		cam.setTranslateX(1000);
		cam.setTranslateY(1000);
		cam.setTranslateZ(3000);
		cam.setRotationAxis(p3);
		cam.setRotate(-45);
	}
	
	public void Terrain (Stage Bildschirm) throws Exception	{
		int nPoints1 = (nColums)+2;
		int zPoints1P1 [] = new int [nPoints1];
		int zPoints1P2 [] = new int [nPoints1];
		int zPoints1P3 [] = new int [nPoints1];
		int zPoints1P4 [] = new int [nPoints1];		
		
		//oberer Punkt bei den Dreiecken (unteres Dreieck im Quadrat)
		for(int y11 = 0; y11 < nRows - 1; y11++){
			System.out.println("y11= " + y11);
			System.out.println("works2.0!");	
				
			for(int x11 = 0; x11 < nColums - 1; x11++) {
				System.out.println("x11= " + x11);
			
				zPoints1P1[x11] = (arrHeights[x11][y11]);
				zPoints1P2[x11] = (arrHeights[x11 + 1][y11]);
				zPoints1P3[x11] = (arrHeights[x11][y11 + 1]);
				zPoints1P4[x11] = (arrHeights[x11 + 1][y11 + 1]);

				//TexCoords vom 1. Dreieck
				Mesh11.getTexCoords().addAll(0, 0);
				
				//Punkte vom 1. Dreieck
				Mesh11.getPoints().addAll(
						x11,       y11,       zPoints1P1[x11], //Oberer linker Punkt   P1
						x11,       y11 + 1,   zPoints1P2[x11], //Unterer linker Punkt  P2
						x11 + 1,       y11,   zPoints1P3[x11], //Oberer rechter Punkt  P3
						x11 + 1,   y11 + 1,   zPoints1P4[x11]  //Unterer rechter Punkt P4
				);
				//Fläche vom 1. Dreieck
				Mesh11.getFaces().addAll(
						0,0, 1,0, 3,0,
						0,0, 3,0, 2,0
				);
			}
		}			
		
		Scene scene = new Scene(root, 600, 600, Color.ANTIQUEWHITE);
		scene.setCamera(cam);
		Bildschirm.setScene(scene);
		Bildschirm.show();		
	}
}
