public class Object {
    double instantaneousVelocity;
    double displacement;
    double acceleration;

    public Object(double instantaneousVelocity, double displacement, double acceleration) {
        this.instantaneousVelocity = instantaneousVelocity;
        this.displacement = displacement;
        this.acceleration = acceleration;
    }

    public Object() {
        instantaneousVelocity = 0.0;
        displacement = 0.0;
        acceleration = 0.0;
    }

    void resetValues() {
        instantaneousVelocity = 0.0;
        displacement = 0.0;
    }
}
