package sil.NiesenFX;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.lang.Integer;

public class derLeser {

	Scanner 	s;
	public int 		nColums = 0;
	int 		nRows = 0;
	int [][] 	scanHeights;

	int Colums() { 
		return nColums;
	}
	int Rows() { 
		return nRows;
	}
	public int[][] Heights() { 
		return scanHeights;
	}

	public derLeser (File f){
		try{
			s = new Scanner (f);
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
		
	public void DateiLesen() {
		
		String sInfoLines [] = new String [6];
		for ( int i = 0; i<6; i++ )
			sInfoLines[i] = s.nextLine();
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
		String sInfo2 = "xllcorner=" + xllcorner + ", yllcorner=" + yllcorner + ", cellsize=" + cellsize +
				", nodata_value=" + nodata_value;
		System.out.println(sInfo2);
		
		// datenabfüllen in scanHeights
		for ( int y = 0; y<nRows; y++ )	{
			for ( int x = 0; x<nColums; x++ ) {
				scanHeights[x][y] = (int)s.nextFloat();					
			}
			//System.out.println(y);
		}
		System.out.print("Fertig");
		
		//daten ausgeben
		/*
		for ( int y = 0; y<nRows; y++ )
		{
			System.out.println(y + ": ");
			for (int x = 0; x<nColums; x++ )
			{
				System.out.print (scanHeights[x][y]+ " ");
				
			}
			System.out.println();
		}
		System.out.print("Fertig");
		*/
		
		/*
		if(s.hasNext()){
			int zahl = s.nextInt ();
			return new Ausgeber();
		}*/
		return;
	}
	public void schliessen(){
		s.close();
	}	
}

	
