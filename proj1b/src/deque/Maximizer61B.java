package deque;
import java.util.Comparator;
import java.util.Iterator;

public class Maximizer61B {
    /**
     * Returns the maximum element from the given iterable of comparables.
     * You may assume that the iterable contains no nulls.
     *
     * @param iterable  the Iterable of T
     * @return          the maximum element
     */
    public static<T extends Comparable<T>> T max(Iterable<T> iterable) {
        T maxItem = null;
        Iterator<T> seer = iterable.iterator();
        if (seer.hasNext()) {
            maxItem = seer.next();
        }
        for (T item : iterable) {
            int cmp = item.compareTo(maxItem);
            if (cmp > 0) {
                maxItem = item;
            }
        }
        return maxItem;
    }


    /**
     * Returns the maximum element from the given iterable according to the specified comparator.
     * You may assume that the iterable contains no nulls.
     *
     * @param iterable  the Iterable of T
     * @param comp      the Comparator to compare elements
     * @return          the maximum element according to the comparator
     */
    public static<T> T max(Iterable<T> iterable, Comparator<T> comp) {
        T maxItem = null;
        Iterator<T> seer = iterable.iterator();
        if (seer.hasNext()) {
            maxItem = seer.next();
        }
        for (T item : iterable) {
            int cmp = comp.compare(item, maxItem);
            if (cmp > 0) {
                maxItem = item;
            }
        }
        return maxItem;
    }
}
