package com.blisek.dnd3manager.dnd3.basic.skills;

import com.blisek.dnd3manager.dnd3.AbstractSkill;
import com.blisek.dnd3manager.dnd3.StringConstants;

public class Skakanie extends AbstractSkill {
	public static final String SYSTEM_NAME = "skakanie";

	@Override
	public String getSystemName() {
		return Skakanie.SYSTEM_NAME;
	}

	@Override
	public String getKeyAbilityPrefix() {
		return StringConstants.STRENGTH;
	}

	@Override
	public boolean trainedOnly() {
		return false;
	}

	@Override
	public boolean armorCheckPenalty() {
		return true;
	}


}
