package com.blisek.dnd3manager.dnd3.fight;

public enum ActionTargetsCountType {
	/**
	 * Akcji podlega jeden uczestnik w zasiegu.
	 */
	ONE_IN_RANGE,
	
	/**
	 * Akcji podlega kilku uczestnikow w zasiegu.
	 */
	FIRST_N_IN_RANGE,
	
	/**
	 * Akcji podlegaja wszyscy w zasiegu.
	 */
	ALL_IN_RANGE,
}
