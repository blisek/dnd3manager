package com.blisek.dnd3manager.dnd3.basic.skills;

import com.blisek.dnd3manager.dnd3.AbstractSkill;
import com.blisek.dnd3manager.dnd3.StringConstants;

public abstract class Rzemioslo extends AbstractSkill {

	@Override
	public String getKeyAbilityPrefix() {
		return StringConstants.INTELLIGENCE;
	}

	@Override
	public boolean trainedOnly() {
		return false;
	}

}