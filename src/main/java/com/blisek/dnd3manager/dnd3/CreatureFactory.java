package com.blisek.dnd3manager.dnd3;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.blisek.dnd3manager.dnd3.CreatureFactoryInterfaces.AbilitiesFactory;
import com.blisek.dnd3manager.dnd3.CreatureFactoryInterfaces.AbilitiesSelectionFactory;
import com.blisek.dnd3manager.dnd3.CreatureFactoryInterfaces.ClassFactory;
import com.blisek.dnd3manager.dnd3.CreatureFactoryInterfaces.FeatsFactory;
import com.blisek.dnd3manager.dnd3.CreatureFactoryInterfaces.OtherParametersFactory;
import com.blisek.dnd3manager.dnd3.CreatureFactoryInterfaces.RaceFactory;
import com.blisek.dnd3manager.dnd3.CreatureFactoryInterfaces.SkillsFactory;

public class CreatureFactory {
	
	public CreatureFactory(AbilitiesFactory abilitiesFactory,
			AbilitiesSelectionFactory abilitiesSelectionFactory,
			RaceFactory raceFactory, ClassFactory classFactory,
			SkillsFactory skillsFactory, FeatsFactory featsFactory,
			OtherParametersFactory otherFactory) {
		super();
		if(abilitiesFactory == null || abilitiesSelectionFactory == null ||
				raceFactory == null || classFactory == null || skillsFactory == null ||
				featsFactory == null || otherFactory == null)
			throw new NullPointerException();
		this.abilitiesFactory = abilitiesFactory;
		this.abilitiesSelectionFactory = abilitiesSelectionFactory;
		this.raceFactory = raceFactory;
		this.classFactory = classFactory;
		this.skillsFactory = skillsFactory;
		this.featsFactory = featsFactory;
		this.otherFactory = otherFactory;
	}
	
	
	@SuppressWarnings("unchecked")
	public CreatureModel generateModel() {
		CreatureModel model = new CreatureModel();
		Map<String, Object> extraParams = new HashMap<>();
		
		extraParams.put(CreatureFactoryInterfaces.P_OTHER_PARAMETERS_FACTORY, otherFactory);
		
		int[] abilities = abilitiesFactory.getAbilities(extraParams);
		raceFactory.selectRace(model, extraParams);
		classFactory.selectClass(model, extraParams);
		abilitiesSelectionFactory.selectAbilities(model, abilities, extraParams);
		
		// wyciągnięcie rang
		Object ranks = extraParams.remove(StringConstants.P_RANKS);
		skillsFactory.selectSkills(model, (ranks == null) ? 0 : (int)ranks, extraParams);
		
		// wyciągnięcie atutów
		Object feats = extraParams.remove(StringConstants.P_FEATS);
		int feats_num = (feats == null) ? 0 : (int)feats;
		feats = extraParams.remove(StringConstants.P_FEATS_LIST);
		Optional<Iterable<AbstractFeat>> featsIter;
		if(feats == null) {
			featsIter = Optional.<Iterable<AbstractFeat>>empty();
		} else {
			featsIter = Optional.<Iterable<AbstractFeat>>of((Iterable<AbstractFeat>)feats);
		}
		featsFactory.selectFeats(model, feats_num, featsIter);
		
		return model;
	}
	
	
	private final AbilitiesFactory abilitiesFactory;
	private final AbilitiesSelectionFactory abilitiesSelectionFactory;
	private final RaceFactory raceFactory;
	private final ClassFactory classFactory;
	private final SkillsFactory skillsFactory;
	private final FeatsFactory featsFactory;
	private final OtherParametersFactory otherFactory;
}
