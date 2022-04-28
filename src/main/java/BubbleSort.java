public class BubbleSort extends Sort {
    /**
     * constructor of BubbleSort
     * @param array: the array to be sorted
     */
    public BubbleSort(int[] array) {
        super(array);
    }
    /**
     * Handle the bubble sort call
     */
    @Override
    public void handle() {
        int n = array.length;
        for (int i = 0; i < n-1; i++) {
            for (int j = 0; j < n-i-1; j++) {
                if (array[j] > array[j+1]) {
                    int temp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = temp;
                }
            }
        }
    }
}
