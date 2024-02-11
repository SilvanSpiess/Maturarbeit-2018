package sil.caster;

import java.io.File;

public class MyMap {
	int x;
	int y;	
	int a;
	int b;
	
	public int nColums;
	public int nRows;
	int mapHeights[][];
		
	int arrHeights1[][];
	int arrHeights2[][];
	int arrHeights3[][];
	int arrHeights4[][];
	
	derLeserCaster dLC1;
	derLeserCaster dLC2;
	derLeserCaster dLC3;
	derLeserCaster dLC4;

	public int[][] MapHeights() { return mapHeights; } 
	
	public void Init() {

		String absPath = System.getProperty("user.dir");
		String relPath = absPath.replace("\\target", "");
		
		/*
		//Gantrisch Files
		File f1 = new File(relPath + "\\res\\DOM_1206_41.asc");
		File f2 = new File(relPath + "\\res\\DOM_1206_42.asc");
		File f3 = new File(relPath + "\\res\\DOM_1206_43.asc");
		File f4 = new File(relPath + "\\res\\DOM_1206_44.asc");
		*/
		
		//Niesen Files
		File f1 = new File(relPath + "\\res\\DOM_1227_21.asc");
		File f2 = new File(relPath + "\\res\\DOM_1227_22.asc");
		File f3 = new File(relPath + "\\res\\DOM_1227_23.asc");
		File f4 = new File(relPath + "\\res\\DOM_1227_24.asc");
		
		dLC1 = new derLeserCaster(f1,0);
		dLC1.DateiLesenCaster(0);
		
		dLC2 = new derLeserCaster(f2,1);
		dLC2.DateiLesenCaster(1);
		
		dLC3 = new derLeserCaster(f3,2);
		dLC3.DateiLesenCaster(2);
		
		dLC4 = new derLeserCaster(f4,3);
		dLC4.DateiLesenCaster(3);
		
		nColums = dLC1.Colums();
		nRows = dLC1.Rows();
		
		mapHeights = new int[nColums*2+1][nRows*2+1];
				
		for(x = 0; x < nColums*2+1; x++) {
			for (y = 0; y < nRows*2+1; y++)	{
				mapHeights [x][y] = 0;
			}
		}
		
		arrHeights1 = dLC1.scanHeights;
		arrHeights2 = dLC2.scanHeights;
		arrHeights3 = dLC3.scanHeights;
		arrHeights4 = dLC4.scanHeights;
	}	
	
	public void Heights() throws Exception {

		for(a = 0; a < nColums*2+1; a++) {
			for(b = 0; b < nRows*2+1; b++) {
				if ((a >= 0 && a < nColums) && (b >= 0 && b < nRows)) {
					mapHeights[a][b] = arrHeights1[a][b];
				}					
				if ((a >= nColums && a < nColums*2) && (b >= 0 && b < nRows)) {
					mapHeights[a][b] = arrHeights2[a-nColums][b];
				}				
				if ((a >= 0 && a < nColums) && (b >= nRows && b < nRows*2)) {
					mapHeights[a][b] = arrHeights3[a][b-nRows];
				}				
				if ((a >= nColums && a < nColums*2) && ( b >= nRows+1 && b < nRows*2)) {
					mapHeights[a][b] = arrHeights4[a-nColums][b-nRows];
				}
			}
		}
	}
}