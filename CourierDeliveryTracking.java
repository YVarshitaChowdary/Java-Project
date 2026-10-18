
import java.util.*; // CO1
import java.io.*;   // CO5
class Parcel {
    protected String id; // CO5
    protected double wt; // CO1
    public Parcel(String id, double wt) { // CO4
        this.id = id; this.wt = wt;
    }
    public void show() { // CO3
        System.out.printf("ID: %s | %.2f kg%n", id, wt); // CO1
    }
}
class UrgentParcel extends Parcel { // CO4
    private int p;
    public UrgentParcel(String id, double w, int p) { // CO4
        super(id, w); this.p = p;
    }
    @Override
    public void show() { // CO4
        super.show(); System.out.println("Priority: " + p);
    }
}
public class CourierDeliveryTracking {
    public static int getPin(int code) { // CO3
        if (code < 10) return code; 
        return (code % 10) + getPin(code / 10); 
    }
    public static void main(String[] args) {
        List<UrgentParcel> list = new ArrayList<UrgentParcel>(); // CO5
        int[][] grid = { {1, 0}, {0, 1} }; // CO3
        Scanner sc = new Scanner(System.in); // CO1
        boolean run = true; // CO1
        while (run) { // CO2
            System.out.print("\n1. Book | 2. Grid | 3. Report | 4. Exit\nChoice: ");
            try { // CO5
                int choice = sc.nextInt(); // CO1
                switch (choice) { // CO2
                    case 1:
                        System.out.print("Token: ");
                        int tk = sc.nextInt();
                        String id = "PKG-" + tk + "-PIN" + getPin(tk); // CO5
                        System.out.print("Weight: ");
                        double w = sc.nextDouble();
                        char tier = (w <= 5.0) ? 'S' : 'L'; // CO2
                        UrgentParcel p = new UrgentParcel(id, w, 1); // CO4
                        list.add(p); p.show();
                        try (BufferedWriter writer = java.nio.file.Files.newBufferedWriter( // CO5
                                java.nio.file.Path.of("manifest.txt"), 
                                java.nio.file.StandardOpenOption.CREATE, 
                                java.nio.file.StandardOpenOption.APPEND)) {
                            writer.write(id + " | " + tier + "\n");
                        }
                        break;
                    case 2:
                        for (int r = 0; r < grid.length; r++) // CO3
                            for (int c = 0; c < grid[r].length; c++) // CO3
                                if (grid[r][c] == 0) System.out.printf("Vacant: R%d, C%d%n", r, c); // CO2
                        break;
                    case 3:
                        System.out.println("\nHeavy Cargo Fleet Report (>= 5.0kg)");
                        List<String> heavy = new ArrayList<String>(); // CO5
                        for (UrgentParcel up : list) // CO3
                            if (up.wt >= 5.0) heavy.add(up.id.toUpperCase()); // CO2
                        Collections.sort(heavy); // CO5
                        if (heavy.isEmpty()) System.out.println("No heavy cargo."); // CO2
                        else 
                            for (String s : heavy) System.out.println(s); // CO3
                        break;
                    case 4: run = false; break;
                    default: System.out.println("Invalid.");
                }
            } catch (Exception e) { // CO5
                System.out.println("Error."); sc.nextLine(); 
            }
        }
        sc.close(); // CO1
    }
}