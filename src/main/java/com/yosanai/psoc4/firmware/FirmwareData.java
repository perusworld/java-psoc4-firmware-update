package com.yosanai.psoc4.firmware;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class FirmwareData {

	private String siliconId;

	private String siliconRev;

	private String checkSumType;

	private List<FlashRow> rows = new ArrayList<FlashRow>();

	public String getSiliconId() {
		return siliconId;
	}

	public String getSiliconRev() {
		return siliconRev;
	}

	public String getCheckSumType() {
		return checkSumType;
	}

	public List<FlashRow> getRows() {
		return rows;
	}

	public void parse(InputStream inputStream) throws Exception {
		String dataLine = null;
		int index = 0;
		BufferedReader bufferedReader = null;
		try {
			bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
			while ((dataLine = bufferedReader.readLine()) != null) {
				parse(++index, dataLine);
			}
		} finally {
			if (null != bufferedReader) {
				bufferedReader.close();
				bufferedReader = null;
			}
		}

	}

	public void parse(File file) throws Exception {
		parse(new FileInputStream(file));
	}

	public void parse(int index, String dataLine) throws Exception {
		byte[] data;
		if (1 == index) {
			String str = FirmwareUtils.getInstance().getMSB(dataLine);
			siliconId = str.substring(4, 12);
			siliconRev = str.substring(2, 4);
			checkSumType = str.substring(0, 2);
		} else {
			FlashRow row = new FlashRow();
			StringBuilder dataBuilder = new StringBuilder(dataLine);
			dataBuilder.deleteCharAt(0);
			row.arrayId = Integer.parseInt(dataBuilder.substring(0, 2), 16);
			row.rowNo = FirmwareUtils.getInstance().getMSB(dataBuilder.substring(2, 6));
			row.dataLength = Integer.parseInt(dataBuilder.substring(6, 10), 16);
			row.rowCheckSum = Integer.parseInt(dataBuilder.substring(dataLine.length() - 3, dataLine.length() - 1), 16);
			String datacharacters = dataBuilder.substring(10, dataLine.length() - 2);
			data = new byte[row.dataLength];
			for (int i = 0, j = 0; i < row.dataLength; i++, j += 2) {
				data[i] = (byte) Integer.parseInt(datacharacters.substring(j, j + 2), 16);
			}
			row.data = data;
			rows.add(row);
		}
	}

}
