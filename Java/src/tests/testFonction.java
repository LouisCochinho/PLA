package tests;

import pla.Decor;
import pla.Map;
import pla.Case;

public class testFonction {
	private Map g = new Map();
	private static final int WIDTH = 32;
	private static final int HEIGHT = 24;
	
	public int nbDecor(Decor d){
		int res =0;
		Case[][] cs = g.getCases();
		Case ctmp;

		for(int i=0; i< WIDTH; i++){
			for(int j=0; j< HEIGHT; j++){
				ctmp = cs[i][j];
				if(ctmp.getDecor() == d){
					res++;
				}
			}
		}
		
		return res;
	}
}
