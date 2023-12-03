package deque;

import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T> {
    private Comparator<T> thisComparator;

    public MaxArrayDeque(Comparator<T> c) {
        super();
        thisComparator = c;
    }

    public T max() {
        if (isEmpty()) {
            return null;
        }
        int maxIndex = 0;
        for (int i = 0; i < size(); i++) {
            if (thisComparator.compare(super.get(i), super.get(maxIndex)) > 0) {
                maxIndex = i;
            }
        }
        return super.get(maxIndex);
    }

    public T max(Comparator<T> c) {
        thisComparator = c;
        return max();
    }
}
