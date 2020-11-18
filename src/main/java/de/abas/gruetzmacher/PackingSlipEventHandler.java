package de.abas.gruetzmacher;

import de.abas.erp.axi2.annotation.EventHandler;
import de.abas.erp.axi2.EventHandlerRunner;
import de.abas.erp.jfop.rt.api.annotation.RunFopWith;
import de.abas.erp.db.schema.part.Product;
import de.abas.erp.db.schema.part.ProductEditor;
import de.abas.erp.db.schema.part.SelectablePart;
import de.abas.erp.db.schema.sales.PackingSlipEditor;
import de.abas.erp.db.schema.sales.PackingSlipEditor.Table;
import de.abas.erp.axi2.type.ScreenEventType;
import de.abas.erp.axi2.event.ScreenEvent;
import de.abas.erp.axi2.event.FieldFillEvent;
import de.abas.erp.axi2.event.FieldEvent;
import de.abas.erp.axi.screen.ScreenControl;
import de.abas.erp.api.gui.TextBox;
import de.abas.erp.axi.event.EventException;
import de.abas.erp.axi2.annotation.FieldEventHandler;
import de.abas.erp.axi2.annotation.ScreenEventHandler;
import de.abas.erp.axi2.type.FieldEventType;
import de.abas.erp.db.DbContext;

@RunFopWith(EventHandlerRunner.class)
@EventHandler(head = PackingSlipEditor.class, row = PackingSlipEditor.Row.class)
public class PackingSlipEventHandler {

	@ScreenEventHandler(type = ScreenEventType.VALIDATION)
	public void screenValidation(ScreenEvent event, ScreenControl screenControl, DbContext ctx, PackingSlipEditor head)
			throws EventException {
		Table table = head.table();
		for (int i = 1; i <= table.getRowCount(); i++) {
			SelectablePart selectablePart = table.getRow(i).getProduct();
			if (selectablePart instanceof Product) {
				Product product = (Product) selectablePart;
				if (product.getYzhmzgylock()) {
					throw new EventException("Achtung, der Artikel " + product.getIdno()
							+ " ist Gesperrt. Der Vorgang kann nicht gespeichert werden!", 1);
				}
			}
		}
	}

	@FieldEventHandler(field = "product", type = FieldEventType.EXIT, table = true)
	public void productExit(FieldEvent event, ScreenControl screenControl, DbContext ctx, PackingSlipEditor head,
			PackingSlipEditor.Row currentRow) throws EventException {

		if (!currentRow.getProduct().equals(null)) {
			SelectablePart selectablePart = currentRow.getProduct();
			if (selectablePart instanceof Product) {
				Product product = (Product) selectablePart;
				if (product.getYzhmzgylock()) {
					TextBox textBox = new TextBox(ctx, "Fehler",
							"Artikel ist gesperrt und kann nicht verwendet werden.");
					textBox.show();
				}
			}
		}

	}
}
