public abstract class Sort {
    protected int[] array;

    public abstract void handle();

    public int[] getArray() {
            return array;
    }
    /**
     * constructor of Sort
     * @param array: the array to be sorted
     */
    public Sort(int[] array) {
        this.array = array;
    }

    public void setArray(int[] array) {
        this.array = array;
    }
}
