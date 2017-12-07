package nl.tudelft.goalanalyzer.util.console;

/**
 * Created by Cedric Willekens (4530373) on 12/7/2017.
 */
public class ProgressBar {

    private static int stringcapacity = 60;
    private static int percentagemultiplier = 100;

    private int taskCount;
    private int taskDone;

    private StringBuilder progress;

    /**
     * A method to create a progressbar instance.
     * @param taskCount The amount of tasks to be done.
     */
    public ProgressBar(int taskCount) {
        this.taskCount = taskCount;
        this.progress = new StringBuilder(stringcapacity);
        this.taskDone = 0;
    }

    /**
     * Update the progress bar.
     * This should be called after every task is finished.
     */
    public void update() {
        char[] formatChar = {'|', '/', '-', '\\'};
        String format = "\r%3d%% %s %c";

        int percentage = (++this.taskDone * percentagemultiplier) / taskCount;
        int extraChars = (percentage / 2) - this.progress.length();

        while (extraChars-- > 0) {
            progress.append("#");
        }

        System.out.printf(format, percentage, progress, formatChar[taskDone % formatChar.length]);

        if (taskDone == taskCount) {
            System.out.flush();
            System.out.println();
            this.progress = new StringBuilder(stringcapacity);
        }

    }

}
