import java.util.ArrayList;

public class ElectronicItems {
    String itemID;
    String itemName;
    double price;

    private static final ArrayList<ElectronicItems> fullItemList = new ArrayList<>();
    public ElectronicItems(String itemName, double price) {
        this.itemID = "I" + String.format("%03d", fullItemList.size() + 1);
        this.itemName = itemName;
        this.price = price;
    }

    public static ArrayList<ElectronicItems> getFullItemList() {
        return fullItemList;
    }

    public static double getPriceFromItem(String itemName) {
        TextFileOperationsComponent.readElectronicsData();
        for (ElectronicItems item: fullItemList) {
            if (item.itemName.equals(itemName)) {
                return item.price;
            }
        }
        return -1;
    }

}
