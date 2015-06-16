package com.blisek.dnd3manager.dnd3;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class CreatureControllerTest {
	private CreatureModel model;
	private CreatureController controller;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		model = new CreatureModel();
		controller = new CreatureController(model);
	}

	@Test
	public void testGetStrengthMod() {
		model.put(StringConstants.STRENGTH, 16);
		assertEquals(3, controller.getStrengthMod(false));
		
		model.put(StringConstants.STRENGTH + "_bonus", 4);
		assertEquals(5, controller.getStrengthMod(false));
	}

	@Test
	public void testGetDexterityMod() {
		model.put(StringConstants.DEXTERITY, 16);
		assertEquals(3, controller.getDexterityMod(false));
		
		model.put(StringConstants.DEXTERITY + "_bonus", 4);
		assertEquals(5, controller.getDexterityMod(false));
	}

	@Test
	public void testGetConstitutionMod() {
		model.put(StringConstants.CONSTITUTION, 16);
		assertEquals(3, controller.getConstitutionMod(false));
		
		model.put(StringConstants.CONSTITUTION + "_bonus", 4);
		assertEquals(5, controller.getConstitutionMod(false));
	}

	@Test
	public void testGetIntelligenceMod() {
		model.put(StringConstants.INTELLIGENCE, 16);
		assertEquals(3, controller.getIntelligenceMod(false));
		
		model.put(StringConstants.INTELLIGENCE + "_bonus", 4);
		assertEquals(5, controller.getIntelligenceMod(false));
	}

	@Test
	public void testGetWisdomMod() {
		model.put(StringConstants.WISDOM, 1);
		assertEquals(-5, controller.getWisdomMod(false));
		
		model.put(StringConstants.WISDOM + "_bonus", 3);
		assertEquals(-3, controller.getWisdomMod(false));
	}

	@Test
	public void testGetCharismaMod() {
		model.put(StringConstants.CHARISMA, 16);
		assertEquals(3, controller.getCharismaMod(false));
		
		model.put(StringConstants.CHARISMA + "_bonus", 4);
		assertEquals(5, controller.getCharismaMod(false));
	}

}
