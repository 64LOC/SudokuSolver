package rule.subgroups;

import java.util.ArrayList;

public class PairSet {

    private final ArrayList<Pair> pairs;

    public PairSet() {
        pairs = new ArrayList<>();
    }

    public void add(Pair pair) {
        pairs.add(pair);
    }

    public int findEqualPairsAndRefine() {
        int solvingSteps = 0;

        for (int i = 0; i < pairs.size() - 1; i++) {
            Pair currentPair = pairs.get(i);
            for (int j = i + 1; j < pairs.size(); j++) {
                Pair comparedPair = pairs.get(j);
                if (currentPair.hasSameCells(comparedPair))
                    solvingSteps += currentPair.removeCandidatesExceptPair(comparedPair);
            }

        }

        return solvingSteps;
    }

}
