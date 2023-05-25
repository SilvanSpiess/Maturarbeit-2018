package sil.caster;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.lang.Integer;

public class derLeserCaster {
	Scanner[] scanner = new Scanner[4];
	
	int 		nColums = 0;
	int 		nRows = 0;
	int [][] scanHeights;

	public int Colums() { return nColums;}
	public int Rows() { return nRows;}
	public int[][] Heights() { return scanHeights;}

	public derLeserCaster(File f, int index) {
		try{
			scanner[index] = new Scanner(f);
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void DateiLesenCaster(int index) {
		String sInfoLines [] = new String [6];
		for ( int i = 0; i<6; i++ )
			sInfoLines[i] = scanner[index].nextLine();
		
		// Zeilen 1 und 2 ausgeben
		String sColums = sInfoLines[0].substring(6);
		String sRows = sInfoLines[1].substring(6);
		nColums = Integer.parseInt(sColums);
		nRows = Integer.parseInt(sRows);
		
		// Daten Array
		scanHeights = new int[nColums][nRows];
		
		String sInfo1 = "cols=" + sColums + ", rows=" + sRows;
		System.out.println(sInfo1);
		
		// Zeilen 3-6
		String xllcorner = sInfoLines[2].substring(9);
		String yllcorner = sInfoLines[3].substring(9);
		String cellsize = sInfoLines[4].substring(8);
		String nodata_value = sInfoLines[5].substring(13);
		String sInfo2 = "xllcorner=" + xllcorner + ", yllcorner=" + yllcorner + ", cellsize=" + cellsize + ", nodata_value=" + nodata_value;
		System.out.println(sInfo2);
		
		// Daten abfüllen in scanHeights
		try {
			for (int y = 0; y<nRows; y++) {
				for (int x = 0; x<nColums; x++) {
					if (scanner[index].hasNextFloat())
						scanHeights[x][y] = (int)scanner[index].nextFloat();
				}
				if(y % 100 == 0){
					System.out.println("processing " + y);
				}
				
			}
			System.out.println("Fertig");
		}
		catch (java.util.NoSuchElementException e) {
			e.printStackTrace();
		}
		scanner[index].close();
		return;
	}	
}

	
