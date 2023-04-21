package task2;

public class SelectionSortAlgorithm {
    public static void selectionSort(Integer[] sortArr) {

        if (sortArr == null){
            return;
        }

        for (int i = 0; i < sortArr.length; i++) {
            swapElem(i, sortArr);
        }

    }

    public static void swapElem(int i, Integer[] sortArr){
        int pos = i;
        int min = sortArr[i];
        //цикл выбора наименьшего элемента
        for (int j = i + 1; j < sortArr.length; j++) {
            if (sortArr[j] < min) {
                //pos - индекс наименьшего элемента
                pos = j;
                min = sortArr[j];
            }
        }
        sortArr[pos] = sortArr[i];
        //меняем местами наименьший с sortArr[i]
        sortArr[i] = min;
    }


}
