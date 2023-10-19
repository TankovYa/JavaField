package Main;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Integer[] i = {1,1,129,129};
		System.out.print("\nArray i:");
		for (int n=0;n<i.length;n++) {
			System.out.print("\n\ti"+(n+1)+" = "+i[n]);
		}
		System.out.print("\n i1==i2:\n\t"+(i[0]==i[1]));
		System.out.print("\n i3==i4:\n\t"+(i[2]==i[3]));
	}

}
