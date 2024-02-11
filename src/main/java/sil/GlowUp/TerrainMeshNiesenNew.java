package sil.GlowUp;

import javafx.geometry.Point3D;
import javafx.scene.Camera;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.PointLight;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Cylinder;
import javafx.scene.shape.DrawMode;
import javafx.scene.shape.MeshView;
import javafx.scene.shape.TriangleMesh;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;

class RotateCamera extends Group {

	public final Camera camera;
	final Translate xTranslate = new Translate();
	final Translate yTranslate = new Translate();

	public RotateCamera() {
		camera = new PerspectiveCamera(true);
		camera.setFarClip(10000);
		camera.setNearClip(0.01);
		camera.setTranslateX(875);
		camera.setTranslateY(-600);
		camera.setTranslateZ(-5000);
	}
}

public class TerrainMeshNiesenNew {
	
	BorderPane pane;
	int nColums;
	int nRows;
	int [][] heightData;

	private static final double MODEL_SCALE_FACTOR = 2;
    
	private double mouseOldX, mouseOldY = 0;
    private Rotate rotateX = new Rotate(0, Rotate.X_AXIS);
    private Rotate rotateY = new Rotate(0, Rotate.Y_AXIS);
	private Rotate rotateZ = new Rotate(0, Rotate.Z_AXIS);

	final Translate xTranslate = new Translate();
	final Translate yTranslate = new Translate();

	TriangleMesh triangleMesh = new TriangleMesh();
	MeshView meshView = new MeshView(triangleMesh);

	PointLight pointLight = new PointLight();

	Point3D XAxisPoint = new Point3D(1, 0, 0);
	Point3D YAxisPoint = new Point3D(0, 1, 0);
	Point3D ZAxisPoint = new Point3D(0, 0, 1);
	Point3D XZAxisPoint = new Point3D(1, 0, 1);
	
	Cylinder AxisX = new Cylinder();		//X-Achse
	Cylinder AxisY = new Cylinder();		//Y-Achse
	Cylinder AxisZ = new Cylinder();		//Z-Achse
	
	int top_left = 0; int top_right = 2;	
	int bottom_left = 1; int bottom_right = 3; 
	//int top_left = 0; int top_right = 3;	
	//int bottom_left = 1; int bottom_right = 2; 
	
	String absPath = System.getProperty("user.dir");
	String relPath = absPath.replace("\\target", "");

	public TerrainMeshNiesenNew (int cols, int rows, int[][] heights) {
		nColums = cols;
		nRows = rows;
		heightData = heights;
		this.pane = new BorderPane();
	}	
	
	public TerrainMeshNiesenNew(){}
	
	public void initCoordinateSystem() throws Exception {		
		//X-Achse
		AxisX.setRadius(2);
		AxisX.setHeight(5000);
		AxisX.setMaterial(new PhongMaterial(Color.BLUE));
		AxisX.setRotationAxis(ZAxisPoint);
		AxisX.setRotate(90);

		//Y-Achse
		AxisY.setRadius(2);
		AxisY.setHeight(5000);
		AxisY.setMaterial(new PhongMaterial(Color.BLACK));

		//Z-Achse
		AxisZ.setRadius(2);
		AxisZ.setHeight(5000);
		AxisZ.setMaterial(new PhongMaterial(Color.GREEN));
		AxisZ.setRotationAxis(XAxisPoint);
		AxisZ.setRotate(90);

		pointLight.setTranslateX(0);
		pointLight.setTranslateY(-4000);
		pointLight.setTranslateZ(0);
		
		pane.getChildren().add(AxisX);
		pane.getChildren().add(AxisY);
		pane.getChildren().add(AxisZ);
		//pane.getChildren().add(pointLight);
	}	
	
