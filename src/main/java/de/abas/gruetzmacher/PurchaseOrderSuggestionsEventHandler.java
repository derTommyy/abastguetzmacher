package de.abas.gruetzmacher;

import java.util.List;

import de.abas.erp.axi.event.EventException;
import de.abas.erp.axi.screen.ScreenControl;
import de.abas.erp.axi2.EventHandlerRunner;
import de.abas.erp.axi2.annotation.ButtonEventHandler;
import de.abas.erp.axi2.annotation.EventHandler;
import de.abas.erp.axi2.event.ButtonEvent;
import de.abas.erp.axi2.type.ButtonEventType;
import de.abas.erp.db.DbContext;
import de.abas.erp.db.schema.part.Product;
import de.abas.erp.db.schema.part.SelectablePart;
import de.abas.erp.db.schema.purchasing.PurchaseOrderSuggestionsEditor;
import de.abas.erp.jfop.rt.api.annotation.RunFopWith;

@RunFopWith(EventHandlerRunner.class)
@EventHandler(head = PurchaseOrderSuggestionsEditor.class, row = PurchaseOrderSuggestionsEditor.Row.class)
public class PurchaseOrderSuggestionsEventHandler {

	@ButtonEventHandler(field = "loadTab", type = ButtonEventType.AFTER)
	public void loadTabButtonAfter(ButtonEvent event, ScreenControl screenControl, DbContext ctx,
			PurchaseOrderSuggestionsEditor head) throws EventException {
		List<PurchaseOrderSuggestionsEditor.Row> table = head.getTableRows();
		for (int i = 0; i < table.size(); i++) {
			if (table.get(i).getProduct() != null) {
				SelectablePart part = table.get(i).getProduct();
				if (part instanceof Product) {
					Product product = (Product) part;
					table.get(i).setYzhmzgytlbestand(product.getStock());
				}
			}
		}
	}
	// Add events here
}
