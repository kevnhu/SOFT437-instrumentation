import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Stack;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * A Singleton class for Instrumentation
 */
public class Instrumentation {
    private long firstStartTime;
    private final Stack<Long> startTime;
    private final Stack<String> indent;
    private final ArrayList<String> log;
    private static boolean activated = false;
    private static final Instrumentation ins = new Instrumentation();
    /**
     * constructor of Instrumentation
     */
    private Instrumentation() {
        firstStartTime = 0;
        startTime = new Stack<>();
        indent = new Stack<>();
        log = new ArrayList<>();
    }

    /**
     * Singleton getter
     * @return: the instance of Instrumentation object
     */
    public static Instrumentation instance() {
        return ins;
    }

    public void startTiming(String comment) {
        if(activated) {
            startTime.push(System.nanoTime());
            String tempIndent = IntStream
                    .range(1, startTime.size())
                    .mapToObj(i -> "| ")
                    .collect(Collectors.joining());
            indent.push(tempIndent);
            log.add(tempIndent + "STARTTIMING: " + comment + "\n");
            if (startTime.size() == 1) {
                firstStartTime = startTime.firstElement();
            }
        }
    }

    public void stopTiming(String comment) {
        if(activated) {
            String time = String.format("%.5f", ((double)(System.nanoTime() - startTime.pop()) / 1000000.0));
            log.add(indent.pop() + "STOPTIMING: " + comment + " " + time + " ms\n");
        }
    }

    public void comment(String comment) {
        if(activated) {
            log.add("COMMENT: " + comment + "\n");
        }
    }
    /**
     * @param filename: the target directory to save the log
     */
    public void dump(String filename) {
        if(activated) {
            String time = String.format("%.5f", ((double)(System.nanoTime() - firstStartTime) / 1000000));
            log.add("TOTAL TIME: " + time + " ms\n");
            Logger logger = Logger.getLogger("InstrumentationLog");
            FileHandler fileHandler;
            try {
                if (filename == null) {
                    SimpleDateFormat f = new SimpleDateFormat("yyMMddHHmmss");
                    filename = f.format(new Date()) + ".log";
                }
                fileHandler = new FileHandler(filename);
                logger.addHandler(fileHandler);
                fileHandler.setFormatter(new SimpleFormatter());
                fileHandler.setLevel(Level.INFO);
                StringBuilder sb = new StringBuilder("\n");
                for (String s : log) {
                    sb.append(s);
                }
                logger.info(sb.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    /**
     * Dump method saving file as instrumentation.log
     */
    public void dump() {
        dump("instrumentation.log");
    }

    public void activate(boolean onoff) {
        activated = onoff;
    }
}
