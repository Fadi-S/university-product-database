import view.Navigator;
import view.Page;
import view.ViewProductScreen;

public class Main {
    public static void main(String[] args) {
        Page page = new ViewProductScreen();

        Navigator.goTo(page);
    }
}