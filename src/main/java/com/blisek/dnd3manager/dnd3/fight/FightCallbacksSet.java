package com.blisek.dnd3manager.dnd3.fight;

import com.blisek.dnd3manager.dnd3.CreatureController;

/**
 * Zbior metod do komunikowania sie z silnikiem.
 * @author BLISEK
 *
 */
public interface FightCallbacksSet {
	
	/**
	 * Zwraca inicjatywe danego uczestnika.
	 * @param controller kontroler postaci.
	 * @return test inicjatywy z uwzglednieniem premii.
	 */
	public int getInitiativeFor(CreatureController controller);
}
