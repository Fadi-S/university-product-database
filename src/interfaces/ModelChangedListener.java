package interfaces;

import java.util.EventListener;

public interface ModelChangedListener extends EventListener {
    void createdModel(Model model);
}
