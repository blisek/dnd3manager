package com.blisek.dnd3manager.dnd3.basic.races;

import java.util.Map;

import com.blisek.dnd3manager.dnd3.AbstractRace;
import com.blisek.dnd3manager.dnd3.CreatureModel;
import com.blisek.dnd3manager.dnd3.ExtraParamsHelper;
import com.blisek.dnd3manager.dnd3.Size;
import com.blisek.dnd3manager.dnd3.StringConstants;

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
		// TODO Auto-generated method stub

	}
	
	private void firstLevel(CreatureModel model, Map<String,Object> extraParams) {
		ExtraParamsHelper.increaseSkillsRanks(extraParams, 4);
		ExtraParamsHelper.increaseFeatsCount(extraParams, 1);
		extraParams.put(StringConstants.SIZE, Size.MEDIUM);
	}
	
	private void nextLevel(CreatureModel model, Map<String, Object> extraParams) {
		
	}

}
