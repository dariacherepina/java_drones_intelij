package Exception;


public class AbsenceTypeForDrone extends Exception{
    /**
     * Throw when there is no DroneType for the Drone
     * @param message string
     */
    public AbsenceTypeForDrone(String message) {
        super(message);
    }
}
