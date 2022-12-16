package interfaces;

import model.Model;

import java.util.EventListener;

public interface ModelChangedListener extends EventListener {
    void createdModel(Model model);
}
