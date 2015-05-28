package com.blisek.dnd3manager.dnd3.basic;

import java.util.Collection;

import com.blisek.dnd3manager.dnd3.AbstractFeat;
import com.blisek.dnd3manager.dnd3.AbstractSkill;
import com.blisek.dnd3manager.dnd3.FeatsInfo;
import com.blisek.dnd3manager.dnd3.SkillsInfo;
import com.blisek.dnd3manager.dnd3.basic.feats.Feats;
import com.blisek.dnd3manager.dnd3.basic.skills.Skills;

public class PackageInfo implements SkillsInfo, FeatsInfo {

	@Override
	public Collection<AbstractFeat> getFeats() {
		return Feats.getFeats();
	}

	@Override
	public Collection<AbstractSkill> getSkills() {
		return Skills.getSkills();
	}

}
