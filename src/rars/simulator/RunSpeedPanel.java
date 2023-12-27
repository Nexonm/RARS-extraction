package rars.simulator;

import rars.Globals;

/**
 * Class for the Run speed slider control.  One is created and can be obtained using
 * getInstance().
 *
 * @author Pete Sanderson
 * @version August 2005
 */

public class RunSpeedPanel{
    /**
     * Constant that represents unlimited run speed.  Compare with return value of
     * getRunSpeed() to determine if set to unlimited.  At the unlimited setting, the GUI
     * will not attempt to update register and memory contents as each instruction
     * is executed.  This is the only possible value for command-line use of Mars.
     */
    public final static double UNLIMITED_SPEED = 40;

    private final static int SPEED_INDEX_MIN = 0;
    private final static int SPEED_INDEX_MAX = 40;
    private final static int SPEED_INDEX_INIT = 40;
    private static final int SPEED_INDEX_INTERACTION_LIMIT = 35;
    private double[] speedTable = {
            .05, .1, .2, .3, .4, .5, 1, 2, 3, 4, 5,      // 0-10
            6, 7, 8, 9, 10, 11, 12, 13, 14, 15,      // 11-20
            16, 17, 18, 19, 20, 21, 22, 23, 24, 25,      // 21-30
            26, 27, 28, 29, 30, UNLIMITED_SPEED, UNLIMITED_SPEED, // 31-37
            UNLIMITED_SPEED, UNLIMITED_SPEED, UNLIMITED_SPEED // 38-40
    };
    private static RunSpeedPanel runSpeedPanel = null;
    private volatile int runSpeedIndex = SPEED_INDEX_MAX;

    /**
     * Retrieve the run speed panel object
     *
     * @return the run speed panel
     */

    public static RunSpeedPanel getInstance() {
        if (runSpeedPanel == null) {
            runSpeedPanel = new RunSpeedPanel();
            Globals.runSpeedPanelExists = true; // DPS 24 July 2008 (needed for standalone tools)
        }
        return runSpeedPanel;
    }

    /*
     * private constructor (this is a singleton class)
     */
    private RunSpeedPanel() {
        // this constructor became blank after removing all swing and awt elements
    }

    /**
     * returns current run speed setting, in instructions/second.  Unlimited speed
     * setting is equal to RunSpeedPanel.UNLIMITED_SPEED
     *
     * @return run speed setting in instructions/second.
     */

    public double getRunSpeed() {
        return speedTable[runSpeedIndex];
    }

    /*
     * set label wording depending on current speed setting
     */
    private String setLabel(int index) {
        String result = "Run speed ";
        if (index <= SPEED_INDEX_INTERACTION_LIMIT) {
            if (speedTable[index] < 1) {
                result += speedTable[index];
            } else {
                result += ((int) speedTable[index]);
            }
            result += " inst/sec";
        } else {
            result += ("at max (no interaction)");
        }
        return result;
    }
}
