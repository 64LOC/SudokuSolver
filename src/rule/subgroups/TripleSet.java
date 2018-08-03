package rule.subgroups;

import java.util.ArrayList;

public class TripleSet {

    private final ArrayList<Triple> triples;

    public TripleSet() {
        triples = new ArrayList<>();
    }

    public void add(Triple triple) {
        triples.add(triple);
    }

    public int findEqualTriplesAndRefine() {
        int solvingSteps = 0;

        for (int i = 0; i < triples.size() - 2; i++) {
            Triple currentTriple = triples.get(i);
            for (int j = i + 1; j < triples.size() - 1; j++) {
                Triple firstComparedTriple = triples.get(j);
                if (currentTriple.hasSameCells(firstComparedTriple))
                    for (int k = j + 1; k < triples.size(); k++) {
                        Triple secondComparedTriple = triples.get(k);
                        if (currentTriple.hasSameCells(secondComparedTriple))
                            solvingSteps += currentTriple.removeCandidatesExceptTriple(firstComparedTriple, secondComparedTriple);
                    }
            }

        }

        return solvingSteps;
    }

}
