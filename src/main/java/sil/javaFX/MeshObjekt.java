package sil.javaFX;

import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.MeshView;
import javafx.scene.shape.TriangleMesh;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;

public class MeshObjekt extends TriangleMesh 
{
	BorderPane 		pane; 
	//TriangleMesh 	mesh;
	
	private PerspectiveCamera addCamera(Scene scene) {
        PerspectiveCamera perspectiveCamera = new PerspectiveCamera(false);
        scene.setCamera(perspectiveCamera);
        return perspectiveCamera;
    }

	public MeshObjekt()
	{
		this.pane = new BorderPane();
		//this.mesh = new TriangleMesh();
	}
	
	//public static void main(String[] args) 
	//{
    //    launch(args);
    //}
    public void Generator(Stage Bildschirm) throws Exception {
    pane.setTranslateX(300);
	pane.setTranslateY(300);
	pane.setTranslateZ(0);
   
	//pane.setCenter(mesh);
	
	final MeshView mesh = new MeshView(
			new TriangleMesh()
	);
	mesh.setMaterial(new PhongMaterial(Color.AQUA));
	mesh.setVisible(true);
	mesh.setRotationAxis(Rotate.X_AXIS);
	mesh.getTransforms().add(new Rotate(10, 0, 0, 0, Rotate.Y_AXIS));
	
	float[] points = {
			50, 50, 0, //P0
			50, 100, 0, //P1
			100, 50, 0, //P2
			100, 100, 0 //P3
			};
	float[] texCoords = {
            1, 1, // idx t0
            1, 0, // idx t1
            0, 1, // idx t2
            0, 0  // idx t3
			};
	int [] faces = {
			0, 0, 1, 1, 2, 2,
			2, 2, 3, 3, 0, 0
			};
	
	this.getPoints().setAll(points);
	this.getTexCoords().setAll(texCoords);
	this.getFaces().setAll(faces);

	Scene scene = new Scene(pane, 600, 600);
	//pane.setCenter(mesh);
	addCamera(scene);
	Bildschirm.setTitle("Bildschirm");
	Bildschirm.setScene(scene);
	Bildschirm.show();
	}
}

	


