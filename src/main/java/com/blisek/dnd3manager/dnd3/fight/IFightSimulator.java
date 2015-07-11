package com.blisek.dnd3manager.dnd3.fight;

import java.awt.geom.Point2D;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;

import com.blisek.dnd3manager.dnd3.CreatureController;
import com.blisek.dnd3manager.dnd3.Pair;

public interface IFightSimulator {
	
	/**
	 * Zwraca nazwe walki. Nazwa ta powinna byc unikatowa, co najmniej
	 * inna dla roznych zestawow postaci (uwzgledniajac inicjatywe np.
	 * dla zestawu Postac A, Postac B powinna zostac wygenerowana inna
	 * nazwa niz dla zestawu Postac B, Postac A).
	 * @return lancuch identyfikujacy te walke.
	 */
	String getFightIndentifier();
	
	/**
	 * Zwraca liste wszystkich uczestnikow posortowanych
	 * malejaco wedlug testu na inicjatywe (osoby z najwyzszym
	 * wynikiem beda na poczatku listy).
	 * @return lista uczestnikow posortowana wedlug inicjatywy.
	 */
	Collection<CreatureController> getParticipants();
	
	/**
	 * Zwraca numer obecnej rundy liczony od 1.
	 * @return numer rundy.
	 */
	int getCurrentRound();
	
	/**
	 * Zwraca wspolrzedne wszystkich postaci wzgledem innej postaci. Jesli 
	 * !Optional.isPresent wspolrzedne sa wyznaczane wzgledem srodka planszy
	 * lub innego punktu (zalezy od implementacji).
	 * @return kolekcja par, gdzie pierwszy element to kontroler postaci, a drugi
	 * to jego polozenie.
	 */
	Collection<Pair<CreatureController, Point2D>> getParticipantsCoordinates(Optional<CreatureController> cre);
	
	/**
	 * Przesuwa obecnego uczestnika (ktorego jest kolej) na koniec kolejki inicjatywy.
	 */
	void moveCurrentParticipantToEndOfQueue();
	
	/**
	 * Zwraca kontroler obecnego uczestnika (na ktorego przypadla kolej).
	 * @return kontroler postaci ktora ma prawo ruchu, badz null jesli
	 * nie ma nastepnego uczestnika.
	 */
	CreatureController getCurrentParticipant();
	
	/**
	 * Zwraca kolekcje dostepnych akcji dla obecnej postaci.
	 * @return kolekcja dostepnych akcji.
	 */
	Collection<FightAction> getAvailableActionsForCurrentParticipant();
	
	/**
	 * Przechodzi do kolejnego uczestnika wg. kolejnosci inicjatywy.
	 */
	void nextParticipant();
	
	/**
	 * Przechodzi do nastepnej rundy. Jesli jakies postacie nie wykonaly
	 * swojego ruchu traca te mozliwosc.
	 */
	void nextRound();
	
	/**
	 * Rozpoczyna walke.
	 * @param extraParams dodatkowe parametry mogace miec wplyw na przebieg
	 * tej walki (np. okolicznosciowe premie etc.)
	 * @return true jesli walka zostala rozpoczeta, false jesli nie udalo sie
	 * to z jakiegos powodu.
	 */
	boolean start(Map<String, Object> extraParams);
	
	/**
	 * Informuje czy walka zostala rozpoczeta.
	 * 
	 * @return
	 */
	boolean isStarted();
}
