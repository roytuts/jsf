package com.roytuts.jsf2.google.chart.data;

import java.util.ArrayList;
import java.util.List;

public class PieChartData {

	private static final List<KeyValue> pieDataList;

	static {
		pieDataList = new ArrayList<PieChartData.KeyValue>();
		pieDataList.add(new KeyValue("Russia", "17098242"));
		pieDataList.add(new KeyValue("Canada", "9984670"));
		pieDataList.add(new KeyValue("USA", "9826675"));
		pieDataList.add(new KeyValue("China", "9596961"));
		pieDataList.add(new KeyValue("Brazil", "8514877"));
		pieDataList.add(new KeyValue("Australia", "7741220"));
		pieDataList.add(new KeyValue("India", "3287263"));
	}

	public static List<KeyValue> getPieDataList() {
		return pieDataList;
	}

	public static class KeyValue {
		String key;
		String value;

		public KeyValue(String key, String value) {
			super();
			this.key = key;
			this.value = value;
		}

		public String getKey() {
			return key;
		}

		public void setKey(String key) {
			this.key = key;
		}

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}

	}

}
