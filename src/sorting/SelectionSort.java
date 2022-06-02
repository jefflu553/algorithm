package sorting;

import java.util.List;

/**
 * @author jeffl
 *
 */
public class SelectionSort {

	
	/**
	 * One of the simplest sorting algorithms works as follows: First, find the smallest 
	 * item in the array, and exchange it with the first entry. Then, find the next smallest 
	 * item and exchange it with the second entry. Continue in this way until the entire array 
	 * is sorted. This method is called selection sort because it works by repeatedly selecting 
	 * the smallest remaining item. 
	 * @param arr
	 */
	public static void Sort(List<Integer> arr) {
		Integer swap = null;
		for(int i=0; i<arr.size(); i++) {
			for(int j=arr.size()-1; j>i; j--) {
				if(arr.get(j) < arr.get(i)) {
					swap = arr.get(j);
					arr.set(j, arr.get(i));
					arr.set(i, swap);
				}
			}
		}
	}
}
