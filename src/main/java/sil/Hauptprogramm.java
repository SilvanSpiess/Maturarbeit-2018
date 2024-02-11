package sil;

import sil.GlowUp.RayCasterGlowUp;
import sil.GlowUp.TerrainMeshNiesenNew;
import sil.GlowUp.derLeserGlowUp;
import sil.NiesenFX.TerrainMeshNiesen;
import sil.caster.MyMap;
import sil.caster.RayCaster;
import sil.javaFX.BoxZeichner;
import sil.javaFX.PyramidMesh;

import java.util.Scanner;

import java.io.File;
import java.awt.Color;
import javax.swing.JFrame;

import javafx.application.Application;
import javafx.stage.Stage;

//Box
/* public class Hauptprogramm extends Application {
	public static void main (String [] args) {
		launch(args);
	}

	public void start (Stage Bildschirm) throws Exception {
		BoxZeichner bz = new BoxZeichner();
		bz.init();
		bz.BoxBewegen(Bildschirm);	
	}
} */
//PyramidMesh
/*
public class Hauptprogramm extends Application {
	public static void main(String [] args) {
		launch(args);
	}
	
	public void start(Stage Bildschirm) throws Exception {
		PyramidMesh PM = new PyramidMesh();
		
		try{
			PM.init();
			PM.pyramid(Bildschirm);
		}
		catch (Exception e){
			System.out.print(e.toString());
		}
	}
}*/
//TerrainMesh
/* public class Hauptprogramm extends Application {
	public static void main(String[] args) {
		launch(args);
	}
	
	public void start (Stage Bildschirm) throws Exception {
		MyMap MMp = new MyMap();
		MMp.Init();
		MMp.Heights();
		
		TerrainMeshNiesen TMN = new TerrainMeshNiesen(MMp.nColums, MMp.nRows, MMp.MapHeights());
		try{
			TMN.init();
		}
		catch (Exception e){
			System.out.print(e.toString());
		}
		
		TMN.MyMesh1(Bildschirm);
		TMN.show(Bildschirm);
	}
}
*/	
//RayCaster

public class Hauptprogramm  extends Application {	

	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";   

	private static int Modus;

	static File dataFile;

	private static void executePyramidMesh(Stage Bildschirm) {
		PyramidMesh PM = new PyramidMesh();
		try {
			PM.init();
			PM.pyramid(Bildschirm);
		}
		catch (Exception e) {
			System.out.print(e.toString());
		}
	}

	private static void executeBox(Stage Bildschirm) {
		BoxZeichner bz = new BoxZeichner();
		try {
			bz.init();
			bz.BoxBewegen(Bildschirm);
		} catch (Exception e) {
			System.out.print(e.toString());
		}		
	}

	private static void executeTerrainMesh(Stage Bildschirm) {
		MyMap MMp = new MyMap();
		
		try {
			MMp.Init();
			MMp.Heights();
		} catch (Exception e) {
			System.out.print(e.toString());
		}
		
		TerrainMeshNiesen TMN = new TerrainMeshNiesen(MMp.nColums, MMp.nRows, MMp.MapHeights());
		try{
			TMN.init();
			TMN.MyMesh1(Bildschirm);
			TMN.show(Bildschirm);
		}
		catch (Exception e){
			System.out.print(e.toString());
		}		
	}

	private static void executeTerrainMeshGlowUp(Stage stage) {

		derLeserGlowUp DLMN = new derLeserGlowUp();
		DLMN.DateiLesen(dataFile);

		TerrainMeshNiesenNew TMNN = new TerrainMeshNiesenNew(DLMN.nColums, DLMN.nRows, DLMN.scanHeights);
		try {
			TMNN.initCoordinateSystem();
			TMNN.createTerrainMesh(stage);
			TMNN.show(stage);
		}
		catch (Exception e){
			System.out.print(e.toString());
		}		
	}

	private static void executeRayCaster() {		
		JFrame fenster = new JFrame ("Bildschirm");
		fenster.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fenster.setSize(800,800);
		fenster.setBackground(Color.LIGHT_GRAY);
		fenster.setVisible(true);
		RayCaster RCGU = new RayCaster();
		try{
			RCGU.Init();
			RCGU.Casten(fenster.getGraphics());
		} 
		catch (Exception e) {
			System.out.print(e.toString());
		}		
		//fenster.add(RCr);
		//fenster.paintAll(fenster.getGraphics());
	}

