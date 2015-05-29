package com.blisek.dnd3manager.dnd3;

import java.util.Map;
import java.util.Optional;

public class CreatureFactoryInterfaces {
	/**
	 * Z kluczem o tej nazwie związana jest fabryka innych
	 * parametrów takich jak wybór bóstwa. Parametr ten jest
	 * dostępny od początku istnienia słownika extraParams.
	 */
	public static final String P_OTHER_PARAMETERS_FACTORY = "oth_param_fact";
	
	/**
	 * Metoda wytwórcza zwracająca wartości
	 * do użycia w atrybutach.
	 * @author bartek
	 *
	 */
	public static interface AbilitiesFactory {
		public int[] getAbilities(Map<String, Object> extraParams);
	}
	
	/**
	 * Metoda wytwórcza zwracająca rasę.
	 * @author bartek
	 *
	 */
	public static interface RaceFactory {
		public void selectRace(CreatureModel model, Map<String, Object> extraParams);
	}
	
	/**
	 * Metoda wytwórcza dająca klasę postaci.
	 * @author bartek
	 *
	 */
	public static interface ClassFactory {
		public void selectClass(CreatureModel model, Map<String, Object> extraParams);
	}
	
	/**
	 * Metoda przyporządkowująca, wcześniej wylosowane, wartości
	 * do konkretnych atrybutów.
	 * @author bartek
	 *
	 */
	public static interface AbilitiesSelectionFactory {
		public void selectAbilities(CreatureModel model, int[] abilities, Map<String, Object> extraParams);
	}
	
	/**
	 * Wybiera umiejętności i przypisuje je modelowi.
	 * @author bartek
	 *
	 */
	public static interface SkillsFactory {
		public void selectSkills(CreatureModel model, int ranks, Map<String, Object> extraParams);
	}
	
	/**
	 * Wybiera atuty.
	 * @author bartek
	 *
	 */
	public static interface FeatsFactory {
		/**
		 * Wybiera atuty i przypisuje je do modelu. Wybór można ograniczyć
		 * do iterowalnej kolekcji przekazanej w argumencie limitation.
		 * @param model model postaci.
		 * @param num_of_feats liczba atutów do wyboru.
		 * @param limitation kolekcja z której będą wybierane atuty.
		 */
		public void selectFeats(CreatureModel model, int num_of_feats, Optional<Iterable<AbstractFeat>> limitation);
	}
	
	/**
	 * Odpowiedzialny za wybór innych parametrów. Np. bóstwa.
	 * @author bartek
	 *
	 */
	public static interface OtherParametersFactory {
		public String selectDeity();
	}
}
