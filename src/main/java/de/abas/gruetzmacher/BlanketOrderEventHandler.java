package de.abas.gruetzmacher;

import java.util.List;

import de.abas.erp.axi.event.EventException;
import de.abas.erp.axi.screen.ScreenControl;
import de.abas.erp.axi2.EventHandlerRunner;
import de.abas.erp.axi2.annotation.EventHandler;
import de.abas.erp.axi2.annotation.ScreenEventHandler;
import de.abas.erp.axi2.event.ScreenEvent;
import de.abas.erp.axi2.type.ScreenEventType;
import de.abas.erp.db.DbContext;
import de.abas.erp.db.schema.sales.BlanketOrder;
import de.abas.erp.db.schema.sales.BlanketOrderEditor;
import de.abas.erp.jfop.rt.api.annotation.RunFopWith;

@RunFopWith(EventHandlerRunner.class)
@EventHandler(head = BlanketOrderEditor.class, row = BlanketOrderEditor.Row.class)
public class BlanketOrderEventHandler {

	@ScreenEventHandler(type = ScreenEventType.ENTER)
	public void screenEnter(ScreenEvent event, ScreenControl screenControl, DbContext ctx, BlanketOrderEditor head)
			throws EventException {

		List<BlanketOrder.Row> blanketOrderTable = head.getTableRows();
		for (int i = 0; i < blanketOrderTable.size(); i++) {
//			SelectionBuilder<SalesOrder.Row> selectionBuilder = SelectionBuilder.create(SalesOrder.Row.class);
//			selectionBuilder.add(Conditions.eq(SalesOrder.Row.META.origItem, blanketOrderTable.get(i).getId()));
//			selectionBuilder.add(Conditions.eq(field, value));
		}
	}
	// Add events here
}
