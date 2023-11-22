import java.io.*;

public class Variables {
    
    double pocketMoney = 0;
    double totalIncome = 0;
    double totalExpenses = 0;
    double savings = 0;

    public void updateToFile(String fileName) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
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
        } catch (IOException e) {
            e.printStackTrace();
        } 
    }


}
