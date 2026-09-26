import java.util.Scanner;

public class CourierDeliveryandRouteTracking {
    public static void main(String[] args) {
        
        // CO1
        String company = "FastTrack";
        double weight = 4.5;
        boolean tracking = true;
        
        // CO3
        String[] route = {"Warehouse", "Hub Station", "Destination"};
        String currentHub = "Warehouse";

        Scanner sc = new Scanner(System.in);
        System.out.println("=== " + company + " System ===");

        // CO2
        while (tracking) {
            System.out.println();
            System.out.println("1. Route Info | 2. Advance Status | 3. Exit");
            System.out.print("Choice: ");
            int choice = sc.nextInt();

            // CO2
            switch (choice) {
                case 1:
                    System.out.println("Start Stop: " + route[0]);
                    System.out.println("Middle Stop: " + route[1]);
                    System.out.println("Final Stop: " + route[2]);
                    System.out.println("Package Weight: " + weight + " kg");
                    break;

                case 2:
                    //CO1&CO2
                    if (currentHub.equals("Warehouse")) {
                        currentHub = "Hub Station";
                    } else if (currentHub.equals("Hub Station")) {
                        currentHub = "Destination";
                    } else {
                        System.out.println("Package already reached final destination.");
                    }
                    String status = currentHub.equals("Destination") ? "Delivered" : "In Transit";
                    System.out.println("Updated Location: " + currentHub + " (" + status + ")");
                    break;

                case 3:
                    System.out.println("Exiting tracker.");
                    tracking = false; // Assignment operator breaks the single loop
                    break;

                default:
                    System.out.println("Invalid option.");
                    break;
            }
        }
        sc.close();
    }
}


