package interfaces;

import java.util.HashMap;
import view.component.PandaProdFrame;

/**
 * Classe servant à construire une action agissant sur l'IHM.
 * @author Mary
 */
public abstract class AbstractIHMAction implements IAction {
   
    protected HashMap<String, Object> hsJcomponent;

    public AbstractIHMAction(PandaProdFrame csFrame) {
        hsJcomponent = csFrame.getJComponent();
    }
}
