package com.blisek.dnd3manager.dnd3.basic.skills;

import java.util.Arrays;
import java.util.Collection;

import com.blisek.dnd3manager.dnd3.AbstractSkill;

public class Skills {
	private static final AbstractSkill[] skills = {
		new Jezdziectwo(),
		new Nasluchiwanie(),
		new Plywanie(),
		new PostepowanieZeZwierzetami(),
		new RzemiosloInne(),
		new RzemiosloPlatnerstwo(),
		new RzemiosloWytwarzanieBroni(),
		new RzemiosloWytwarzanieLukow(),
		new Skakanie(),
		new Wspinaczka(),
		new WyczucieKierunku(),
		new Zastraszanie(),
		new Zauwazanie(),
		new ZnajomoscDziczy()
	};
	
	public static Collection<AbstractSkill> getSkills() {
		return Arrays.<AbstractSkill>asList(skills);
	}
}
