package exception;

/**
 * Throw when there is no DroneType for the Drone
 *
 * @author Daria Cherepina
 */
public class AbsenceTypeForDrone extends Exception {
    public AbsenceTypeForDrone(String message) {
        super(message);
    }
}