package de.abas.gruetzmacher;

import de.abas.erp.axi2.annotation.EventHandler;
import de.abas.erp.axi2.EventHandlerRunner;
import de.abas.erp.jfop.rt.api.annotation.RunFopWith;
import de.abas.erp.db.schema.customer.CustomerEditor;
import de.abas.erp.axi2.type.ScreenEventType;
import de.abas.erp.axi2.event.ScreenEvent;
import de.abas.erp.axi.screen.ScreenControl;
import de.abas.erp.axi.event.EventException;
import de.abas.erp.axi2.annotation.ScreenEventHandler;
import de.abas.erp.db.DbContext;
import de.abas.erp.axi2.event.ScreenEndEvent;

@RunFopWith(EventHandlerRunner.class)
@EventHandler(head = CustomerEditor.class)
public class CustomerEventHandler {

	@ScreenEventHandler(type = ScreenEventType.ENTER)
	public void screenEnter(ScreenEvent event, ScreenControl screenControl, DbContext ctx, CustomerEditor head) throws EventException {
		// TextBox textBox = new TextBox(ctx, "Willkommen im Kunden", "Sie befinden sich im Objekt Kunde.");
		
	}
	// Add events here

	@ScreenEventHandler(type = ScreenEventType.END)
	public void screenEnd(ScreenEndEvent event, ScreenControl screenControl, DbContext ctx, CustomerEditor head) throws EventException {
		// TODO Auto-generated method stub
	}
	
}
