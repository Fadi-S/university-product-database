import model.ProductItem;
import view.Navigator;
import view.Page;
import view.ViewProductScreen;

public class Main {
    public static void main(String[] args) {
        ProductItem.createTableIfNotExist();
        Page page = new ViewProductScreen();
        Navigator.goTo(page);
    }
}