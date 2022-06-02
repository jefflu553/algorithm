package sorting;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class InsertionSortTest {

	@Test
	public void testSort() {
		List<Integer> arr = Arrays.asList(10, 210, 30, 3, 3);
		InsertionSort.Sort(arr);
		arr.stream().forEach(System.out::println);
	}

}
