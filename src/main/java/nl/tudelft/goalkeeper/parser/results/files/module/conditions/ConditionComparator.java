package nl.tudelft.goalkeeper.parser.results.files.module.conditions;

import java.util.Comparator;

public class ConditionComparator implements Comparator<Condition> {


    /**
     * Method to compare if two objects are able to be swapped around when sorting the list.
     * @param o1 The first object in the list.
     * @param o2 The second object in the list.
     * @return Returns the difference between the items. If equal to 0, they are equal and thus do
     * not need to turn around, if greater than 1 they are in the correct order and thus cannot
     * be turned around and if less than 1 they are
     * in the wrong order and thus need to be turned around.
     */
    @Override
    public int compare(Condition o1, Condition o2) {
        //TODO: Peroform better checks if the condition can be turned around if they access the
        // same variables.
        return o1.hashCode() - o2.hashCode();

    }
}