	public void createTerrainMesh(Stage Bildschirm) throws Exception {
		
		//1. Loop für jede Zeile
		for(int z = 0; z < (nRows-1); z++) {
			//2. Loop für jede Spalte (Punkt auf Zeile)
			for(int x = 0; x < (nColums-1); x++) {

				triangleMesh.getTexCoords().addAll(
			0.1f, 0.5f, //Tree Color (0)
					0.25f, 0.5f, 	//Upper Grass (1)
					0.4f, 0.5f, 	//Lower Grass (2)
					0.60f, 0.5f, 	//Dark Grey (3)
					0.7f, 0.5f, 	//Light Grey (4)
					0.95f, 0.5f 	//yellow Grass
				);
				
				//Alle Punkte eines Quadrats
				triangleMesh.getPoints().addAll(
					 x*2-(nColums),  		(-heightData[x][z]/4),	  		z*2-(nRows),       	//Oberer linker Punkt   P1
					(x*2+2)-(nColums),   	(-heightData[x+1][z]/4),   		z*2-(nRows),    		//Oberer rechter Punkt  P2
					 x*2-(nColums),    	(-heightData[x][z+1]/4),   	   (z*2+2)-(nRows), 		//Unterer linker Punkt  P3
					(x*2+2)-(nColums),  	(-heightData[x+1][z+1]/4), 	   (z*2+2)-(nRows)  		//Unterer rechter Punkt P4
				);	
				int steepness;
				steepness = heightData[x][z]-heightData[x+1][z+1];
				
				if((steepness > 9 || steepness < -9) && heightData[x][z] <= 1800) {
					//Trees, Dark Green
					triangleMesh.getFaces().addAll(
						top_left,0, bottom_right,0, top_right,0,
						top_left,0, bottom_left,0, bottom_right,0
					);
				} 
				else if((steepness > 10 || steepness < -10) && heightData[x][z] > 1800) {
					//Steep Rocks, Dark Grey
					triangleMesh.getFaces().addAll(
						top_left,3, bottom_right,3, top_right,3,
						top_left,3, bottom_left,3, bottom_right,3
					);
				} 
				else if(heightData[x][z] > 1800) {
					//Rocky Rerrain, Light Grey
					triangleMesh.getFaces().addAll(
						top_left,4, bottom_right,4, top_right,4,
						top_left,4, bottom_left,4, bottom_right,4
					);
				}
				else if(heightData[x][z] > 1300) {
					//Low Grass, Normal Green
					triangleMesh.getFaces().addAll(
						top_left,2, bottom_right,2, top_right,2,
						top_left,2, bottom_left,2, bottom_right,2
					);
				}
				else {
					//Fläche der beiden Dreiecke in einem Quadrat (Grün)
					triangleMesh.getFaces().addAll(
						top_left,1, bottom_right,1, top_right,1,
						top_left,1, bottom_left,1, bottom_right,1
					);					
				}
				
				top_left += 4;  top_right += 4;
				bottom_left += 4; bottom_right += 4;
			}
		}
		PhongMaterial mat = new PhongMaterial();
		mat.setDiffuseMap(new Image(relPath + "\\res\\texturePic.png"));
		meshView.setDrawMode(DrawMode.FILL);
		meshView.setMaterial(mat);
		meshView.setScaleX(MODEL_SCALE_FACTOR);
		meshView.setScaleY(MODEL_SCALE_FACTOR);
		meshView.setScaleZ(MODEL_SCALE_FACTOR);
		pane.getChildren().add(meshView);
	}
	
	public void show(Stage stage) throws Exception {
		
		RotateCamera g = new RotateCamera();
		Scene scene = new Scene(pane, 1200, 800, true);
		scene.setFill(Color.LIGHTBLUE);

		pane.getTransforms().addAll(rotateY, rotateX);
		pane.setTranslateX(600);
		pane.setTranslateY(0);
		pane.setTranslateZ(0);
		rotateX.setAngle(0);
		rotateY.setAngle(45);
		scene.setCamera(g.camera);

		stage.setTitle("Bildschirm Niesen");
		stage.setScene(scene);
		stage.show();

		scene.setOnMousePressed(event -> {
            mouseOldX = event.getSceneX();
            mouseOldY = event.getSceneY();
        });

		scene.setOnMouseDragged(event -> {
            rotateX.setAngle(rotateX.getAngle() - (event.getSceneY() - mouseOldY)/10);
            rotateY.setAngle(rotateY.getAngle() + (event.getSceneX() - mouseOldX)/10);
			rotateZ.setAngle(rotateZ.getAngle() - (event.getSceneY() - mouseOldY)/10);
            mouseOldX = event.getSceneX();
            mouseOldY = event.getSceneY();
        });

		stage.addEventHandler(ScrollEvent.SCROLL, event -> {
			double delta = event.getDeltaY();
			pane.translateZProperty().set(pane.getTranslateZ() + delta*4);
		});

		scene.setOnKeyPressed((KeyEvent e) -> {
            KeyCode code = e.getCode();
            switch (code) {
                case LEFT:
                    break;
                case RIGHT:
                    break;
                case UP:
                    pane.setTranslateY(pane.getTranslateY() + 20);
                    break;
                case DOWN:
                    pane.setTranslateY(pane.getTranslateY() - 20);
                    break; 
				case D:
					pane.setTranslateX(0);
					pane.setTranslateY(0);
					pane.setTranslateZ(0);
					rotateX.setAngle(0);
            		rotateY.setAngle(45);
					break;
                default:
                    break;
            }
        });		
	}
}