	private static void executeRayCasterGlowUp(Stage stage) {		
		String absPath = System.getProperty("user.dir");
		String relPath = absPath.replace("\\target", "");
		File NiesenFile = new File(relPath + "\\res\\NiesenHeightsx20.asc");

		derLeserGlowUp DLMN = new derLeserGlowUp();
		DLMN.DateiLesen(NiesenFile);
		RayCasterGlowUp RCGU = new RayCasterGlowUp(DLMN.nColums, DLMN.nRows, DLMN.scanHeights);
		try{
			RCGU.Casten();
			RCGU.show(stage);
		} 
		catch (Exception e) {
			System.out.print(e.toString());
		}		
	}

	public static void main (String[]args) {

		Scanner scanner = new Scanner(System.in);

		System.out.println(ANSI_CYAN + "Choose what you wanna do, by choosing a number:" + ANSI_RESET);
		System.out.println(ANSI_PURPLE + "1 [Box]" + ANSI_RESET);
		System.out.println(ANSI_PURPLE + "2 [Pyramidmesh]" + ANSI_RESET);
		System.out.println(ANSI_PURPLE + "3 [Terrainmesh]" + ANSI_RESET);
		System.out.println(ANSI_PURPLE + "4 [Terrainmesh Glow up]" + ANSI_RESET);
		System.out.println(ANSI_PURPLE + "5 [Raycaster]" + ANSI_RESET);
		System.out.println(ANSI_PURPLE + "6 [Raycaster Glow Up]" + "\033[1;93m");

		String input = scanner.nextLine();
		Modus = Integer.parseInt(input);
		//scanner.close();
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		switch (Modus) {
			case 1:
				System.out.println(ANSI_CYAN + "Executing the" + ANSI_GREEN + " Box " + ANSI_CYAN + "code!" + ANSI_RESET);
				executeBox(stage);
				break;
			case 2:
				System.out.println(ANSI_CYAN + "Executing the" + ANSI_GREEN + " Pyramidmesh " + ANSI_CYAN + "code!" + ANSI_RESET);
				executePyramidMesh(stage);
				break;
			case 3:
				System.out.println(ANSI_CYAN + "Executing the" + ANSI_GREEN + " Terrainmesh " + ANSI_CYAN + "code!" + ANSI_RESET);
				executeTerrainMesh(stage);
				break;
			case 4:
				Scanner scanner = new Scanner(System.in);
				String absPath = System.getProperty("user.dir");
				String relPath = absPath.replace("\\target", "");
				System.out.println(ANSI_CYAN + "Choose Niesen "+ANSI_PURPLE+"[n]"+ANSI_CYAN+" or Gantrisch "+ANSI_PURPLE+"[g]\033[1;93m");
				String input = scanner.nextLine();
				scanner.close();
				if(input.contains("n")) {
					dataFile = new File(relPath + "\\res\\NiesenHeightsx20.asc");
					System.out.println(ANSI_CYAN + "You chose the " + ANSI_GREEN + " Niesen " + ANSI_CYAN + "as a mountain!" + ANSI_RESET);
				}
				else if(input.contains("g")) {
					dataFile = new File(relPath + "\\res\\GantrischHeightsx20.asc");
					System.out.println(ANSI_CYAN + "You chose the " + ANSI_GREEN + " Gantrisch " + ANSI_CYAN + "as a mountain!" + ANSI_RESET);
				}
				else {
					dataFile = new File(relPath + "\\res\\NiesenHeightsx20.asc");
					System.out.println(ANSI_CYAN + "You didn't chose any, taking the " + ANSI_GREEN + " Niesen " + ANSI_CYAN + "as a mountain!" + ANSI_RESET);
				}
				executeTerrainMeshGlowUp(stage);
				break;
			case 5:
				System.out.println(ANSI_CYAN + "Executing the" + ANSI_GREEN + " Raycaster " + ANSI_CYAN + "code!" + ANSI_RESET);
				executeRayCaster();
				break;
			case 6:
				System.out.println(ANSI_CYAN + "Executing the" + ANSI_GREEN + " Raycaster Glow Up " + ANSI_CYAN + "code!" + ANSI_RESET);
				executeRayCasterGlowUp(stage);
				break;			
			default:
				System.out.println(ANSI_RED + "Invalid input, cya o/" + ANSI_RESET);
				break;				
		}
	}
}