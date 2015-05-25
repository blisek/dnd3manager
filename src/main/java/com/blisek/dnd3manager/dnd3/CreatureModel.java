package com.blisek.dnd3manager.dnd3;

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
	 * Serial version UID
	 */
	private static final long serialVersionUID = -5491615991801707374L;
	
	private List<CreatureModelObservator> observators;

}
