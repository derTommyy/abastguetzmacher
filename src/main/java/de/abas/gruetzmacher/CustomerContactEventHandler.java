package de.abas.gruetzmacher;

import de.abas.erp.axi2.annotation.EventHandler;
import de.abas.erp.axi2.EventHandlerRunner;
import de.abas.erp.jfop.rt.api.annotation.RunFopWith;
import de.abas.erp.db.schema.customer.CustomerContact;
import de.abas.erp.db.schema.customer.CustomerContactEditor;
import de.abas.erp.db.selection.Condition;
import de.abas.erp.db.selection.Conditions;
import de.abas.erp.db.selection.SelectionBuilder;
import de.abas.erp.axi2.type.ScreenEventType;
import de.abas.erp.common.type.enums.EnumEditorAction;
import de.abas.erp.axi2.event.ScreenEvent;
import de.abas.erp.axi.screen.ScreenControl;

import java.util.List;

import de.abas.erp.axi.event.EventException;
import de.abas.erp.axi2.annotation.ScreenEventHandler;
import de.abas.erp.db.DbContext;
import de.abas.erp.db.Query;

@RunFopWith(EventHandlerRunner.class)
@EventHandler(head = CustomerContactEditor.class)
public class CustomerContactEventHandler {

	@ScreenEventHandler(type = ScreenEventType.VALIDATION)
	public void screenValidation(ScreenEvent event, ScreenControl screenControl, DbContext ctx,
			CustomerContactEditor head) throws EventException {
		// Generierung der Identnummer
		String xtPrefix = "";
		String xtCount = "";
		if (event.getCommand() == EnumEditorAction.New) {

			// Selektion aller Kundenkontakte zu dieser Firma
			SelectionBuilder<CustomerContact> selectionBuilder = SelectionBuilder.create(CustomerContact.class);
			selectionBuilder = selectionBuilder.add(Conditions.eq(CustomerContact.META.companyARAP, head.getCompanyARAP()));
			Query<CustomerContact> queryCC = ctx.createQuery(selectionBuilder.build());
			List<CustomerContact> list = queryCC.execute();

			
			if (list.size() <= 100 && list.size() >= 10) {
				xtPrefix = "0";
			} else if (list.size() <= 10) {
				xtPrefix = "00";
			}
			
			head.setIdno(head.getCompanyARAP().getIdno() + xtPrefix + String.valueOf(list.size() + 1));

		}

	}
}
