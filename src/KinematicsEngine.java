// [IMPORTS]
import java.util.Scanner;

/** KINEMATICS ENGINE - holds and calculates all data. **/
public class KinematicsEngine {

    // [FIELDS]

    // static access for readability / usage
    static Scanner userInput = new Scanner(System.in);
    static final int TEXT = 0;
    static final int GUI = 1;

    // engine mode
    static final int GRAVITY_DROP = 0;
    static final int CAR_SIMULATOR = 1;
    int engineMode;

    // main functionality vars
    Object object;
    double displayHeight, displayVelocity;
    boolean runningEngine;
    int entryUI;

    // time-incrementation system variables
    int tick;
    double timeElapsed; // in s

    // physics-based constant
    final double GRAVITY = -9.80665;

    // inputs
    double startHeight;
    int timePrecision;

    KinematicsEngine(int engineMode) {
        this.engineMode = engineMode;
    }

//    void startPhysicsEngine() {
//        if (CAR_SIMULATOR) {
//            ;
//        }
//        else {
//            runCarSimulator();
//        }
//
//    }


    // equivalent of the "main" - runs the gravity simulator
    void runGravitySimulator(int callFrom) throws InterruptedException {

        entryUI = callFrom;

        // have to register values in console if entry call is from console
        if (entryUI == TEXT) {
            registerValues();
        }

        // use given values to make object
        object = new Object();

        // make constant for time (if user wants real-time generation) TODO: MAKE THIS AN OPTIONAL THING
        final long tickTimingInterval = (long) (1000.0 / Math.pow(10, timePrecision));

        // activate gravity engine
        runningEngine = true;

        while (true) {
            // Wait for the specified time interval
            if (runningEngine) {

                try {
                    Thread.sleep(tickTimingInterval);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt(); // Restore interrupted status
                }

                runTick();

                if ((object.displacement + startHeight) < 0) {
                    object.displacement = -startHeight;
                    printValues();
                    System.out.println("GROUND CONTACT!");
//                    resetSimulation();
                    runningEngine = false;
                }

                if (entryUI == TEXT) {
                    printValues();
                }
                else {
                    displayHeight = Math.round((object.displacement + startHeight) * Math.pow(10, timePrecision)) / Math.pow(10, timePrecision);
                    displayVelocity = Math.round((object.instantaneousVelocity) * Math.pow(10, timePrecision)) / Math.pow(10, timePrecision);
                    Main.graphicsModule.updateSimulationTracker();
                }

            }


        }
    }

    // assume text based only
    void runCarSimulator()  {
        // open pedal ui - thread may be needed

        // custom tick


    }

    // [BEHAVIOURS]

    void registerValues() {

        // Start height
        while (true) {
            try {
                System.out.print("[METERS] START HEIGHT: ");
                startHeight = userInput.nextDouble();

                if (startHeight >= 0) {
                    break;
                }
                else {
                    System.out.println("[ERROR] HEIGHT MUST BE GREATER THAN/EQUAL TO 0.");
                }

            }
            catch (Exception e) {
                System.out.println("[ERROR] INPUT FLOAT VALUE.");
                userInput.nextLine();

            }
        }

        while (true) {
            try {
                System.out.print("[TICKS] TIME PRECISION: ");

                // negative values should work as well.
                timePrecision = userInput.nextInt();
                break;

            }
            catch (Exception e) {
                System.out.println("[ERROR] INPUT INTEGER VALUE.");
                userInput.nextLine();
            }
        }



    }

    void runTick() {

        // 1 time
        tick += 1;
        timeElapsed = (tick) / (Math.pow(10, timePrecision));

        // 2 velocity
        final double GRAVITY_ACCELERATION_BY_TIME = GRAVITY / (Math.pow(10, timePrecision));
        double proposedVelocity = object.instantaneousVelocity + GRAVITY_ACCELERATION_BY_TIME;

        // 3 displacement (might have to rework for bouncing)
        double proposedDisplacement = ((proposedVelocity) / 2.0) * timeElapsed;

        // check if d<=0
        object.instantaneousVelocity = proposedVelocity;
        object.displacement = proposedDisplacement;


    }

    void toggleSimulation() {
        runningEngine = !runningEngine;
    }

    public void setStartHeight(double startHeight) {
        this.startHeight = startHeight;
    }

    public void setTimePrecision(int timePrecision) {
        this.timePrecision = timePrecision;
    }

    void resetSimulation() {
        object.resetValues();
        timeElapsed = 0.0;
        tick = 0;
    }

    public double getDisplayHeight() {
        return displayHeight;
    }

    public double getDisplayVelocity() {
        return displayVelocity;
    }

    public double getTimeElapsed() {
        return timeElapsed;
    }

    void printValues() {
        if (entryUI == TEXT) {
            System.out.println(
                    "TICK: " + tick + " | ELAPSED TIME: " + timeElapsed + " | VELOCITY: " + Math.round((object.instantaneousVelocity) * Math.pow(10, timePrecision)) / Math.pow(10, timePrecision) + " | HEIGHT: "  + Math.round((object.displacement + startHeight) * Math.pow(10, timePrecision)) / Math.pow(10, timePrecision) + "|"
            );        }
        else {
            displayHeight = Math.round((object.displacement + startHeight) * Math.pow(10, timePrecision)) / Math.pow(10, timePrecision);
            displayVelocity = Math.round((object.instantaneousVelocity) * Math.pow(10, timePrecision)) / Math.pow(10, timePrecision);
            Main.graphicsModule.updateSimulationTracker();
        }


    }
}
