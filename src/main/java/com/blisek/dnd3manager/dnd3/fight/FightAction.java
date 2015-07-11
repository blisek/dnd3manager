package com.blisek.dnd3manager.dnd3.fight;

import java.util.Collection;
import java.util.Map;

import com.blisek.dnd3manager.dnd3.CreatureController;

public interface FightAction {
	
	/**
	 * Zwraca nazwe systemowa tej akcji.
	 * @return nazwa systemowa.
	 */
	String getSystemName();

	/**
	 * Zwraca nazwe akcji w formie zrozumialej
	 * dla uzytkownika.
	 * @return lancuch znakow.
	 */
	String getName();
	
	/**
	 * Zwraca opis akcji w formie zrozumialej
	 * dla uzytkownika.
	 * @return lancuch znakow.
	 */
	String getDescription();
	
	/**
	 * Zwraca koszt tej akcji np. 1 akcja, calorundowa itp.
	 * @return
	 */
	FightActionCost getCost();
	
	/**
	 * Zwraca typ akcji. Np. ofensywna, defensywna etc.
	 * @return typ akcji.
	 */
	ActionType getActionType();
	
	/**
	 * Informuje o celu akcji. Np. dla walki wiekszoscia broni
	 * jest to jeden przeciwnik, dla wirujacego ataku wszyscy
	 * przeciwnicy w zasiegu broni etc.
	 * @return typ celu.
	 */
	TargetType getTargetType();
	
	/**
	 * Na ilu uczestnikach przeprowadzana jest
	 * ta akcja.
	 * @return liczba uczestnikow 1 v (n < ALL) v ALL.
	 */
	ActionTargetsCountType getRangeType();
	
	/**
	 * Wykonuje akcje.
	 * @param controller
	 * @param extraParams
	 */
	void accept(IFightContext fightContext,
			CreatureController controller, 
			Collection<CreatureController> targets, 
			Map<String, Object> extraParams);
}
