import java.util.Scanner;

public class KinematicsEngine {

    int tick;

    // "main"
    static void runSimulator() {
        KinematicsEngine kinematicsEngine = new KinematicsEngine();
        kinematicsEngine.registerValues();
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
        Ball object = new Ball(startHeight);
    }

    void simulateGravity() {

    }

    void runTick() {

    }
}
