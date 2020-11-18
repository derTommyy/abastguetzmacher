package de.abas.gruetzmacher;

import de.abas.erp.axi.event.EventException;
import de.abas.erp.axi.screen.ScreenControl;
import de.abas.erp.axi2.EventHandlerRunner;
import de.abas.erp.axi2.annotation.ButtonEventHandler;
import de.abas.erp.axi2.annotation.EventHandler;
import de.abas.erp.axi2.event.ButtonEvent;
import de.abas.erp.axi2.type.ButtonEventType;
import de.abas.erp.db.DbContext;
import de.abas.erp.db.schema.purchasing.InvoiceEditor;
import de.abas.erp.jfop.rt.api.annotation.RunFopWith;

@RunFopWith(EventHandlerRunner.class)
@EventHandler(head = InvoiceEditor.class, row = InvoiceEditor.Row.class)
public class PurchasingInvoiceEventHandler {

	@ButtonEventHandler(field="yzhmzggetOpenItems", type = ButtonEventType.AFTER)
	public void yzhmzggetOpenItemsButtonAfter(ButtonEvent event, ScreenControl screenControl, DbContext ctx, InvoiceEditor head) throws EventException {
		// TODO Auto-generated method stub
	}
	// Add events here
	
}
