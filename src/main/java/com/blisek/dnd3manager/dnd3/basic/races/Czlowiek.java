package com.blisek.dnd3manager.dnd3.basic.races;

import java.util.Map;

import com.blisek.dnd3manager.dnd3.AbstractRace;
import com.blisek.dnd3manager.dnd3.CreatureModel;
import com.blisek.dnd3manager.dnd3.ExtraParamsHelper;
import com.blisek.dnd3manager.dnd3.Size;
import com.blisek.dnd3manager.dnd3.StringConstants;
import com.blisek.dnd3manager.dnd3.basic.BasicLanguages;

public class Czlowiek extends AbstractRace {
	public static final String SYSTEM_NAME = "czlowiek";

	@Override
	public String getSystemName() {
		return Czlowiek.SYSTEM_NAME;
	}

	@Override
	public void turnOnFor(CreatureModel model, Map<String, Object> extraParams) {
		Boolean firstEverLevel = ExtraParamsHelper.getBoolean(extraParams, StringConstants.P_FIRST_LEVEL);
		if(firstEverLevel != null && firstEverLevel)
			firstLevel(model, extraParams);
		else
			nextLevel(model, extraParams);
	}

	@Override
	public void turnOffFor(CreatureModel model, Map<String, Object> extraParams) {
		// jeśli rasa jest usuwana na innym etapie niż podczas
		// tworzenia postaci zostanie zgłoszony wyjątek
		// IllegalStateException.
		Boolean firstLevel = ExtraParamsHelper.getBoolean(extraParams, StringConstants.P_FIRST_LEVEL);
		if(firstLevel == null || !firstLevel) 
			throw new IllegalStateException();
		model.remove(StringConstants.SIZE);
		model.remove(StringConstants.SPEED);
		ExtraParamsHelper.increaseFeatsCount(extraParams, -1);
		ExtraParamsHelper.increaseSkillsRanks(extraParams, -4);
		model.getLanguages().add(BasicLanguages.COMMON);
	}
	
	private void firstLevel(CreatureModel model, Map<String,Object> extraParams) {
		ExtraParamsHelper.increaseSkillsRanks(extraParams, 4);
		ExtraParamsHelper.increaseFeatsCount(extraParams, 1);
		model.put(StringConstants.SIZE, Size.MEDIUM);
		model.put(StringConstants.SPEED, 9.0f);
	}
	
	private void nextLevel(CreatureModel model, Map<String, Object> extraParams) {
		ExtraParamsHelper.increaseSkillsRanks(extraParams, 1);
	}

}
