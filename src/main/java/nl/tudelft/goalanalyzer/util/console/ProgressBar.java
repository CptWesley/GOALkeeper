package nl.tudelft.goalanalyzer.util.console;

import lombok.Getter;

/**
 * Created by Cedric Willekens (4530373) on 12/7/2017.
 */
public final class ProgressBar {

    private static ProgressBar instance;

    private static final int STRINGCAPACITY = 60;
    private static final int PERCENTAGEMULTIPLIER = 100;

    @Getter private int taskCount;
    private int taskDone;

    private StringBuilder progress;


    /**
     * Gets the progressbar currently present.
     * This may be NULL.
     * @return An intance of progressbar.
     */
    public static ProgressBar getINSTANCE() {
        return instance;
    }

    /**
     * Checks if there is an instance currently present with that specified task count and returns
     * it if it present and else it creates it and returns it afterwards.
     * @param taskCount The amount of tasks to be done.
     * @return The progressbar instance with @code{taskcount} tasks to be finished.
     */
    public static ProgressBar getINSTANCE(int taskCount) {
        if (instance != null && instance.getTaskCount() == taskCount) {
            return instance;
        }
        instance = new ProgressBar(taskCount);
        return instance;
    }

    /**
     * A method to create a progressbar instance.
     * @param taskCount The amount of tasks to be done.
     */
    private ProgressBar(int taskCount) {
        this.taskCount = taskCount;
        this.progress = new StringBuilder(STRINGCAPACITY);
        this.taskDone = 0;
    }

    /**
     * Update the progress bar.
     * This should be called after every task is finished.
     */
    public void update() {
        char[] formatChar = {'|', '/', '-', '\\'};
        String format = "\r%3d%% %s %c";

        this.taskDone++;
        int percentage = (this.taskDone * PERCENTAGEMULTIPLIER) / taskCount;
        int extraChars = (percentage / 2) - this.progress.length();

        while (extraChars-- > 0) {
            progress.append("#");
        }

        System.out.printf(format, percentage, progress, formatChar[taskDone % formatChar.length]);

        if (percentage == 100) {
            System.out.flush();
            //System.out.println();
            this.progress = new StringBuilder(STRINGCAPACITY);
            this.taskDone = 0;
        }

    }

}
