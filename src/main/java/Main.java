public class Main {
    /**
     * Instrumented method that populates an array base on given size
     * @param size: the size of an array
     * @return: a randomly generated array
     */
    public static int[] populateArray(int size) {
        Instrumentation.instance().startTiming("populate array");
        int[] arr = new int[size];
        for (int i=0; i<size; i++) {
            arr[i] = (int) (Math.random() * 99999 + 1);
        }
        Instrumentation.instance().stopTiming("populate array");
        return arr;
    }
    /**
     * Instrumented method that runs the given sorting algorithm
     * @param sort: the size of an array
     * @param comment: the comment of the sort algorithm
     */
    public static void instrumentSort(Sort sort, String comment) {
        Instrumentation ins = Instrumentation.instance();
        ins.startTiming(comment);
        sort.handle();
        ins.stopTiming(comment);
    }

    public static void overheadLoop(int number) {
        Instrumentation ins = Instrumentation.instance();
        ins.activate(true);
        for (int i = 0; i < number; i++) {
            ins.startTiming("overhead loop");
            ins.stopTiming("overhead loop");
        }
        ins.comment("overhead finished");
    }

    public static void main(String[] args) {
        int[] size = { 5, 10, 50 ,100, 500, 1000, 5000, 10000, 50000, 100000 };
        int[] loopNumber = { 7, 13, 67, 131, 661, 133, 6605, 13331, 66777, 135377 };
        Instrumentation ins = Instrumentation.instance();
        ins.activate(true);
        ins.startTiming("main");
        for (int s : size) {
            int[] commonArray = Main.populateArray(s);
            Main.instrumentSort(new BubbleSort(commonArray.clone()), "bubble sort");
            Main.instrumentSort(new QuickSort(commonArray.clone()), "quick sort");
            new BubbleSortInstrumented(commonArray.clone()).handle();
            new QuickSortInstrumented(commonArray.clone()).handle();
        }
        for (int n : loopNumber) {
            ins.startTiming("overhead");
            overheadLoop(n);
            ins.stopTiming("overhead");
        }
        ins.stopTiming("main");
        ins.dump();
        ins.activate(false);
    }
}
