package de.abas.gruetzmacher;

import java.math.BigDecimal;

import de.abas.erp.api.gui.MenuBuilder;
import de.abas.erp.axi.event.EventException;
import de.abas.erp.axi.screen.ScreenControl;
import de.abas.erp.axi2.EventHandlerRunner;
import de.abas.erp.axi2.annotation.EventHandler;
import de.abas.erp.axi2.annotation.ScreenEventHandler;
import de.abas.erp.axi2.event.ScreenEvent;
import de.abas.erp.axi2.type.ScreenEventType;
import de.abas.erp.db.DbContext;
import de.abas.erp.db.Query;
import de.abas.erp.db.schema.company.CompanyData;
import de.abas.erp.db.schema.sales.SalesOrderEditor;
import de.abas.erp.db.selection.Conditions;
import de.abas.erp.db.selection.SelectionBuilder;
import de.abas.erp.jfop.rt.api.annotation.RunFopWith;
import de.abas.jfop.base.buffer.BufferFactory;
import de.abas.jfop.base.buffer.GlobalTextBuffer;

@RunFopWith(EventHandlerRunner.class)
@EventHandler(head = SalesOrderEditor.class, row = SalesOrderEditor.Row.class)
public class SalesOrderEventHandler {

	@ScreenEventHandler(type = ScreenEventType.VALIDATION)
	public void screenValidation(ScreenEvent event, ScreenControl screenControl, DbContext ctx, SalesOrderEditor head)
			throws EventException {
		// Check the minimum order amount from the company data
		GlobalTextBuffer globalTextBuffer = getGlobalTextBuffer();
		if (!globalTextBuffer.getStringValue("evtCmd").equals(globalTextBuffer.getStringValue("actionName12"))) {

			BigDecimal mininumOrderAmount = new BigDecimal(0);
			SelectionBuilder<CompanyData> sbCd = SelectionBuilder.create(CompanyData.class);
			sbCd.add(Conditions.eq(CompanyData.META.idno, "1"));
			Query<CompanyData> qCd = ctx.createQuery(sbCd.build());

			for (CompanyData companyData : qCd) {
				mininumOrderAmount = companyData.getYzhmzgyminorderamount();
			}

			if (head.getTotalNetAmt().compareTo(mininumOrderAmount) == -1) {
				MenuBuilder<String> menuBuilder = new MenuBuilder<>(ctx, "Mindestbestellwert wurde nicht erreicht.");
				createMenu(menuBuilder);
				String select = menuBuilder.show();
				if (select == "2") {
					// add AdditionalItem
					SalesOrderEditor.Row appendRow = head.table().appendRow();
					appendRow.setString(SalesOrderEditor.Row.META.product, "26");
					appendRow.setPrice(mininumOrderAmount);
					// head.table().appendRow().setString(SalesOrderEditor.Row.META.product, "26");
				}
			}
		}
	}

	private GlobalTextBuffer getGlobalTextBuffer() {
		BufferFactory bufferFactory = BufferFactory.newInstance(true);
		return bufferFactory.getGlobalTextBuffer();
	}
	// Add events here

	private void createMenu(MenuBuilder<String> menuBuilder) {
		menuBuilder.addItem("1", "Speichern.");
		menuBuilder.addItem("2", "Mindestbestellposition hinzufügen.");
	}
}
