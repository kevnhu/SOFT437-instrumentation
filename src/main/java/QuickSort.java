public class QuickSort extends Sort {
    /**
     * constructor of QuickSort
     * @param array: the array to be sorted
     */
    public QuickSort(int[] array) {
        super(array);
    }
    /**
     * @param low: the starting index of pivot
     * @param high: the ending index of pivot
     * @return: new index of partition pivot
     */
    private int partition(int low, int high) {
        int pivot = array[high];
        int i = (low-1); // index of smaller element
        for (int j=low; j<high; j++) {
            // If current element is smaller than the pivot
            if (array[j] < pivot) {
                i++;
                // swap arr[i] and arr[j]
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }
        int temp = array[i+1];
        array[i+1] = array[high];
        array[high] = temp;
        return i+1;
    }
    /**
     * @param low: the starting index
     * @param high: the ending index
     */
    private void quickSort(int low, int high) {
        if (low < high) {
            int pi = partition(low, high);
            quickSort(low, pi - 1);
            quickSort(pi + 1, high);
        }
    }

    @Override
    public void handle() {
        quickSort(0, array.length - 1);
    }
}
