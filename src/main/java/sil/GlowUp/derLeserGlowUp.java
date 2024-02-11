package sil.GlowUp;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Random;

public class derLeserGlowUp {

	public final int nColums = 875;
	public final int nRows = 600;
	public int [][] scanHeights = new int[nColums][nRows];
		
	public void DateiLesen(File f) {
		
		Scanner s;
		try {
			s = new Scanner(f);
			
			// datenabfüllen in scanHeights
			for (int y = 0; y < nRows; y++)	{
				for (int x = 0; x < nColums; x++) {
					scanHeights[x][y] = Math.round(s.nextFloat());
					if(scanHeights[x][y] < 100 && x >=1) {
						Random random = new Random();
						scanHeights[x][y] = scanHeights[x-1][y] + (random.nextInt(7) - 3);
					}					
				}
			}
			s.close();
		} catch (FileNotFoundException e) {
			System.out.println(e.toString());
		}
	}
}

	
