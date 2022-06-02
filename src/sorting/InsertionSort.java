package sorting;

import java.util.List;

public class InsertionSort {

	/**
	 * The algorithm that people often use to sort bridge hands is to consider 
	 * the cards one at a time, inserting each into its proper place among those 
	 * already considered (keeping them sorted). In a computer implementation, we 
	 * need to make space for the current item by moving larger items one position 
	 * to the right, before inserting the current item into the vacated position
	 * @param arr
	 */
	public static void Sort(List<Integer> arr) {
		
		for(int i=1; i<arr.size(); i++) {
			for(int j=i; j>0 && arr.get(j)<arr.get(j-1); j--) {
				Integer swap = arr.get(j);
				arr.set(j, arr.get(j-1));
				arr.set(j-1, swap);
			}
		}

	}
}
