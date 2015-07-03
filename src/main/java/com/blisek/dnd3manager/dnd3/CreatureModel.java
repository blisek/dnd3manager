package com.blisek.dnd3manager.dnd3;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.blisek.dnd3manager.dnd3.equipment.Item;

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
		
		// klucz przechowujący aktywowane efekty dla tego modelu.
		putWithoutNotify(StringConstants.KEY_EFFECTS, new ObservableMap<String, Object>(false));
		
		// przechowuje specjalne flagi opisujące stan postaci. Np. czy może się poruszać, czy jest żywa etc.
		creatureFlags = new BitSet();
		
//		// przechowuje informacje o redukcji obrażeń
//		putWithoutNotify(StringConstants.KEY_DAMAGE_REDUCTION, new HashMap<Integer, Integer>());
		
		// ekwipunek używany obecnie przez postać
		creatureInventory = new Inventory();
		
		// lista wszystkich przedmiotów
		equipment = new ArrayList<Item>();
	}
	
	/**
	 * Zwraca cały ekwipunek postaci.
	 * @return lista przedmiotów posiadanych przez
	 * przez postać.
	 */
	public List<Item> getEquipment() {
		return equipment;
	}
	
	/**
	 * Zwraca aktywny ekwipunek postaci.
	 * @return ekwipunek postaci.
	 */
	public Inventory getCurrentInventory() {
		return creatureInventory;
	}
	
	/**
	 * Informuje czy flaga jest ustawiona na true.
	 * @param bitNum numer bitu z klasy CreatureFlags.
	 * @return true jeśli flaga jest ustawiona inaczej false.
	 */
	public boolean isFlagOn(int bitNum) {
		return creatureFlags.get(bitNum);
	}
	
	/**
	 * Ustawia flagę na true. Jeśli wcześniej flaga nie była
	 * ustawiona informuje o zmianie wszystkich obserwatorów
	 * flag.
	 * @param bitNum numer bitu z klasy CreatureFlags.
	 */
	public void turnFlagOn(int bitNum) {
		if(creatureFlags.get(bitNum))
			return;
		else {
			creatureFlags.set(bitNum, true);
			notifyFlagObservers(bitNum, false, true);
		}
	}
	
	/**
	 * Ustawia flagę na false. Jeśli wcześniej flaga była włączona,
	 * informuje o zmianie wszystkich obserwatorów flag.
	 * @param bitNum numer bitu z klasy CreatureFlags.
	 */
	public void turnFlagOff(int bitNum) {
		if(!creatureFlags.get(bitNum))
			return;
		else {
			creatureFlags.set(bitNum, false);
			notifyFlagObservers(bitNum, true, false);
		}
	}
	
	/**
	 * Dodaje obserwatora flag tego modelu.
	 * @param observer obserwator.
	 * @return true jeśli obserwator został zarejestrowany.
	 */
	public boolean addFlagObserver(FlagObserver observer) {
		if(observer == null)
			throw new NullPointerException();
		if(flagObservers == null)
			flagObservers = new ArrayList<FlagObserver>(2);
		return flagObservers.add(observer);
	}
	
	/**
	 * Usuwa obserwatora flag tego modelu.
	 * @param observer wcześniej zarejestrowany obserwator.
	 * @return true jeśli rozpoznano i usunięto obserwatora.
	 */
	public boolean removeFlagObserver(FlagObserver observer) {
		if(observer == null)
			throw new NullPointerException();
		return flagObservers.remove(observer);
	}
	
	/**
	 * Ustawia redukcję obrażeń dla danego typu obrażeń.
	 * @param reductionType typ obrażeń z klasy DamageType.
	 * @param value nowa wartość. Jeśli z typem obrażeń była powiązana inna
	 * wartość zostanie ona zwrócona. Jeśli nie zostanie zwrócone 0.
	 * @return poprzednia redukcja dla danych obrażeń. Jeśli nie było poprzedniej
	 * zostanie zwrócone 0.
	 */
	@SuppressWarnings("unchecked")
	public int setDamageReduction(int reductionType, int value) {
		Object tmp = get(StringConstants.KEY_DAMAGE_REDUCTION);
		Map<Integer, Integer> mapOfDR;
		if(tmp == null) {
			mapOfDR = new HashMap<Integer, Integer>();
			put(StringConstants.KEY_DAMAGE_REDUCTION, mapOfDR);
		} else {
			mapOfDR = (Map<Integer,Integer>)tmp;
		}
		
		tmp = mapOfDR.put(reductionType, value);
		return tmp == null ? 0 : (int)tmp;
	}
	
	/**
	 * Zwraca redukcję obrażeń dla danego typu obrażeń.
	 * @param reductionType typ obrażeń z klasy DamageType.
	 * @return redukcja obrażeń dla danego typu obrażeń, bądź 0 jeśli brak takiej redukcji.
	 */
	@SuppressWarnings("unchecked")
	public int getDamageReduction(int reductionType) {
		Object tmp = get(StringConstants.KEY_DAMAGE_REDUCTION);
		if(tmp == null)
			return 0;
		tmp = ((Map<Integer,Integer>)tmp).get(reductionType);
		return tmp == null ? 0 : (int)tmp;
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
	 * Zwraca mapę elementów klucz-wartość, gdzie klucz jest nazwą systemową efektu,
	 * a wartość danymi do użytku efektu.
	 * @return mapa efektów.
	 */
	@SuppressWarnings("unchecked")
	public ObservableMap<String, Object> getEffectsMap() {
		return (ObservableMap<String, Object>)get(StringConstants.KEY_EFFECTS);
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
	
	public void addLevelTo(String systemName) {
		if(systemName == null)
			throw new NullPointerException();
		List<ClassLevelInfo> cliList = getClassInfos();
		int lastElement = cliList.size() - 1;
		ClassLevelInfo cli = cliList.get(lastElement);
		if(cli.systemName.equals(systemName)) {
			cliList.add(new ClassLevelInfo(systemName, cliList.remove(lastElement).level + 1));
		} else {
			cli = new ClassLevelInfo(systemName, 1);
		}
		
		cliList.add(cli);
	}
	
	private void notifyFlagObservers(int bNum, boolean oVal, boolean nVal) {
		if(flagObservers != null)
			flagObservers.stream().forEach(o -> o.flagChanged(this, bNum, oVal, nVal));
	}

	/**
	 * Serial version UID
	 */
	private static final long serialVersionUID = -5491615991801707374L;
	
	private final BitSet creatureFlags;
	private final Inventory creatureInventory;
	private final List<Item> equipment;
	private List<FlagObserver> flagObservers;
	
}

