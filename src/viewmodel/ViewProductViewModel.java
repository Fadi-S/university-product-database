package viewmodel;

import model.ProductItem;

public class ViewProductViewModel {
    public ProductItem[] get() {
        return ProductItem.getAll();
    }
}
