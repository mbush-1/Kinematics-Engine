public class Ball extends Object {
    double currentHeight;
    // instantaneous velocity

    public Ball(double height) {
        currentHeight = height;
        instantaneousVelocity = 0;
        displacement = 0;
    }
}
