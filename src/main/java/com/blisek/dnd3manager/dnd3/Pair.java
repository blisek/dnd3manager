package com.blisek.dnd3manager.dnd3;

import java.util.Comparator;

public class Pair<E, F> {
	public E first;
	public F second;
	
	public Pair(E first, F second) {
		super();
		this.first = first;
		this.second = second;
	}
	
	public static class FirstComparator<E extends Comparable<E>> implements Comparator<Pair<E,?>> {

		@Override
		public int compare(Pair<E, ?> arg0, Pair<E, ?> arg1) {
			return arg0.first.compareTo(arg1.first);
		}
		
	}
	
	public static class SecondComparator<F extends Comparable<F>> implements Comparator<Pair<?,F>> {
		private boolean reversed;
		
		public SecondComparator() {
			reversed = false;
		}
		
		public SecondComparator(boolean reversed) {
			this.reversed = reversed;
		}
		
		@Override
		public int compare(Pair<?, F> o1, Pair<?, F> o2) {
			return reversed ? -o1.second.compareTo(o2.second)
					: o1.second.compareTo(o2.second);
		}
		
	}
}
