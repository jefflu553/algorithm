package sorting;

public class QuickSort {
	
	private static int partition(int[] arr, int low, int high) {
		
		int partition = arr[low];
		int i = low;
		int j = high + 1;
		int swap = 0;
		
		while(true) {
			while(arr[++i] <= partition) {
				if(i >= high) break;
			}
			while(arr[--j] >= partition) {
				if(j <= low) break;
			}
			if(i >= j) break;
			swap = arr[i];
			arr[i] = arr[j];
			arr[j] = swap;
		}
		swap = arr[low];
		arr[low] = arr[j];
		arr[j] = swap;
		
		return j;
	}
	
	public static void sort(int[] arr) {
		int length = arr.length;
		int partition = partition(arr, 0, length-1);
		sort(arr, 0, partition-1);
		sort(arr, partition+1, length-1);
	}
	
	private static void sort(int[] arr, int low, int high) {
		if (low >= high) return;
		int partition = partition(arr, low, high);
		sort(arr, low, partition-1);
		sort(arr, partition+1, high);
	}
	

}
