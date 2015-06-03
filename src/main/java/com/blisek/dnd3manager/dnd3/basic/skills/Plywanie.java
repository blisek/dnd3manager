package com.blisek.dnd3manager.dnd3.basic.skills;

import com.blisek.dnd3manager.dnd3.AbstractSkill;
import com.blisek.dnd3manager.dnd3.StringConstants;

public class Plywanie extends AbstractSkill {
	public static final String SYSTEM_NAME = "plywanie";

	@Override
	public String getSystemName() {
		return Plywanie.SYSTEM_NAME;
	}

	@Override
	public String getKeyAbilityPrefix() {
		return StringConstants.STRENGTH;
	}

	@Override
	public boolean trainedOnly() {
		return false;
	}

}
