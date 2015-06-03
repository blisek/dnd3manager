package com.blisek.dnd3manager.dnd3.basic.skills;

import com.blisek.dnd3manager.dnd3.AbstractSkill;
import com.blisek.dnd3manager.dnd3.StringConstants;

public class Zastraszanie extends AbstractSkill {
	public static final String SYSTEM_NAME = "zastraszanie";

	@Override
	public String getSystemName() {
		return Zastraszanie.SYSTEM_NAME;
	}

	@Override
	public String getKeyAbilityPrefix() {
		return StringConstants.CHARISMA;
	}

	@Override
	public boolean trainedOnly() {
		return false;
	}

}
