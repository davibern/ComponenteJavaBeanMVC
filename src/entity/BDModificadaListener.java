package entity;

import event.BDModificadaEvent;
import java.util.EventListener;

/**
 *
 * @author davibern
 */
    public interface BDModificadaListener extends EventListener {
        public void capturarBDModificada(BDModificadaEvent ev);
    }
