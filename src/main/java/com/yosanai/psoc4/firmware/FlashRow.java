package com.yosanai.psoc4.firmware;

public class FlashRow {
	int arrayId;
	String rowNo;
	int dataLength;
	byte[] data;
	int rowCheckSum;
	boolean rowEnd;
	
	public FlashRow() {
	}

	public int getArrayId() {
		return arrayId;
	}

	public void setArrayId(int arrayId) {
		this.arrayId = arrayId;
	}

	public String getRowNo() {
		return rowNo;
	}

	public void setRowNo(String rowNo) {
		this.rowNo = rowNo;
	}

	public int getDataLength() {
		return dataLength;
	}

	public void setDataLength(int dataLength) {
		this.dataLength = dataLength;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	public int getRowCheckSum() {
		return rowCheckSum;
	}

	public void setRowCheckSum(int rowCheckSum) {
		this.rowCheckSum = rowCheckSum;
	}

	public boolean isRowEnd() {
		return rowEnd;
	}

	public void setRowEnd(boolean rowEnd) {
		this.rowEnd = rowEnd;
	}

}
