package Drone;

public class Drones extends Catalog {

    private int id;
    private String dronetype;
    private String created;
    private String serialnumber;
    private int carriage_weight;
    private String carriage_type;

    public Drones() {
    }

    public Drones(int id, String dronetype, String created, String serialnumber, int carriage_weight, String carriage_type) {
        this.id = id;
        this.dronetype = dronetype;
        this.created = created;
        this.serialnumber = serialnumber;
        this.carriage_weight = carriage_weight;
        this.carriage_type = carriage_type;
    }


    public int getId() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Drones [id=" + id +
                ", dronetype=" + dronetype +
                ", created=" + created
                + ", serialnumber=" + serialnumber
                + ", carriage_weight=" + carriage_weight
                + ", carriage_type=" + carriage_type + "]";
    }

}