package com.blisek.dnd3manager.dnd3;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Model postaci. Metody wstawiania, zastępowania i usuwania zgłaszają
 * te czynności obserwatorom implementującym interfejs CreatureModelObservator.
 * Metody putAll i replaceAll niedostępne.
 * @author bartek
 */
public class CreatureModel extends ObservableMap<String, Object> {
	
	public CreatureModel() {
		super(false);
		
		// klucz przechowujący listę z poziomami zdobytymi w różnych klasach.
		putWithoutNotify(StringConstants.KEY_CLASS, new LinkedList<ClassLevelInfo>());
		
		// klucz przechowujący informację o atutach dla tej postaci.
		// Przechowywane są w postaci klucz-wartość, gdzie klucz to nazwa systemowa atutu
		// a wartość to obiekt, którego tożsamość (typ, zawartość etc.) jest znana i do użytku
		// wewnętrznego atutu.
		// Np. jeśli atutem byłaby Biegłość w broni prostej, wartością mogłaby być lista broni
		// w których postać tę biegłość posiada.
		putWithoutNotify(StringConstants.KEY_FEATS, new ObservableMap<String, Object>(false));
		
		// Klucz przechowujący informację o posiadanych umiejętnościach.
		// Dane przechowywane są w postaci klucz-wartość, gdzie klucz jest nazwą systemową
		// umiejętności, a wartość liczbą rang.
		putWithoutNotify(StringConstants.KEY_SKILLS, new ObservableMap<String, Integer>(false));
		
		// klucz przechowujący informacje o specjalnych umiejętnościach postaci.
		putWithoutNotify(StringConstants.KEY_SPECIAL_ABILITIES, new ObservableMap<String, Object>(false));
	}
	
	/**
	 * Zwraca listę par postaci (nazwa systemowa klasy, liczba poziomów).
	 * Kolejność elementów na liście odpowiada kolejności zdobywania doświadczenia
	 * w posiadanych klasach (co może być istotne np. w przypadku wieloklasowca z
	 * poziomem w klasie Mnich).
	 * @return lista obiektów ClassInfo.
	 */
	@SuppressWarnings("unchecked")
	public List<ClassLevelInfo> getClassInfos() {
		return (List<ClassLevelInfo>)get(StringConstants.KEY_CLASS);
	}
	
	/**
	 * Zwraca mapę atutów w postaci klucz-wartość, gdzie klucz jest nazwą systemową
	 * atutu, a wartością dane wykorzystywane przez ten atut.
	 * @return mapa atutów
	 */
	@SuppressWarnings("unchecked")
	public ObservableMap<String, Object> getFeatsMap() {
		return (ObservableMap<String, Object>)get(StringConstants.KEY_FEATS);
	}
	
	/**
	 * Zwraca mapę klucz-wartość umiejętności postaci, gdzie klucz jest nazwą
	 * systemową umiejętności, a wartością jest liczba rang w tej umiejętności
	 * bez uwzględnienia premii z modyfikatora atrybutu kluczowego.
	 * @return mapa umiejętności.
	 */
	@SuppressWarnings("unchecked")
	public ObservableMap<String, Integer> getSkillsMap() {
		return (ObservableMap<String, Integer>)get(StringConstants.KEY_SKILLS);
	}
	
	/**
	 * Zwraca mapę klucz-wartość specjalnych umiejętności/zdolności postaci, gdzie
	 * klucz jest nazwą systemową, a wartość jest do użytku wewnętrznego powiązanego
	 * atutu. W tym słowniku może znaleźć się np. Szał barbarzyńcy.
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public ObservableMap<String, Object> getSpecialAbilitiesMap() {
		return (ObservableMap<String, Object>)get(StringConstants.KEY_SPECIAL_ABILITIES);
	}
	
	/**
	 * Zwraca modyfikowalną listę języków. Lista nazw systemowych.
	 * @return lista języków.
	 */
	@SuppressWarnings("unchecked")
	public Set<String> getLanguages() {
		Object lang = get(StringConstants.LANGUAGES);
		Set<String> langList;
		if(lang == null) {
			langList = new HashSet<String>();
			putWithoutNotify(StringConstants.LANGUAGES, langList);
		} else {
			langList = (Set<String>)lang;
		}
		
		return langList;
	}
	
	/**
	 * Zlicza liczbę poziomów w klasie o danej nazwie. Jeśli postać nie ma
	 * żadnych poziomów w tej klasie zwracane jest 0.
	 * @param classSystemName nazwa systemowa klasy.
	 * @return liczba poziomów w klasie.
	 */
	public int sumLevelsIn(String classSystemName) {
		if(classSystemName == null)
			throw new NullPointerException();
		return getClassInfos().parallelStream()
				.filter(classInfo -> classInfo.systemName.equals(classSystemName))
				.mapToInt(classInfo -> classInfo.level)
				.sum();
	}

	/**
	 * Serial version UID
	 */
	private static final long serialVersionUID = -5491615991801707374L;
	
}

