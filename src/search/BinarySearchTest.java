package search;

import static org.junit.Assert.assertTrue;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class BinarySearchTest {
	
	private BinarySearch<Character, Integer> p;
	private BinarySearch<MyLocalTime, String> localTimes;
	
	@Before
    public void init() {
		p = new BinarySearch<>(10);
		p.put('S', 0);
		p.put('E', 1);
		p.put('A', 2);
		p.put('R', 3);
		p.put('C', 4);
		p.put('H', 5);
		p.put('E', 6);
		p.put('X', 7);
		p.put('A', 8);
		p.put('M', 9);
		p.put('P', 10);
		p.put('L', 11);
		p.put('E', 12);
		System.out.println(p);
		
		localTimes = new BinarySearch<>(20);
		
		localTimes.put(new MyLocalTime(LocalTime.parse("09:00:13")), "Houston");
		localTimes.put(new MyLocalTime(LocalTime.parse("09:10:25")), "Seattle");
		localTimes.put(new MyLocalTime(LocalTime.parse("09:35:21")), "Chicago");
		localTimes.put(new MyLocalTime(LocalTime.parse("09:03:13")), "Chicago");
		localTimes.put(new MyLocalTime(LocalTime.parse("09:10:11")), "Seattle");
		localTimes.put(new MyLocalTime(LocalTime.parse("09:00:00")), "Chicago");
		localTimes.put(new MyLocalTime(LocalTime.parse("09:00:03")), "Phoenix");
		localTimes.put(new MyLocalTime(LocalTime.parse("09:19:32")), "Chicago");
		localTimes.put(new MyLocalTime(LocalTime.parse("09:36:14")), "Seattle");
		localTimes.put(new MyLocalTime(LocalTime.parse("09:14:25")), "Phoenix");
		localTimes.put(new MyLocalTime(LocalTime.parse("09:00:59")), "Chicago");
		localTimes.put(new MyLocalTime(LocalTime.parse("09:01:10")), "Houston");
		localTimes.put(new MyLocalTime(LocalTime.parse("09:22:43")), "Seattle");
		localTimes.put(new MyLocalTime(LocalTime.parse("09:22:54")), "Seattle");
		localTimes.put(new MyLocalTime(LocalTime.parse("09:25:52")), "Chicago");
		localTimes.put(new MyLocalTime(LocalTime.parse("09:19:46")), "Chicago");
		localTimes.put(new MyLocalTime(LocalTime.parse("09:21:05")), "Chicago");
		localTimes.put(new MyLocalTime(LocalTime.parse("09:37:44")), "Phoenix");
    }

	@Test
	public void testPut() {
		BinarySearch<Character, Integer> answer = new BinarySearch<>(10);
		answer.put('S', 0);
		answer.put('E', 12);
		answer.put('A', 8);
		answer.put('R', 3);
		answer.put('C', 4);
		answer.put('H', 5);
		answer.put('X', 7);
		answer.put('M', 9);
		answer.put('P', 10);
		answer.put('L', 11);
		assertTrue("asdf", answer.equals(p));
	}
	
	@Test
	public void testGet() {
		assertTrue("asdf", p.get('M') == 9);
		assertTrue("asdf", p.get('A') == 8);
		assertTrue("asdf", p.get('X') == 7);
	}
	
	@Test
	public void testContain() {
		assertTrue("asdf", p.contain('M') == true);
		assertTrue("asdf", p.contain('Z') == false);
	}
	
	@Test
	public void testDelete() {
		assertTrue("asdf", p.contain('A') == true);
		assertTrue("asdf", p.size() == 10);
		p.delete('A');
		assertTrue("asdf", p.contain('A') == false);
		assertTrue("asdf", p.size() == 9);
		
		assertTrue("asdf", p.contain('X') == true);
		p.delete('X');
		assertTrue("asdf", p.contain('X') == false);
		assertTrue("asdf", p.size() == 8);
		
		assertTrue("asdf", p.contain('P') == true);
		p.delete('P');
		assertTrue("asdf", p.contain('P') == false);
		assertTrue("asdf", p.size() == 7);
	}
	
	@Test
	public void testMin() {
		MyLocalTime min = localTimes.min();
		assertTrue("asdf", "09:00:00".equals(min.toString()));
	}
	
	@Test
	public void testMax() {
		MyLocalTime min = localTimes.max();
		assertTrue("asdf", "09:37:44".equals(min.toString()));
	}
	
	@Test
	public void testFloor() {
		MyLocalTime floor = localTimes.floor(new MyLocalTime(LocalTime.parse("09:05:00")));
		assertTrue("asdf", "09:03:13".equals(floor.toString()));
		floor = localTimes.floor(new MyLocalTime(LocalTime.parse("09:00:00")));
		assertTrue("asdf", "09:00:00".equals(floor.toString()));
		floor = localTimes.floor(new MyLocalTime(LocalTime.parse("08:00:00")));
		assertTrue("asdf", floor == null);
		floor = localTimes.floor(new MyLocalTime(LocalTime.parse("09:37:45")));
		assertTrue("asdf", "09:37:44".equals(floor.toString()));
	}
	
	@Test
	public void testCeiling() {
		MyLocalTime ceiling = localTimes.ceiling(new MyLocalTime(LocalTime.parse("09:30:00")));
		assertTrue("asdf", "09:35:21".equals(ceiling.toString()));
		ceiling = localTimes.ceiling(new MyLocalTime(LocalTime.parse("08:00:00")));
		assertTrue("asdf", "09:00:00".equals(ceiling.toString()));
		ceiling = localTimes.ceiling(new MyLocalTime(LocalTime.parse("09:00:00")));
		assertTrue("asdf", "09:00:00".equals(ceiling.toString()));
		ceiling = localTimes.ceiling(new MyLocalTime(LocalTime.parse("09:37:45")));
		assertTrue("asdf", ceiling == null);
	}

	@Test
	public void testRank() {
		int num = localTimes.rank(new MyLocalTime(LocalTime.parse("09:10:25")));
		assertTrue("asdf", num == 7);
		num = localTimes.rank(new MyLocalTime(LocalTime.parse("09:10:24")));
		assertTrue("asdf", num == 7);
		num = localTimes.rank(new MyLocalTime(LocalTime.parse("09:10:26")));
		assertTrue("asdf", num == 8);
		num = localTimes.rank(new MyLocalTime(LocalTime.parse("09:00:00")));
		assertTrue("asdf", num == 0);
	}
	
	@Test
	public void testSelect() {
		MyLocalTime key = localTimes.select(7);
		assertTrue("asdf", "09:10:25".equals(key.toString()));
		key = localTimes.select(0);
		assertTrue("asdf", "09:00:00".equals(key.toString()));
		key = localTimes.select(localTimes.size());
		assertTrue("asdf", key == null);
		key = localTimes.select(localTimes.size()+1);
		assertTrue("asdf", key == null);
		BinarySearch<MyLocalTime, String> tmp = new BinarySearch<>(10);
		key = tmp.select(7);
		assertTrue("asdf", key == null);
	}
	
	@Test
	public void testRangeSize() {
		int num = localTimes.size(new MyLocalTime(LocalTime.parse("09:15:00")), new MyLocalTime(LocalTime.parse("09:25:00")));
		assertTrue("asdf", num == 5);
		num = localTimes.size(new MyLocalTime(LocalTime.parse("08:15:00")), new MyLocalTime(LocalTime.parse("08:25:00")));
		assertTrue("asdf", num == 0);
		num = localTimes.size(new MyLocalTime(LocalTime.parse("11:15:00")), new MyLocalTime(LocalTime.parse("11:25:00")));
		assertTrue("asdf", num == 0);
		num = localTimes.size(new MyLocalTime(LocalTime.parse("09:10:26")), new MyLocalTime(LocalTime.parse("09:10:27")));
		assertTrue("asdf", num == 0);
		num = localTimes.size(new MyLocalTime(LocalTime.parse("08:15:00")), new MyLocalTime(LocalTime.parse("09:00:59")));
		assertTrue("asdf", num == 4);
		num = localTimes.size(new MyLocalTime(LocalTime.parse("09:00:00")), new MyLocalTime(LocalTime.parse("09:00:59")));
		assertTrue("asdf", num == 4);
		num = localTimes.size(new MyLocalTime(LocalTime.parse("09:00:00")), new MyLocalTime(LocalTime.parse("09:00:57")));
		assertTrue("asdf", num == 3);
		num = localTimes.size(new MyLocalTime(LocalTime.parse("09:00:00")), new MyLocalTime(LocalTime.parse("09:00:00")));
		assertTrue("asdf", num == 1);
	}
	
	@Test
	public void testKeys() {
		List<MyLocalTime> answers5 = Arrays.asList(new MyLocalTime(LocalTime.parse("09:19:32")),
													new MyLocalTime(LocalTime.parse("09:19:46")),
													new MyLocalTime(LocalTime.parse("09:21:05")),
													new MyLocalTime(LocalTime.parse("09:22:43")),
													new MyLocalTime(LocalTime.parse("09:22:54"))
												);
		List<MyLocalTime> answers4 = Arrays.asList(new MyLocalTime(LocalTime.parse("09:00:00")),
													new MyLocalTime(LocalTime.parse("09:00:03")),
													new MyLocalTime(LocalTime.parse("09:00:13")),
													new MyLocalTime(LocalTime.parse("09:00:59"))
												);
		List<MyLocalTime> answers3 = Arrays.asList(new MyLocalTime(LocalTime.parse("09:00:00")),
													new MyLocalTime(LocalTime.parse("09:00:03")),
													new MyLocalTime(LocalTime.parse("09:00:13"))
												);
		List<MyLocalTime> answers1 = Arrays.asList(new MyLocalTime(LocalTime.parse("09:00:00")));
		
		
		List<MyLocalTime> ranges = (List<MyLocalTime>)localTimes.keys(new MyLocalTime(LocalTime.parse("09:15:00")), new MyLocalTime(LocalTime.parse("09:25:00")));
		assertTrue("asdf", ranges.equals(answers5));
		ranges = (List<MyLocalTime>)localTimes.keys(new MyLocalTime(LocalTime.parse("08:15:00")), new MyLocalTime(LocalTime.parse("08:25:00")));
		assertTrue("asdf", ranges.size() == 0);
		ranges = (List<MyLocalTime>)localTimes.keys(new MyLocalTime(LocalTime.parse("11:15:00")), new MyLocalTime(LocalTime.parse("11:25:00")));
		assertTrue("asdf", ranges.size() == 0);
		ranges = (List<MyLocalTime>)localTimes.keys(new MyLocalTime(LocalTime.parse("09:10:26")), new MyLocalTime(LocalTime.parse("09:10:27")));
		assertTrue("asdf", ranges.size() == 0);
		ranges = (List<MyLocalTime>)localTimes.keys(new MyLocalTime(LocalTime.parse("08:15:00")), new MyLocalTime(LocalTime.parse("09:00:59")));
		assertTrue("asdf", ranges.equals(answers4));
		ranges = (List<MyLocalTime>)localTimes.keys(new MyLocalTime(LocalTime.parse("09:00:00")), new MyLocalTime(LocalTime.parse("09:00:59")));
		assertTrue("asdf", ranges.equals(answers4));
		ranges = (List<MyLocalTime>)localTimes.keys(new MyLocalTime(LocalTime.parse("09:00:00")), new MyLocalTime(LocalTime.parse("09:00:57")));
		assertTrue("asdf", ranges.equals(answers3));
		ranges = (List<MyLocalTime>)localTimes.keys(new MyLocalTime(LocalTime.parse("09:00:00")), new MyLocalTime(LocalTime.parse("09:00:00")));
		assertTrue("asdf", ranges.equals(answers1));
	}
}
