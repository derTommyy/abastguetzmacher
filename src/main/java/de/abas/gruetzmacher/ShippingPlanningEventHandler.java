package de.abas.gruetzmacher;

import de.abas.erp.axi2.annotation.EventHandler;
import de.abas.erp.axi2.EventHandlerRunner;
import de.abas.erp.jfop.rt.api.annotation.RunFopWith;
import de.abas.erp.db.schema.shippingplanning.ShippingPlanningEditor;
import de.abas.erp.db.schema.shippingplanning.ShippingPlanningEditor.Table;
import de.abas.erp.axi.screen.ScreenControl;
import de.abas.erp.axi2.event.ButtonEvent;
import de.abas.erp.axi.event.EventException;
import de.abas.erp.axi2.annotation.ButtonEventHandler;
import de.abas.erp.db.DbContext;
import de.abas.erp.axi2.type.ButtonEventType;

@RunFopWith(EventHandlerRunner.class)
@EventHandler(head = ShippingPlanningEditor.class, row = ShippingPlanningEditor.Row.class)
public class ShippingPlanningEventHandler {

	@ButtonEventHandler(field = "releaseProcSugg", type = ButtonEventType.BEFORE)
	public void releaseProcSuggButtonBefore(ButtonEvent event, ScreenControl screenControl, DbContext ctx,
			ShippingPlanningEditor head) throws EventException {
		Table table = head.table();
		for (int i = 1; i <= table.getRowCount(); i++) {
			if(table.getRow(i).getRelease()) {
				// Prüfe ob Artikel gesperrt ist
				if(table.getRow(i).getProduct().getYzhmzgylock()) {
					// Artikel ist gesperrt
					throw new EventException("Artikel " + table.getRow(i).getProduct().getIdno() + " für den Auftrag "+table.getRow(i).getSalesHead().getIdno()+" ist gesperrt. Die Versandplanung kann nicht freigegeben werden.", 1);
				}
			}
		}
	}
}
