package com.blisek.dnd3manager.dnd3;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;

public class AbilityHelperTest {
	private static CreatureModel model;
	private static CreatureController controller;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		model = new CreatureModel();
		controller = new CreatureController(model);
		model.put(StringConstants.STRENGTH, 10);
		model.put(StringConstants.DEXTERITY, 11);
		model.put(StringConstants.CONSTITUTION, 13);
		model.put(StringConstants.INTELLIGENCE, 14);
		model.put(StringConstants.WISDOM, 17);
		model.put(StringConstants.CHARISMA, 20);
	}

	@Test
	public void testGetModifierCreatureControllerString() {
		assertEquals(controller.getStrengthMod(false), 
				AbilityHelper.getModifier(controller, AbilityHelper.abilityTypeToAbilityPrefix(AbilityType.STRENGTH)));
		assertEquals(controller.getDexterityMod(false), 
				AbilityHelper.getModifier(controller, AbilityHelper.abilityTypeToAbilityPrefix(AbilityType.DEXTERITY)));
		assertEquals(controller.getConstitutionMod(false), 
				AbilityHelper.getModifier(controller, AbilityHelper.abilityTypeToAbilityPrefix(AbilityType.CONSTITUTION)));
		assertEquals(controller.getIntelligenceMod(false), 
				AbilityHelper.getModifier(controller, AbilityHelper.abilityTypeToAbilityPrefix(AbilityType.INTELLIGENCE)));
		assertEquals(controller.getWisdomMod(false), 
				AbilityHelper.getModifier(controller, AbilityHelper.abilityTypeToAbilityPrefix(AbilityType.WISDOM)));
		assertEquals(controller.getCharismaMod(false), 
				AbilityHelper.getModifier(controller, AbilityHelper.abilityTypeToAbilityPrefix(AbilityType.CHARISMA)));
	}

}
