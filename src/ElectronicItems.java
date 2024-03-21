import java.util.ArrayList;

public class ElectronicItems {
    String itemID;
    String itemName;
    double price;

    private static final ArrayList<ElectronicItems> fullItemList = new ArrayList<>();
    public ElectronicItems(String itemID, String itemName, double price) {
        this.itemID = itemID;
        this.itemName = itemName;
        this.price = price;
    }

    public static ArrayList<ElectronicItems> getFullItemList() {
        return fullItemList;
    }

}
