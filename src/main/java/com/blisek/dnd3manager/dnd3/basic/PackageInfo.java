package com.blisek.dnd3manager.dnd3.basic;

import java.util.Collection;

import com.blisek.dnd3manager.dnd3.AbstractClass;
import com.blisek.dnd3manager.dnd3.AbstractFeat;
import com.blisek.dnd3manager.dnd3.AbstractRace;
import com.blisek.dnd3manager.dnd3.AbstractSkill;
import com.blisek.dnd3manager.dnd3.ClassInfo;
import com.blisek.dnd3manager.dnd3.FeatsInfo;
import com.blisek.dnd3manager.dnd3.RaceInfo;
import com.blisek.dnd3manager.dnd3.SkillsInfo;
import com.blisek.dnd3manager.dnd3.basic.classes.Classes;
import com.blisek.dnd3manager.dnd3.basic.feats.Feats;
import com.blisek.dnd3manager.dnd3.basic.races.Races;
import com.blisek.dnd3manager.dnd3.basic.skills.Skills;

public class PackageInfo implements SkillsInfo, FeatsInfo, ClassInfo, RaceInfo {

	@Override
	public Collection<AbstractFeat> getFeats() {
		return Feats.getFeats();
	}

	@Override
	public Collection<AbstractSkill> getSkills() {
		return Skills.getSkills();
	}

	@Override
	public Collection<AbstractClass> getClasses() {
		return Classes.getClasses();
	}

	@Override
	public Collection<AbstractRace> getRaces() {
		return Races.getRaces();
	}
	
}
