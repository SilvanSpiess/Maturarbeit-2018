package sil.javaFX;

import javafx.geometry.Point3D;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.PointLight;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.DrawMode;
import javafx.scene.shape.MeshView;
import javafx.scene.shape.TriangleMesh;
import javafx.stage.Stage;

public class PyramidMesh {
	public TriangleMesh pyramidMesh = new TriangleMesh();
	public Group root = new Group();
	PerspectiveCamera cam = new PerspectiveCamera();
	PointLight PL = new PointLight(); 
	PhongMaterial mat = new PhongMaterial();
	
	Point3D p1 = new Point3D(1, 0, 0);
	Point3D p2 = new Point3D(0, 1, 0);
	Point3D p3 = new Point3D(0, 0, 1);
	
	public void init () throws Exception {
		MeshView pyramid = new MeshView(pyramidMesh);
		
		PhongMaterial mat = new PhongMaterial(Color.RED);
		pyramid.setDrawMode(DrawMode.FILL);
		pyramid.setTranslateX(0);
		pyramid.setTranslateY(0);
		pyramid.setTranslateZ(0);
		pyramid.setMaterial(mat);
		root.getChildren().add(pyramid);
		
		PL.setTranslateX(200);
		PL.setTranslateY(-100);
		PL.setTranslateZ(0);
		PL.setColor(Color.WHITE);
		root.getChildren().add(PL);
		
		cam.setRotationAxis(p1);
		cam.setRotate(10);
		cam.setTranslateX(-300);
		cam.setTranslateY(-300);
		cam.setTranslateZ(-150);
		cam.setNearClip(0.1);
		cam.setFarClip(1000.0);
		cam.setFieldOfView(55);
	}
	
	public void pyramid(Stage Bildschirm) throws Exception {
		//TexCoords der Dreiecke
		pyramidMesh.getTexCoords().addAll(0,0);
		
		//Points der Dreiecke
		pyramidMesh.getPoints().addAll(
				0,   	0,     		 0,			//P1
				0,    	250, 		-150,		//P2
				-150, 	250,    	0,			//P3
				150,  	250,    	0,			//P4
				0,    	250,  		150			//P5
				);
		
		//Erstellen der Dreiecke
		pyramidMesh.getFaces().addAll(
				0,0, 2,0, 1,0,  //Front Links
				0,0, 1,0, 3,0,  //Front Rechts
				0,0, 3,0, 4,0,  //Hinten Rechts
				0,0, 4,0, 2,0,  //Hinten Links
				4,0, 2,0, 1,0,  //Unten Hinten
				1,0, 3,0, 4,0   //Unten Vorne
				);
		
	    Scene scene = new Scene(root, 600, 600, Color.ANTIQUEWHITE);
	    scene.setCamera(cam);
	    
	    Bildschirm.setTitle("Bildschirm");
	    Bildschirm.setScene(scene);
	    Bildschirm.show();
	}
}
