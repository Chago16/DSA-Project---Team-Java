import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Variables {
    
    public static double pocketMoney = 0.0;
    public static double totalIncome = 0.0;
    public static double totalExpenses = 0.0;
    public static double savings = 0.0;

    // Reference to HomePage instance
    private static HomePage homePage;

    // Setter method for HomePage
    public static void setHomePage(HomePage page) {
        homePage = page;
    }

    public void updateToFile(String fileName) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName, false))) {
            oos.writeDouble(pocketMoney);
            oos.writeDouble(totalIncome);
            oos.writeDouble(totalExpenses);
            oos.writeDouble(savings);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updateFromFile(String fileName) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            pocketMoney = ois.readDouble();
            totalIncome = ois.readDouble();
            totalExpenses = ois.readDouble();
            savings = ois.readDouble();
            
            // Trigger the update in HomePage
            if (homePage != null) {
                homePage.updateAvailableBalance();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } 
    }
}
