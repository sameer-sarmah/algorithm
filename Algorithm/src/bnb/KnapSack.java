package bnb;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class KnapSack {
    final int MAX_WEIGHT = 10;

    public static void main(String[] args) {
    new KnapSack().findMaxValue();
    }

    public void findMaxValue() {
        double arr[][] = { { 2, 40 }, { 3.14, 50 }, { 1.98, 100 }, { 5, 95 }, { 3, 30 } };
        List<ValueAndWeight> list = createList(arr);
        list.sort(new ValueComparator());
        calculateBound(list);
        
    }

    private int calculateBound(List<ValueAndWeight> list) {
        int val = 0;
        int weight = 0;
        for (ValueAndWeight v : list) {
            if (weight <= MAX_WEIGHT) {
                val += v.value;
                weight += v.weight;
            }

        }
        return val;
    }

    private List<ValueAndWeight> createList(double arr[][]) {
        List<ValueAndWeight> list = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            double objArr[] = arr[i];
            ValueAndWeight obj = new ValueAndWeight();
            obj.weight = objArr[0];
            obj.value = objArr[1];
            list.add(obj);
        }
        return list;
    }

    class ValueAndWeight {
        double value;
        double weight;

        public double getValue() {
            return value;
        }

        public void setValue(double value) {
            this.value = value;
        }

        public double getWeight() {
            return weight;
        }

        public void setWeight(double weight) {
            this.weight = weight;
        }

    }

    class ValueComparator implements Comparator<ValueAndWeight> {

        @Override
        public int compare(ValueAndWeight o1, ValueAndWeight o2) {
            if ((o1.value / o1.weight) > (o2.value / o2.weight))
                return 1;
            else if ((o1.value / o1.weight) < (o2.value / o2.weight))
                return -1;
            else
                return 0;

        }

    }
}
