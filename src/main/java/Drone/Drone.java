package Drone;

public class Drone {

        private int id;
        private String dronetype;
        private String created;
        private String serialnumber;
        private int carriage_weight;
        private String carriage_type;
//        private String manufacturer;
//        private String typename;
//        private int weight;
//        private int max_speed;
//        private int battery_capacity;
//        private int control_range;
//        private int max_carriage;

        public Drone (){}
        public Drone(int id, String dronetype, String created, String serialnumber, int carriage_weight, String carriage_type) {
                this.id = id;
                this.dronetype = dronetype;
                this.created = created;
                this.serialnumber = serialnumber;
                this.carriage_weight = carriage_weight;
                this.carriage_type = carriage_type;
//                this.manufacturer = manufacturer;
//                this.typename = typename;
//                this.weight = weight;
//                this.max_speed = max_speed;
//                this.battery_capacity = battery_capacity;
//                this.control_range = control_range;
//                this.max_carriage = max_carriage;
        }


        @Override
        public String toString() {
                return "Drone{" +
                        "id=" + id +
                        ", dronetype='" + dronetype + '\'' +
                        ", created='" + created + '\'' +
                        ", serialnumber='" + serialnumber + '\'' +
                        ", carriage_weight=" + carriage_weight +
                        ", carriage_type='" + carriage_type + '\'';
//                        ", manufacturer='" + manufacturer + '\'' +
//                        ", typename='" + typename + '\'' +
//                        ", weight=" + weight +
//                        ", max_speed=" + max_speed +
//                        ", battery_capacity=" + battery_capacity +
//                        ", control_range=" + control_range +
//                        ", max_carriage=" + max_carriage +
//                        '}';
        }

}
