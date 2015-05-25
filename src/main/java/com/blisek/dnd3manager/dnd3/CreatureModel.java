package com.blisek.dnd3manager.dnd3;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiFunction;

/**
 * Model postaci. Metody wstawiania, zastępowania i usuwania zgłaszają
 * te czynności obserwatorom implementującym interfejs CreatureModelObservator.
 * Metody putAll i replaceAll niedostępne.
 * @author bartek
 */
public class CreatureModel extends ConcurrentHashMap<String, Object> {
	
	public CreatureModel() {
		observators = new LinkedList<CreatureModelObservator>();
		
		// klucz przechowujący listę z poziomami zdobytymi w różnych klasach.
		put(StringConstants.KEY_CLASS, new LinkedList<ClassInfo>());
		
		// klucz przechowujący informację o atutach dla tej postaci.
		// Przechowywane są w postaci klucz-wartość, gdzie klucz to nazwa systemowa atutu
		// a wartość to obiekt, którego tożsamość (typ, zawartość etc.) jest znana i do użytku
		// wewnętrznego atutu.
		// Np. jeśli atutem byłaby Biegłość w broni prostej, wartością mogłaby być lista broni
		// w których postać tę biegłość posiada.
		put(StringConstants.KEY_FEATS, new HashMap<String, Object>());
		
		// Klucz przechowujący informację o posiadanych umiejętnościach.
		// Dane przechowywane są w postaci klucz-wartość, gdzie klucz jest nazwą systemową
		// umiejętności, a wartość liczbą rang.
		put(StringConstants.KEY_SKILLS, new HashMap<String, Integer>());
	}

	@Override
	public Object put(String key, Object value) {
		Object o = super.put(key, value);
		observators.parallelStream().forEach((CreatureModelObservator observer) -> observer.onNewKeyPut(this, key, o, value));
		return o;
	}

	@Override
	public void putAll(Map<? extends String, ? extends Object> m) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void replaceAll(
			BiFunction<? super String, ? super Object, ? extends Object> function) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Object remove(Object key) {
		Object o = super.remove(key);
		observators.parallelStream().forEach((observer) -> observer.onKeyRemoved(this, (String)key, o));
		return o;
	}

	@Override
	public Object putIfAbsent(String key, Object value) {
		Object o = super.putIfAbsent(key, value);
		if(o == null)
			observators.parallelStream().forEach((observer) -> observer.onNewKeyPut(this, key, o, value));
		return o;
	}

	@Override
	public boolean remove(Object key, Object value) {
		boolean removed = super.remove(key, value);
		if(removed)
			observators.parallelStream().forEach((observer) -> observer.onKeyRemoved(this, (String)key, value));
		return removed;
	}

	@Override
	public boolean replace(String key, Object oldValue, Object newValue) {
		boolean replaced = super.replace(key, oldValue, newValue);
		if(replaced)
			observators.parallelStream().forEach((observer) -> observer.onKeyReplaced(this, key, oldValue, newValue));
		return replaced;
	}

	@Override
	public Object replace(String key, Object value) {
		Object obj = super.replace(key, value);
		observators.parallelStream().forEach((observer) -> observer.onKeyReplaced(this, key, obj, value));
		return obj;
	}
	
	/**
	 * Zwraca listę par postaci (nazwa systemowa klasy, liczba poziomów).
	 * Kolejność elementów na liście odpowiada kolejności zdobywania doświadczenia
	 * w posiadanych klasach (co może być istotne np. w przypadku wieloklasowca z
	 * poziomem w klasie Mnich).
	 * @return lista obiektów ClassInfo.
	 */
	@SuppressWarnings("unchecked")
	public List<ClassInfo> getClassInfos() {
		return (List<ClassInfo>)get(StringConstants.KEY_CLASS);
	}
	
	/**
	 * Zwraca mapę atutów w postaci klucz-wartość, gdzie klucz jest nazwą systemową
	 * atutu, a wartością dane wykorzystywane przez ten atut.
	 * @return mapa atutów
	 */
	@SuppressWarnings("unchecked")
	public Map<String, Object> getFeatsMap() {
		return (Map<String, Object>)get(StringConstants.KEY_FEATS);
	}
	
	/**
	 * Zwraca mapę klucz-wartość umiejętności postaci, gdzie klucz jest nazwą
	 * systemową umiejętności, a wartością jest liczba rang w tej umiejętności
	 * bez uwzględnienia premii z modyfikatora atrybutu kluczowego.
	 * @return mapa umiejętności.
	 */
	@SuppressWarnings("unchecked")
	public Map<String, Integer> getSkillsMap() {
		return (Map<String, Integer>)get(StringConstants.KEY_SKILLS);
	}

	/**
	 * Serial version UID
	 */
	private static final long serialVersionUID = -5491615991801707374L;
	
	private List<CreatureModelObservator> observators;

}

