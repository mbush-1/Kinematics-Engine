import java.util.Scanner;

public class KinematicsEngine {

    int tick;
    double timeElapsed; // in s
    Object object;

    // "main"
    static void runSimulator() {
        KinematicsEngine kinematicsEngine = new KinematicsEngine();
        kinematicsEngine.registerValues();
        kinematicsEngine.initializeObjects();
        for (int i = 0; i <= 5000; i++) {
            kinematicsEngine.runTick();
        }
    }

    // [FIELDS]

    static Scanner userInput;
    final double GRAVITY = -9.80665;
    double startHeight;
    int timePrecision;

    // [BEHAVIOURS]

    void registerValues() {
        userInput = new Scanner(System.in);

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

    void initializeObjects() {
        object = new Ball(startHeight);
    }

    void simulateGravity() {
        while (true) {

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


//        proposedVelocity = Math.round(proposedVelocity * Math.pow(10, timePrecision)) / Math.pow(10, timePrecision);
//        proposedDisplacement = Math.round(proposedDisplacement * Math.pow(10, timePrecision)) / Math.pow(10, timePrecision);


        // check if d<=0

        object.instantaneousVelocity = proposedVelocity;
        object.displacement = proposedDisplacement;

        printValues();

    }

    void printValues() {
        System.out.println(
                "TICK: " + tick + " | ELAPSED TIME: " + timeElapsed + " | VELOCITY: " + object.instantaneousVelocity + " | HEIGHT: "  + (object.displacement + startHeight ) + "|"
        );
    }
}
