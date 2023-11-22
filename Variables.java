import java.io.*;

public class Variables {
    
    double pocketMoney = 0;
    double totalIncome = 0;
    double totalExpenses = 0;
    double savings = 0;

    public void updateToFile(String fileName) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeDouble(55.0);
            oos.writeDouble(45.78);
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
        } catch (IOException e) {
            e.printStackTrace();
        } 
    }

    public static void main(String[] args) {
        Variables myVariables = new Variables();
        // Update variables to file
        myVariables.updateToFile("Data.dat");

        // Update variables from file
        myVariables.updateFromFile("Data.dat");

        // Test: Print values after reading from file
        System.out.println("Pocket Money: " + myVariables.pocketMoney);
        System.out.println("Total Income: " + myVariables.totalIncome);
        System.out.println("Total Expenses: " + myVariables.totalExpenses);
        System.out.println("Savings: " + myVariables.savings);
    }

}
