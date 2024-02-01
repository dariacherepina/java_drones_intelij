package exception;

public class AbsenceTypeForDrone extends Exception {
    /**
     * Throw when there is no DroneType for the Drone
     *
     * @param message String
     */
    public AbsenceTypeForDrone(String message) {
        super(message);
    }
}