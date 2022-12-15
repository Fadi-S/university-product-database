import model.ProductItem;
import view.Navigator;
import view.ViewProductScreen;

public class Main {
    public static void main(String[] args) {
        ProductItem.createTableIfNotExist();

        Navigator.goTo(new ViewProductScreen());
    }
}