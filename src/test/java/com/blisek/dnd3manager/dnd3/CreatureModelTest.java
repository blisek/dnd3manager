package com.blisek.dnd3manager.dnd3;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class CreatureModelTest {
	private static CreatureModel model;
	private static final int FLAG_NUM = 11;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		model = new CreatureModel();
		model.turnFlagOn(FLAG_NUM);
	}

	@Before
	public void setUp() throws Exception {
	}
	
	@Test
	public void testIsFlagOn() {
		assertTrue(model.isFlagOn(FLAG_NUM));
	}
	
	@Test
	public void testTurnFlagOnOff() {
		int randomFlag = new Random().nextInt(80) + 11;
		assertFalse(model.isFlagOn(randomFlag));
		model.turnFlagOn(randomFlag);
		assertTrue(model.isFlagOn(randomFlag));
		model.turnFlagOff(randomFlag);
		assertFalse(model.isFlagOn(randomFlag));
	}
	
	@Test
	public void testAddFlagObserver() {
		final boolean[] callback_invoke = { false };
		FlagObserver observer = new FlagObserver() {
			@Override
			public void flagChanged(Object sender, int bitNum, boolean oldValue,
					boolean newValue) {
				callback_invoke[0] = newValue;
			}
		};
		int randomFlag = new Random().nextInt(50) + 12;
		model.addFlagObserver(observer);
		model.turnFlagOn(randomFlag);
		assertTrue(callback_invoke[0]);
		model.turnFlagOff(randomFlag);
		assertFalse(callback_invoke[0]);
		model.removeFlagObserver(observer);
		model.turnFlagOn(randomFlag);
		assertFalse(callback_invoke[0]);
	}

}
