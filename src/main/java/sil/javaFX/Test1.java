package sil.javaFX;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.stage.Stage;

public class Test1 {
	
	public void darstellen (Stage Bildschirm) {		
		
		Pane pane = new Pane();
		pane.setTranslateX(200);
		pane.setTranslateY(200);

		Box box = new Box(100,100,100);
		box.setMaterial(new PhongMaterial (Color.RED));
		box.setLayoutX(100);
		box.setLayoutY(100);
	}
}
