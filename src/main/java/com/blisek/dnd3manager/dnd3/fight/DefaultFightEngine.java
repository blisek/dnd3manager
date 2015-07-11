package com.blisek.dnd3manager.dnd3.fight;

import java.awt.geom.Point2D;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.Queue;
import java.util.stream.Collectors;

import com.blisek.dnd3manager.dnd3.CreatureController;
import com.blisek.dnd3manager.dnd3.Pair;

public class DefaultFightEngine implements IFightSimulator, IFightContext {
	private static final String STATE_EXCEPTION_MSG = "Fight not started";
	private ArrayList<Pair<CreatureController, Integer>> participants;
	private FightCallbacksSet callbacks;
//	private ArrayList<ParticipantInfo> participantsInfos;
	private static int instancesCounter = 0;
	private final String fightId;
	private boolean started;
	private int roundNum = -1;
	private ArrayDeque<Pair<CreatureController, Integer>> roundOrder;
	private Pair<CreatureController, Integer> currentParticipant;
	
	public DefaultFightEngine(FightCallbacksSet fgs) {
		if(fgs == null)
			throw new NullPointerException();
		participants = new ArrayList<>();
		roundOrder = new ArrayDeque<>();
		
		fightId = "fight" + instancesCounter++;
	}
	
	public boolean addParticipant(CreatureController controller) {
		if(controller == null)
			throw new NullPointerException();
		
		return participants.add(new Pair<CreatureController, Integer>(controller, 0));
	}
	
	@Override
	public String getFightIndentifier() {
		return fightId;
	}

	@Override
	public Collection<CreatureController> getParticipants() {
		return participants.stream().map(p -> p.first).collect(Collectors.toList());
	}

	@Override
	public int getCurrentRound() {
		checkStarted();
		return roundNum;
	}

	@Override
	public Collection<Pair<CreatureController, Point2D>> getParticipantsCoordinates(Optional<CreatureController> cre) {
		checkStarted();
		return null;
	}

	@Override
	public void moveCurrentParticipantToEndOfQueue() {

	}

	@Override
	public CreatureController getCurrentParticipant() {
		return currentParticipant.first;
	}

	@Override
	public Collection<FightAction> getAvailableActionsForCurrentParticipant() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void nextParticipant() {
		// TODO Auto-generated method stub

	}

	@Override
	public void nextRound() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean start(Map<String, Object> extraParams) {
		if(started) return false;
		
		// wyznaczenie inicjatywy dla uczestnikow
		participants.forEach(p -> p.second = callbacks.getInitiativeFor(p.first));
		participants.sort(new Pair.SecondComparator<>(true));
		
		roundOrder.addAll(participants);
		
		roundNum = 1;
		
		started = true;
	}
	
	private void checkStarted() {
		if(!started)
			throw new IllegalStateException(STATE_EXCEPTION_MSG);
	}
	
}
