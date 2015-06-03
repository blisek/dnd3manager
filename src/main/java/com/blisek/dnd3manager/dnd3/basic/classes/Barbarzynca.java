package com.blisek.dnd3manager.dnd3.basic.classes;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import com.blisek.dnd3manager.dnd3.AbstractClass;
import com.blisek.dnd3manager.dnd3.AbstractSkill;
import com.blisek.dnd3manager.dnd3.CreatureModel;
import com.blisek.dnd3manager.dnd3.ExtraParamsHelper;
import com.blisek.dnd3manager.dnd3.NumericConstants;
import com.blisek.dnd3manager.dnd3.StringConstants;

public class Barbarzynca extends AbstractClass {
	public static final String SYSTEM_NAME = "barbarzynca";
	public static final String[] CLASS_SKILLS = {
		
	};
	public static final String[] ABILITIES_PRIORITY = {
		StringConstants.STRENGTH, StringConstants.DEXTERITY, StringConstants.CONSTITUTION,
		StringConstants.WISDOM, StringConstants.CHARISMA, StringConstants.INTELLIGENCE
	};

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
		return model.sumLevelsIn(Barbarzynca.SYSTEM_NAME) > 0;
	}

	@Override
	public void turnOnFor(CreatureModel model, Map<String, Object> extraParams) {
		// TODO Auto-generated method stub
		throw new NotImplementedException();
	}

	@Override
	public void turnOffFor(CreatureModel model, Map<String, Object> extraParams) {
		throw new NotImplementedException();
	}

	@Override
	public int getHitDice() {
		return 12;
	}

	@Override
	public int[] getAttacks(CreatureModel model) {
		int level = model.sumLevelsIn(Barbarzynca.SYSTEM_NAME);
		int[] attacks = new int[Math.min((level-1) / 5 + 1, 4)];
		int baseAttack = getBaseAttackMod(level);
		for(int i = 0; i < attacks.length; ++i)
			attacks[i] = baseAttack - i * 5;
		return attacks;
	}

	@Override
	public int getSkillRanksForLevel() {
		return 4;
	}

	@Override
	public int getFortitudeMod(int level) {
		return (level / 2) + 2;
	}

	@Override
	public int getReflexMod(int level) {
		return level / 3;
	}

	@Override
	public int getWillMod(int level) {
		return level / 3;
	}

	@Override
	public int getBaseAttackMod(int level) {
		return Math.min(level, 20);
	}

	@Override
	public Collection<String> getClassSkills() {
		throw new NotImplementedException();
//		return Arrays.asList(Barbarzynca.CLASS_SKILLS);
	}

	@Override
	public Collection<String> getAbilitiesByPriorityDescending() {
		return Arrays.asList(Barbarzynca.ABILITIES_PRIORITY);
	}

	@Override
	public int getAlignmentsFlag() {
		return NumericConstants.AL_CHAOTIC_EVIL | NumericConstants.AL_CHAOTIC_GOOD |
				NumericConstants.AL_CHAOTIC_NEUTRAL | NumericConstants.AL_NEUTRAL |
				NumericConstants.AL_NEUTRAL_EVIL | NumericConstants.AL_NEUTRAL_GOOD;
	}
	
	

}
