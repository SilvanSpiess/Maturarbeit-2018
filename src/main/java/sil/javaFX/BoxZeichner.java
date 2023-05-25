package sil.javaFX;

import javafx.animation.RotateTransition;
import javafx.geometry.Point3D;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;

public class BoxZeichner {

	PerspectiveCamera cam = new PerspectiveCamera(false);
	BorderPane 			pane;
	Box 				box;
	
	//Konstruktor für Box
	public BoxZeichner() {
		this.box = new Box(100,100,100);
		this.pane = new BorderPane();
	}
	
	public void init () throws Exception {
		box.setMaterial(new PhongMaterial(Color.RED));
		box.setManaged(false);
		pane.setCenter(box);
		
		box.setTranslateX(250);
		box.setTranslateY(300);		
		
		cam.setTranslateX(0);
		cam.setTranslateY(0);
		cam.setTranslateZ(0);
		
		//Bewegung Camera
		RotateTransition Bewegung = new RotateTransition(Duration.seconds(1),cam);
		Bewegung.setCycleCount(1);
		Bewegung.setFromAngle(0);
		Bewegung.setToAngle(-30);
		Bewegung.setAutoReverse(true);
		Bewegung.setAxis(Rotate.X_AXIS);
		Bewegung.play();
		
	}
	
	//Methode um Box zu bewegen	
	public void BoxBewegen (Stage Bildschirm)throws Exception {
				
		Point3D p3 = new Point3D(0, 1, 0);
		box.setRotationAxis(p3);
		
		Slider SR3 = new Slider(0, 360, 180);
		SR3.setShowTickLabels(true);
		SR3.setShowTickMarks(true);
		SR3.setMajorTickUnit(60);
		SR3.setMinorTickCount(60);
		SR3.valueProperty().bindBidirectional(box.rotateProperty());
		pane.setBottom(SR3);
		
		RotateTransition Bewegung = new RotateTransition(Duration.seconds(1),SR3);
		Bewegung.setCycleCount(1);
		Bewegung.setFromAngle(0);
		Bewegung.setToAngle(-30);
		Bewegung.setAutoReverse(true);
		Bewegung.setAxis(Rotate.X_AXIS);
		Bewegung.play();
		
		Scene scene = new Scene(pane, 600, 600);
		scene.setCamera(cam);
		
		Bildschirm.setTitle("Bildschirm");
		Bildschirm.setScene(scene);
		Bildschirm.show();		
	}
		
		/*
		//Slider zum Bewegen des Objekts
		Slider sl1 = new Slider();
		sl1.setMax(600);
		sl1.valueProperty().bindBidirectional(box.translateXProperty());
		pane.setRight(sl1);
		
		Slider sl2 = new Slider();
		sl2.setMax(600);
		sl2.valueProperty().bindBidirectional(box.translateYProperty());
		pane.setLeft(sl2);
		*/
	
		/*
		//Bewegung des Objekts (ohne Slider)
		box.getTransforms().add(new Rotate(10, 0, 0, 0, Rotate.X_AXIS));
		box.getTransforms().add(new Rotate(10, 0, 0, 0, Rotate.Y_AXIS));
		box.getTransforms().add(new Rotate(10, 0, 0, 0, Rotate.Z_AXIS));
		pane.getChildren().add(box);
		
		box.rotateProperty();
		box.rotationAxisProperty();
		
		RotateTransition rotra = new RotateTransition(Duration.seconds(2), box);
		rotra.setAxis(Rotate.Y_AXIS);
		rotra.setToAngle(90);
		rotra.setCycleCount(2);
		rotra.setAutoReverse(true);
		rotra.play();
		*/	
}

