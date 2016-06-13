package Snake.Statistic;


import Snake.Entity.Notation;

import java.util.Random;


public class JavaSort {

    static Random rand = new Random();
    public void qSort(Notation[] array, int begin, int end) {
        int i = begin;
        int j = end;
        int x = array[begin + rand.nextInt(end - begin + 1)].getDirection();
        while (i <= j) {
            while (array[i].getDirection() > x) {
                i++;
            }
            while (array[j].getDirection() < x) {
                j--;
            }
            if (i <= j) {
                Notation temp = array[i];
                array[i] = array[j];
                array[j] = temp;
                i++;
                j--;
            }
        }
        if (begin < j) {
            qSort(array, begin, j);
        }
        if (i < end) {
            qSort(array, i, end);
        }
    }

}
