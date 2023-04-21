package task2;

import org.junit.Test;
import org.junit.Assert;

public class SelectionSortAlgorithmTest {

    @Test
    public void sort(){
        Integer[] testArray = new Integer[]{12, 61, 77, 94, 80, 58, 44, 1, 71, 88, 21, 6, 27, 28, 57};
        Integer[] sortArray = new Integer[]{1, 6, 12, 21, 27, 28, 44, 57, 58, 61, 71, 77, 80, 88, 94};

        for (int i = 0; i < testArray.length; i++) {
            SelectionSortAlgorithm.swapElem(i, testArray);
            Assert.assertEquals(testArray[i], sortArray[i]);
        }
    }

    @Test
    public void checkMaxMin(){
        Integer[] testArray = new Integer[]{12, Integer.MIN_VALUE, 0, 94, Integer.MAX_VALUE, Integer.MIN_VALUE, Integer.MAX_VALUE};
        Integer[] sortArray = new Integer[]{Integer.MIN_VALUE, Integer.MIN_VALUE, 0, 12, 94, Integer.MAX_VALUE, Integer.MAX_VALUE};

        for (int i = 0; i < testArray.length; i++) {
            SelectionSortAlgorithm.swapElem(i, testArray);
            Assert.assertEquals(testArray[i], sortArray[i]);
        }
        Assert.assertArrayEquals(testArray, sortArray);
    }

    @Test
    public void checkNull(){
        Integer[] testArray = null;
        SelectionSortAlgorithm.selectionSort(testArray);
        Assert.assertArrayEquals(testArray, null);
    }

    @Test
    public void checkRepetitions(){
        Integer[] testArray = new Integer[]{0, 1, 0, 0, 0, 4, 0, 0, 8};
        Integer[] sortArray = new Integer[]{0, 0, 0, 0, 0, 0, 1, 4, 8};
        for (int i = 0; i < testArray.length; i++) {
            SelectionSortAlgorithm.swapElem(i, testArray);
            Assert.assertEquals(testArray[i], sortArray[i]);
        }
    }

    @Test
    public void checkOneNumber(){
        Integer[] testArray = new Integer[]{-17};
        Integer[] sortArray = new Integer[]{-17};

        for (int i = 0; i < testArray.length; i++) {
            SelectionSortAlgorithm.swapElem(i, testArray);
            Assert.assertEquals(testArray[i], sortArray[i]);
        }
    }

    @Test
    public void checkNoNumber(){
        Integer[] testArray = new Integer[]{};
        Integer[] sortArray = new Integer[]{};

        for (int i = 0; i < testArray.length; i++) {
            SelectionSortAlgorithm.swapElem(i, testArray);
            Assert.assertEquals(testArray[i], sortArray[i]);
        }
    }

}
