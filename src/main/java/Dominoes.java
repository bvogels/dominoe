import java.util.ArrayList;
import java.util.List;

public class Dominoes {

    public List<Domino> formChain(List<Domino> dominoesList) throws ChainNotFoundException{
        if (dominoesList.size() == 1 && dominoesList.get(0).getRight() != dominoesList.get(0).getLeft()) {
            throw new ChainNotFoundException("No domino chain found.");
        }
        if (dominoesList.size() > 1) {
            List<Integer> used = new ArrayList<>();
            List<Domino> chain = new ArrayList<>();
            chain.add(dominoesList.get(0));
            used.add(0);

            dominoesList = chainDominoes(dominoesList, chain, used);



        }

        return dominoesList;

    }

    private List<Domino> chainDominoes(List<Domino> dominoes, List<Domino> chain, List<Integer> used) throws ChainNotFoundException {
        if (dominoes.size() == chain.size()) {
            return chain;
        }
        int chainElements = chain.size();
        for (Domino stone : dominoes) {
            if (used.contains(dominoes.indexOf(stone))) {
                continue;
            }
            if (stone.getLeft() == chain.get(chain.size() - 1).getRight()) {
                chain.add(stone);
                used.add(dominoes.indexOf(stone));
                chainDominoes(dominoes, chain, used);
            }
            else if (stone.getRight() == chain.get(chain.size() - 1).getRight()) {
                chain.add(new Domino(stone.getRight(), stone.getLeft()));
                used.add(dominoes.indexOf(stone));
                chainDominoes(dominoes, chain, used);
            }
        }
        if (chainElements == chain.size()) {
            chain.remove(chain.size()-1);
        }
        return chainDominoes(dominoes, chain, used);
    }
}

/*

Since this exercise has a difficulty of > 4 it doesn't come
with any starter implementation.
This is so that you get to practice creating classes and methods
which is an important part of programming in Java.

Please remove this comment when submitting your solution.

*/
