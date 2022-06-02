package sorting;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;

public class MergeSortTest {

	@Test
	public void testSort() {
		int [] arr = {2, 3, 4, 1};
		int [] auxiliary = new int[4];
		MergeSort.sort(arr, auxiliary, 0, 3);
		List<Integer> arrList = Arrays.stream(arr).boxed().collect(Collectors.toList());
		arrList.stream().forEach(System.out::println);
	}

}
