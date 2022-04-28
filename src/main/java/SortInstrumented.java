public abstract class SortInstrumented extends Sort {
    protected int numberOfCalling;
    /**
     * constructor of SortInstrumented
     * @param array: the array to be sorted
     */
    public SortInstrumented(int[] array) {
        super(array);
    }
}
