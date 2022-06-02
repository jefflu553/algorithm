package sorting;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;

public class QuickSortTest {

	@Test
	public void test() {
		int [] arr = {2, 3, 4, 1};
		QuickSort.sort(arr);
		List<Integer> arrList = Arrays.stream(arr).boxed().collect(Collectors.toList());
		arrList.stream().forEach(System.out::println);
	}

}
