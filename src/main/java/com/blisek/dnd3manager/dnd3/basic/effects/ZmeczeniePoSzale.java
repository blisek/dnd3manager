package com.blisek.dnd3manager.dnd3.basic.effects;

import java.util.Map;

import com.blisek.dnd3manager.dnd3.AbstractEffect;
import com.blisek.dnd3manager.dnd3.CreatureController;
import com.blisek.dnd3manager.dnd3.CreatureModel;
import com.blisek.dnd3manager.dnd3.Duration;
import com.blisek.dnd3manager.dnd3.StringConstants;

public class ZmeczeniePoSzale extends AbstractEffect {
	public static final String SYSTEM_NAME = "e_zmposzale";
	public static final String STR_KEY = String.format("%s_%s", StringConstants.STRENGTH, SYSTEM_NAME);
	public static final String DEX_KEY = String.format("%s_%s", StringConstants.DEXTERITY, SYSTEM_NAME);

	@Override
	public String getSystemName() {
		return ZmeczeniePoSzale.SYSTEM_NAME;
	}

	@Override
	public void turnOnFor(CreatureModel model, Map<String, Object> extraParams) {
		model.put(SYSTEM_NAME, 0);
		model.put(STR_KEY, -2);
		model.put(DEX_KEY, -2);
	}

	@Override
	public void turnOffFor(CreatureModel model, Map<String, Object> extraParams) {
		model.remove(DEX_KEY);
		model.remove(STR_KEY);
		model.remove(SYSTEM_NAME);
	}

	@Override
	public Duration getDurationFor(CreatureModel model,
			CreatureController controller, Map<String, Object> extraParams) {
		
	}

}
