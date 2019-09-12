package com.roytuts.jsf2.google.chart.mbean;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.roytuts.jsf2.google.chart.data.PieChartData;
import com.roytuts.jsf2.google.chart.data.PieChartData.KeyValue;

@ViewScoped
@ManagedBean
public class PieChartMBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private String pieChartData;
	private List<KeyValue> pieDataList;

	public PieChartMBean() {
		this.pieDataList = PieChartData.getPieDataList();
	}

	public String getPieChartData() {
		if (pieChartData == null || pieChartData.trim().length() <= 0) {
			populateData();
		}
		return pieChartData;
	}

	private void populateData() {
		StringBuilder stringBuilder = new StringBuilder();
		for (KeyValue pieData : pieDataList) {
			stringBuilder.append("[");
			stringBuilder.append("'");
			stringBuilder.append(pieData.getKey());
			stringBuilder.append("'");
			stringBuilder.append(",");
			stringBuilder.append(pieData.getValue());
			stringBuilder.append("]");
			stringBuilder.append(",");
		}
		pieChartData = stringBuilder.toString().substring(0, stringBuilder.toString().length() - 1);
	}

}
