package com.blisek.dnd3manager.dnd3.basic.feats;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.blisek.dnd3manager.dnd3.AbstractFeat;
import com.blisek.dnd3manager.dnd3.CreatureModel;
import com.blisek.dnd3manager.dnd3.StringConstants;
import com.blisek.dnd3manager.dnd3.basic.skills.Nasluchiwanie;
import com.blisek.dnd3manager.dnd3.basic.skills.Zauwazanie;

public class CzujnoscTest {
	private CreatureModel model;
	private Map<String, Object> extraParams;
	private static AbstractFeat czujnosc;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		czujnosc = new Czujnosc();
	}

	@Before
	public void setUp() throws Exception {
		model = new CreatureModel();
		model.put(StringConstants.STRENGTH, 13);
		model.put(StringConstants.DEXTERITY, 14);
		model.put(StringConstants.CONSTITUTION, 16);
		model.put(StringConstants.INTELLIGENCE, 17);
		model.put(StringConstants.WISDOM, 19);
		model.put(StringConstants.CHARISMA, 20);
		extraParams = new HashMap<String, Object>();
	}

	@Test
	public void testTurnOnOffFor() {
		assertNull(model.getSkillsMap().get(Nasluchiwanie.SYSTEM_NAME));
		assertNull(model.getSkillsMap().get(Zauwazanie.SYSTEM_NAME));
		
		czujnosc.turnOnFor(model, extraParams);
		
		assertEquals(2, (int)model.getSkillsMap().get(Nasluchiwanie.SYSTEM_NAME));
		assertEquals(2, (int)model.getSkillsMap().get(Zauwazanie.SYSTEM_NAME));
		
		czujnosc.turnOffFor(model, extraParams);
		
		assertEquals(0, (int)model.getSkillsMap().get(Nasluchiwanie.SYSTEM_NAME));
		assertEquals(0, (int)model.getSkillsMap().get(Zauwazanie.SYSTEM_NAME));
	}
	
	
	@Test
	public void testIsOnFor() {
		assertTrue(czujnosc.isAvailableFor(model, extraParams));
		assertFalse(czujnosc.isOnFor(model, extraParams));
		czujnosc.turnOnFor(model, extraParams);
		assertTrue(czujnosc.isOnFor(model, extraParams));
		assertFalse(czujnosc.isAvailableFor(model, extraParams));
		czujnosc.turnOffFor(model, extraParams);
		assertFalse(czujnosc.isOnFor(model, extraParams));
		assertTrue(czujnosc.isAvailableFor(model, extraParams));
	}

}
