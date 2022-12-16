package viewmodel;

import model.ProductItem;

import java.util.ArrayList;
import java.util.Arrays;

public class ViewProductViewModel {
    public ArrayList<ProductItem> get() {
        return new ArrayList<>(Arrays.stream(ProductItem.getAll()).toList());
    }
}
