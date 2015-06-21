package com.blisek.dnd3manager.dnd3.basic.specialabilities;

import java.util.Map;

import com.blisek.dnd3manager.dnd3.AbstractSpecialAbility;
import com.blisek.dnd3manager.dnd3.CreatureModel;

public class RedukcjaObrazen extends AbstractSpecialAbility {
	public static final String SYSTEM_NAME = "red_obr";

	@Override
	public boolean isAvailableFor(CreatureModel model, Object... extraParams) {
		// z reguły nie można po prostu zdobyć redukcji obrażeń. Bierze się
		// ona najczęściej z rasy, albo klasy.
		return false;
	}

	@Override
	public String getSystemName() {
		return RedukcjaObrazen.SYSTEM_NAME;
	}

	@Override
	public void turnOnFor(CreatureModel model, Map<String, Object> extraParams) {
		// TODO Auto-generated method stub

	}

	@Override
	public void turnOffFor(CreatureModel model, Map<String, Object> extraParams) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isPassive() {
		return true;
	}
	
	
	public static int getReductionFor(CreatureModel model, String type) {
		String keyName = String.format("%s_%s", SYSTEM_NAME, type);
		Map<String, Object> abilitiesMap = model.getSpecialAbilitiesMap();
		
	}
	
}
