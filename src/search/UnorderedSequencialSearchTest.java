package search;

import static org.junit.Assert.*;

import java.time.LocalTime;

import org.junit.Before;
import org.junit.Test;

import entity.MyLocalTime;

public class UnorderedSequencialSearchTest {
	
	private UnorderedSequencialSearch<MyLocalTime, String> localTimes;
	
	@Before
    public void init() {
		localTimes = new UnorderedSequencialSearch<>();
		localTimes.put(new MyLocalTime(LocalTime.parse("09:00:00")), "Chicago");
		localTimes.put(new MyLocalTime(LocalTime.parse("09:00:03")), "Phoenix");
		localTimes.put(new MyLocalTime(LocalTime.parse("09:00:13")), "Houston");
		localTimes.put(new MyLocalTime(LocalTime.parse("09:00:59")), "Chicago");
		localTimes.put(new MyLocalTime(LocalTime.parse("09:01:10")), "Houston");
		localTimes.put(new MyLocalTime(LocalTime.parse("09:03:13")), "Chicago");
		localTimes.put(new MyLocalTime(LocalTime.parse("09:10:11")), "Seattle");
		localTimes.put(new MyLocalTime(LocalTime.parse("09:10:25")), "Seattle");
		localTimes.put(new MyLocalTime(LocalTime.parse("09:14:25")), "Phoenix");
		localTimes.put(new MyLocalTime(LocalTime.parse("09:19:32")), "Chicago");
		localTimes.put(new MyLocalTime(LocalTime.parse("09:19:46")), "Chicago");
		localTimes.put(new MyLocalTime(LocalTime.parse("09:21:05")), "Chicago");
		localTimes.put(new MyLocalTime(LocalTime.parse("09:22:43")), "Seattle");
		localTimes.put(new MyLocalTime(LocalTime.parse("09:22:54")), "Seattle");
		localTimes.put(new MyLocalTime(LocalTime.parse("09:25:52")), "Chicago");
		localTimes.put(new MyLocalTime(LocalTime.parse("09:35:21")), "Chicago");
		localTimes.put(new MyLocalTime(LocalTime.parse("09:36:14")), "Seattle");
		localTimes.put(new MyLocalTime(LocalTime.parse("09:37:44")), "Phoenix");
    }

	@Test
	public void testPut() {
		UnorderedSequencialSearch<Character, Integer> list = new UnorderedSequencialSearch<>();
		list.put('S', 0);
		list.put('E', 1);
		list.put('A', 2);
		list.put('R', 3);
		list.put('C', 4);
		list.put('H', 5);
		list.put('E', 6);
		list.put('X', 7);
		list.put('A', 8);
		list.put('M', 9);
		list.put('P', 10);
		list.put('L', 11);
		list.put('E', 12);
		
		UnorderedSequencialSearch<Character, Integer> answer = new UnorderedSequencialSearch<>();
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
		int result = answer.compareTo(list);
		assertTrue("asdf", 0 == result);
	}

	@Test
	public void testMin() {
		System.out.println(localTimes.min());
		assertTrue("asdf", "09:00:00 / Chicago".equals(localTimes.min().toString()));
	}
	
	@Test
	public void testMax() {
		System.out.println(localTimes.max());
		assertTrue("asdf", "09:37:44 / Phoenix".equals(localTimes.max().toString()));
	}

}
