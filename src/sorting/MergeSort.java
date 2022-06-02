package sorting;

public class MergeSort {

	public static void sort(int [] original, int [] auxiliary, int low, int high) {
		if(low >= high) return;
		int mid = low + (high - low) / 2;
		sort(original, auxiliary, low, mid);
		sort(original, auxiliary, mid+1, high);
		merge(original, auxiliary, low, mid, high);
	}
	
	private static void merge(int [] original, int [] auxiliary, int low, int mid, int high) {
		for(int k=low; k<=high; k++) {
			auxiliary[k] = original[k];
		}
		int i = low;
		int j = mid + 1;
		for(int k=low; k<=high; k++) {
			if(i > mid) original[k] = auxiliary[j++];
			else if(j > high) original[k] = auxiliary[i++];
			else if(auxiliary[i] > auxiliary[j]) original[k] = auxiliary[j++];
			else original[k] = auxiliary[i++];
		}
	}
}
