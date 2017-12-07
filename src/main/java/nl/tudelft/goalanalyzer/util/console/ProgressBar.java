package nl.tudelft.goalanalyzer.util.console;

/**
 * Created by Cedric Willekens (4530373) on 12/7/2017.
 */
public class ProgressBar {

    private int taskCount;
    private int taskDone;

    private StringBuilder progress;

    public ProgressBar(int taskCount) {
        this.taskCount = taskCount;
        this.progress = new StringBuilder(60);
        this.taskDone = 0;
    }

    public void update() {
        char[] formatChar = {'|', '/', '-', '\\'};
        String format = "\r%3d%% %s %c";

        int percentage = (++this.taskDone * 100) / taskCount;
        int extraChars = (percentage / 2) - this.progress.length();

        while (extraChars-- > 0) {
            progress.append("#");
        }

        System.out.printf(format, percentage, progress, formatChar[taskDone % formatChar.length]);

        if (taskDone == taskCount) {
            System.out.flush();
            System.out.println();
            this.progress = new StringBuilder(60);
        }

    }

}
