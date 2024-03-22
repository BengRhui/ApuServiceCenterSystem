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

    public static double getPriceFromItem(String itemName) {
        TextFileOperationsComponent.readElectronicsData();
        for (ElectronicItems item: fullItemList) {
            if (item.itemName.equals(itemName)) {
                return item.price;
            }
        }
        return -1;
    }

    public static String generateElectronicItemID() {

        TextFileOperationsComponent.readElectronicsData();
        StringBuilder lastID = new StringBuilder(ElectronicItems.getFullItemList().getLast().itemID);
        lastID.delete(0, 1);
        int newID = Integer.parseInt(lastID.toString()) + 1;

        return "I" + String.format("%03d", newID);

    }

}
