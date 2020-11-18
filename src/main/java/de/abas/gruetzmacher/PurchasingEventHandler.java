package de.abas.gruetzmacher;

import de.abas.erp.axi2.annotation.EventHandler;
import de.abas.erp.axi2.EventHandlerRunner;
import de.abas.erp.jfop.rt.api.annotation.RunFopWith;

import de.abas.erp.db.schema.purchasing.PurchasingEditor;

@RunFopWith(EventHandlerRunner.class)
@EventHandler(head = PurchasingEditor.class)
public class PurchasingEventHandler {
	// Add events here
}
