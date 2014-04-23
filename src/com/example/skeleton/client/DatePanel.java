package com.example.skeleton.client;

import java.util.Date;
import java.util.logging.Logger;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;

import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.ClickEvent;

import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ChangeEvent;

import com.google.gwt.user.client.ui.Composite;

import org.gwtbootstrap3.extras.datetimepicker.client.ui.DateTimeBox;
import org.gwtbootstrap3.extras.datetimepicker.client.ui.base.constants.DateTimePickerView;

import org.gwtbootstrap3.client.ui.Well;
import org.gwtbootstrap3.client.ui.ListBox;
import org.gwtbootstrap3.client.ui.Button;


public class DatePanel extends Composite {

	@UiField DateTimeBox dateBox;
	@UiField ListBox dateGranularity;
	@UiField Button btnGetDate;


	public DatePanel() {
		this.initWidget(uiBinder.createAndBindUi(this));

		this.dateGranularity.addChangeHandler(new ChangeHandler() {
			@Override
			public void onChange(ChangeEvent event) {
				DatePanel self = DatePanel.this;

				String selectedGranularity = self.dateGranularity.getValue(
					self.dateGranularity.getSelectedIndex());

				DateTimePickerView granularityType = DateTimePickerView.valueOf(
					selectedGranularity.toUpperCase());

				DatePanel.logger.finest("Date granularity selected: "
					+ granularityType.toString());

				self.setDateTimePickerView(granularityType);
			}
		});

		this.btnGetDate.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				DatePanel.this.logger.finest("DateTimeBox value: "
					+ DatePanel.this.dateBox.getValue().toString());
			}
		});
	}


	/**
	 * Alters DateTimeBox view mode and format according to the specified date granularity.
	 */
	private void setDateTimePickerView(DateTimePickerView granularityType) {
		DateTimePickerView minViewMode = DateTimePickerView.HOUR;
		String dateTimeFormat = "yyyy-mm-dd hh:ii:ss";

		if (granularityType == DateTimePickerView.YEAR) {
			minViewMode = DateTimePickerView.DECADE;
			dateTimeFormat = "yyyy";

		} else if (granularityType == DateTimePickerView.MONTH) {
			minViewMode = DateTimePickerView.YEAR;
			dateTimeFormat = "yyyy-mm";

		} else if (granularityType == DateTimePickerView.DAY) {
			minViewMode = DateTimePickerView.MONTH;
			dateTimeFormat = "yyyy-mm-dd";

		} else if (granularityType == DateTimePickerView.HOUR) {
			minViewMode = DateTimePickerView.DAY;
			dateTimeFormat = "yyyy-mm-dd hh";
		}

		this.dateBox.setMinView(minViewMode);
		this.dateBox.setStartView(minViewMode);
		this.dateBox.setFormat(dateTimeFormat);
		this.dateBox.reload();
	}


	interface DatePanelUiBinder extends UiBinder<Well, DatePanel> {}
	private static DatePanelUiBinder uiBinder = GWT.create(DatePanelUiBinder.class);

	private static final Logger logger = Logger.getLogger(DatePanel.class.getName());

}
