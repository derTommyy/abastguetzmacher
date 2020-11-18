package de.abas.gruetzmacher;

import de.abas.erp.axi2.annotation.EventHandler;
import de.abas.erp.axi2.EventHandlerRunner;
import de.abas.erp.jfop.rt.api.annotation.RunFopWith;
import de.abas.jfop.base.buffer.BufferFactory;
import de.abas.jfop.base.buffer.GlobalTextBuffer;
import de.abas.erp.db.schema.part.ProductEditor;
import de.abas.erp.axi2.event.FieldFillEvent;
import de.abas.erp.axi2.event.FieldEvent;
import de.abas.erp.axi.screen.ScreenControl;
import de.abas.erp.axi2.annotation.FieldEventHandler;
import de.abas.eks.jfop.FOPException;
import de.abas.eks.jfop.FOPExitException;
import de.abas.erp.api.gui.TextBox;
import de.abas.erp.axi.event.EventException;
import de.abas.erp.axi2.type.FieldEventType;
import de.abas.erp.db.DbContext;
import de.abas.erp.axi2.type.ScreenEventType;
import de.abas.erp.common.type.AbasDate;
import de.abas.erp.axi2.event.ScreenEvent;
import de.abas.erp.axi2.annotation.ScreenEventHandler;

@RunFopWith(EventHandlerRunner.class)
@EventHandler(head = ProductEditor.class, row = ProductEditor.Row.class)
public class ProductEventHandler {

	@FieldEventHandler(field = "yzhmzgylock", type = FieldEventType.EXIT)
	public void yzhmzgylockExit(FieldEvent event, ScreenControl screenControl, DbContext ctx, ProductEditor head)
			throws EventException {
		// Bei betätigen des Bool Feldes Artikelsperre wird das Datum und der
		// Mitarbeiter gefüllt
		BufferFactory bufferFactory = BufferFactory.newInstance(true);
		GlobalTextBuffer globalTextBuffer = bufferFactory.getGlobalTextBuffer();

		if (head.getYzhmzgylock()) {
			// head.setYzhmzgylockemployee();
			head.setYzhmzgylockdate(globalTextBuffer.getAbasDateValue("date"));
			head.setYzhmzgylockemployee(globalTextBuffer.getStringValue("operatorCode"));
		} else {
			head.setYzhmzgylockdate(null);
			head.setYzhmzgylockemployee("");
			head.setYzhmzgylockreason("");
		}
	}

	@ScreenEventHandler(type = ScreenEventType.VALIDATION)
	public void screenValidation(ScreenEvent event, ScreenControl screenControl, DbContext ctx, ProductEditor head)
			throws EventException {
		// Ist das Bool Feld Artikelsperre gesetzt muss auch ein Sperrgrund angegeben
		// werden
		if (head.getYzhmzgylock() && head.getYzhmzgylockreason() == "") {
			throw new EventException("Achtung: der Artikel ist Gesperrt. Bitte dokumentieren Sie einen Sperrgrund!", 1);
		}
	}
}
