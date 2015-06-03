package com.blisek.dnd3manager.dnd3.basic.classes;

import java.util.Collection;
import java.util.Map;

import com.blisek.dnd3manager.dnd3.AbstractClass;
import com.blisek.dnd3manager.dnd3.AbstractSkill;
import com.blisek.dnd3manager.dnd3.CreatureModel;
import com.blisek.dnd3manager.dnd3.ExtraParamsHelper;
import com.blisek.dnd3manager.dnd3.NumericConstants;
import com.blisek.dnd3manager.dnd3.StringConstants;

public class Barbarzynca extends AbstractClass {
	public static final String SYSTEM_NAME = "barbarzynca";

	@Override
	public boolean isAvailableFor(CreatureModel model, Object... extraParams) {
		int alignment = ExtraParamsHelper.getIntegerDefault0(model, StringConstants.ALIGNMENT);
		return (alignment & getAlignmentsFlag()) > 0;
	}

	@Override
	public String getSystemName() {
		return Barbarzynca.SYSTEM_NAME;
	}

	@Override
	public boolean isOnFor(CreatureModel model, Map<String, Object> extraParams) {
	}

	@Override
	public void turnOnFor(CreatureModel model, Map<String, Object> extraParams) {
		// TODO Auto-generated method stub

	}

	@Override
	public void turnOffFor(CreatureModel model, Map<String, Object> extraParams) {
	}

	@Override
	public int getHitDice() {
		return 12;
	}

	@Override
	public int[] getAttacks(CreatureModel model) {
	}

	@Override
	public int getSkillRanksForLevel() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getFortitudeMod(int level) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getReflexMod(int level) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getWillMod(int level) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getBaseAttackMod(int level) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Collection<AbstractSkill> getClassSkills() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] getAbilitiesByPriorityDescending() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getAlignmentsFlag() {
		return NumericConstants.AL_CHAOTIC_EVIL | NumericConstants.AL_CHAOTIC_GOOD |
				NumericConstants.AL_CHAOTIC_NEUTRAL | NumericConstants.AL_NEUTRAL |
				NumericConstants.AL_NEUTRAL_EVIL | NumericConstants.AL_NEUTRAL_GOOD;
	}
	
	

}
