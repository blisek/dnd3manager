package com.blisek.dnd3manager.dnd3.fight;

import java.util.Collection;

import com.blisek.dnd3manager.dnd3.CreatureController;

public interface IFightContext {

	RandomRollsGenerator getRandomRollsGenerator();
	
	Collection<CreatureController> getParticipants();
}
