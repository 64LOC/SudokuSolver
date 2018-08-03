package playfield;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Cell {

    private Integer number;
    private final List<Integer> candidates;

    public Cell() {
        number = null;
        candidates = new LinkedList<>();
        addAllCandidates();
    }

    private void addAllCandidates() {
        for (int i = 1; i < 10; i++)
            candidates.add(i);
    }

    public int removeCandidate(Integer removedCandidate) {
        return candidates.remove(removedCandidate) ? 1 : 0;
    }

    public int removeCandidates(LinkedList<Integer> removedCandidates) {
        int removals = 0;

        for (Integer removedCandidate : removedCandidates)
            if (candidates.contains(removedCandidate)) {
                candidates.remove(removedCandidate);
                removals++;
            }

        return removals;
    }

    public boolean isAssigned() {
        return number != null;
    }

    public void setAssigned() {
        this.number = candidates.get(0);
        candidates.clear();
    }

    public void setAssigned(Integer number) {
        this.number = number;
        candidates.clear();
    }

    public Integer getNumber() {
        return number;
    }

    @Override
    public String toString() {

        String candidatesString = "";
        for (int i = 1; i <= 9; i++)
            if (candidates.contains(i))
                candidatesString += i;
            else
                candidatesString += "x";

        return (number == null ? "_" : number.toString()) + "[" + candidatesString + "]";
    }

    public int getNumberOfCandidates() {
        return candidates.size();
    }

    public void addCandidatesToHistogramOfNumbers(HashMap<Integer, Integer> histogramOfNumbers) {
        for (Integer candidate : candidates)
            histogramOfNumbers.put(candidate, histogramOfNumbers.get(candidate) + 1);
    }

    public boolean hasCandidate(Integer key) {
        return candidates.contains(key);
    }

    public void leaveJustOneCandidate(Integer key) {
        candidates.clear();
        candidates.add(key);
    }

    public Collection<? extends Integer> getCandidates() {
        return candidates;
    }

    public int removeCandidatesExcept(Integer... candidatesToRemain) {
        LinkedList<Integer> candidatesToRemove = new LinkedList<>(candidates);
        candidatesToRemove.removeAll(Arrays.asList(candidatesToRemain));
        return removeCandidates(candidatesToRemove);
    }
}
