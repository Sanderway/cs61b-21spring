package deque;

import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T> {
    ArrayDeque<T> myDeque;
    private Comparator<T> thisComparator;
    public MaxArrayDeque(Comparator<T> c){
        myDeque = new ArrayDeque<T>();
        thisComparator = c;
    }

    public T max(){
        if (isEmpty()){
            return null;
        }
        int maxIndex = 0;
        for (int i = 0;i < size();i++){
            if (thisComparator.compare(myDeque.get(i), myDeque.get(maxIndex)) > 0){
                maxIndex = i;
            }
        }
        return myDeque.get(maxIndex);
    }

    public T max(Comparator<T> c){
        thisComparator = c;
        return max();
    }
}
