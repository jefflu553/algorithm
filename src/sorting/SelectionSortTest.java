package sorting;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class SelectionSortTest {

	@Test
	public void testSort() {
		List<Integer> arr = Arrays.asList(10, 210, 30, 3);
		SelectionSort.Sort(arr);
		arr.stream().forEach(System.out::println);
	}
	


}
