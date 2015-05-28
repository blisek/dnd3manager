package com.blisek.dnd3manager.dnd3.basic.skills;

import com.blisek.dnd3manager.dnd3.AbstractSkill;
import com.blisek.dnd3manager.dnd3.StringConstants;

public class Zauwazanie extends AbstractSkill {
	public static final String SYSTEM_NAME = "zauwazanie";

	@Override
	public String getSystemName() {
		return Zauwazanie.SYSTEM_NAME;
	}

	@Override
	public String getKeyAbilityPrefix() {
		return StringConstants.WISDOM;
	}

	@Override
	public boolean trainedOnly() {
		return false;
	}

}
