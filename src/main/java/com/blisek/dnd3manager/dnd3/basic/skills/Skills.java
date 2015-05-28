package com.blisek.dnd3manager.dnd3.basic.skills;

import java.util.Arrays;
import java.util.Collection;

import com.blisek.dnd3manager.dnd3.AbstractSkill;

public class Skills {
	private static final AbstractSkill[] skills = {
		new Nasluchiwanie(),
		new Zauwazanie()
	};
	
	public static Collection<AbstractSkill> getSkills() {
		return Arrays.<AbstractSkill>asList(skills);
	}
}
