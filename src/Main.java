/** MAIN - Starts up all necessary functions. **/
public class Main {

    static KinematicsEngine kinematicsEngine;
    static GraphicsModule graphicsModule;

    public static void main(String[] args) throws InterruptedException {

        kinematicsEngine = new KinematicsEngine(KinematicsEngine.CAR_SIMULATOR);




        // For debugging convenience, allows option to run GUI or TEXT-BASED ui.
        System.out.print("[TEXT - 0 | GUI - 1]: ");

        if (KinematicsEngine.userInput.nextInt() == 0) {
            kinematicsEngine.runGravitySimulator(KinematicsEngine.TEXT);
        }
        else {
            graphicsModule = new GraphicsModule();
            graphicsModule.startGraphicsModule();
        }

    }

}