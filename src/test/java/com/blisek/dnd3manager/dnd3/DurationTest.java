package com.blisek.dnd3manager.dnd3;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class DurationTest {
	private static Duration d1, d2;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		d1 = new Duration(3, TimeUnit.ROUND);
		d2 = new Duration(2, TimeUnit.TOUR);
	}

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testSum() {
		Duration tmp = Duration.sum(d1, d2);
		assertEquals(23, tmp.duration);
		assertEquals(TimeUnit.ROUND, tmp.timeUnit);
	}
	
	@Test
	public void testCompareTo()	 {
		Duration[] durations = { 
				new Duration(1, TimeUnit.DAY), new Duration(1, TimeUnit.YEAR),
				new Duration(1, TimeUnit.WEEK), new Duration(1, TimeUnit.TOUR),
				new Duration(1, TimeUnit.ROUND)
		};
		Duration[] expected = {
				new Duration(1, TimeUnit.ROUND), new Duration(1, TimeUnit.TOUR),
				new Duration(1, TimeUnit.DAY), new Duration(1, TimeUnit.WEEK),
				new Duration(1, TimeUnit.YEAR)
		};
		Arrays.sort(durations);
		assertArrayEquals(expected, durations);
	}

}
