package com.blisek.dnd3manager.dnd3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ObservableMap<K, V> extends HashMap<K, V> {
	private List<MapObservator> observers;
	private boolean parallel;
	
	public ObservableMap(boolean parallel) {
		this.parallel = parallel;
	}
	
	public boolean addObservator(MapObservator mo) {
		if(mo == null)
			throw new NullPointerException();
		if(observers == null)
			observers = new ArrayList<MapObservator>();
		return observers.add(mo);
	}
	
	public boolean removeObservator(MapObservator mo) {
		if(mo == null)
			throw new NullPointerException();
		return observers.remove(mo);
	}

	@Override
	public V put(K key, V value) {
		V obj = super.put(key, value);
		notifyObserversPut(key, obj, value);
		return obj;
	}
	
	public V putWithoutNotify(K key, V value) {
		return super.put(key, value);
	}

	@Override
	public V remove(Object key) {
		V obj = super.remove(key);
		notifyObserversRemove(key, obj);
		return obj;
	}

	@Override
	public V putIfAbsent(K key, V value) {
		V obj = super.putIfAbsent(key, value);
		if(obj == null)
			notifyObserversPut(key, obj, value);
		return obj;
	}

	@Override
	public V replace(K key, V value) {
		V obj = super.replace(key, value);
		notifyObserversReplaced(key, obj, value);
		return obj;
	}

	@Override
	public boolean remove(Object key, Object value) {
		boolean result = super.remove(key, value);
		if(result)
			notifyObserversRemove(key, value);
		return result;
	}

	private void notifyObserversPut(final Object key, final Object oVal, final Object nVal) {
		if(observers == null)
			return;
		if(parallel)
			observers.parallelStream().forEach(observer -> observer.onNewKeyPut(this, key, oVal, nVal));
		else
			observers.stream().forEach(observer -> observer.onNewKeyPut(this, key, oVal, nVal));
	}
	
	private void notifyObserversRemove(final Object key, final Object oldValue) {
		if(observers == null)
			return;
		if(parallel)
			observers.parallelStream().forEach(observer -> observer.onKeyRemoved(this, key, oldValue));
		else
			observers.stream().forEach(observer -> observer.onKeyRemoved(this, key, oldValue));
	}
	
	private void notifyObserversReplaced(final Object key, final Object oldValue, final Object newValue) {
		if(observers == null)
			return;
		if(parallel)
			observers.parallelStream().forEach(observer -> observer.onKeyReplaced(this, key, oldValue, newValue));
		else
			observers.stream().forEach(observer -> observer.onKeyReplaced(this, key, oldValue, newValue));
	}
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -2910505313812442254L;

}
