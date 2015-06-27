package com.blisek.dnd3manager.dnd3;

public final class CreatureHelper {

	/**
	 * Sumuje wszystkie wartości przypisane do kluczy modelu postaci, które zaczynają się od prefix.
	 * @param prefix prefiks nazw kluczy.
	 * @return suma wszystkich wartości spełniających warunek.
	 */
	public static int calculateMod(CreatureModel model, String prefix) {
		int sum = model.entrySet().parallelStream()
				.filter((entry) -> entry.getKey().startsWith(prefix))
				.mapToInt((entry) -> { Object obj = entry.getValue(); return obj == null ? 0 : (int)obj; })
				.sum();	
		return sum < 10 ? (sum - 11) / 2 : (sum - 10) / 2;
	}
	
	
	public static int sumParamsBeginWith(CreatureModel model, String prefix) {
		return model.entrySet().parallelStream()
				.filter(entry -> entry.getKey().startsWith(prefix))
				.mapToInt(entry -> { Object obj = entry.getValue(); return obj == null ? 0 : (int)obj; })
				.sum();
	}

	public static double sumDoubleParamsBeginWith(CreatureModel model, String prefix) {
		return model.entrySet().parallelStream()
				.filter(entry -> entry.getKey().startsWith(prefix))
				.mapToDouble(entry -> { Object obj = entry.getValue(); return obj == null ? 0 : (float)obj; })
				.sum();
	}
}
