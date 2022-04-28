public class BubbleSortInstrumented extends SortInstrumented {
    /**
     * constructor of BubbleSortInstrumented
     * @param array: the array to be sorted
     */
    public BubbleSortInstrumented(int[] array) {
        super(array);
    }
    /**
     * Handle the instrumented bubble sort call
     */
    @Override
    public void handle() {
        Instrumentation ins = Instrumentation.instance();
        ins.activate(true);
        ins.startTiming("bubble sort");
        numberOfCalling = 0;
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
        ins.stopTiming("bubble sort outer");
        numberOfCalling++;
        ins.comment("Instrumentation number of call in bubble sort:" + numberOfCalling);
    }
}
