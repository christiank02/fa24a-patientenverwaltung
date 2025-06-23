package patientenverwaltung;

public class Main {

    public static void main(String[] args) {
        try {
            Application application = new Application();
            application.start();
        } catch (Exception e) {
            System.err.println("An error occurred while starting the application: " + e.getMessage());
            e.printStackTrace();
        }
    }

}
