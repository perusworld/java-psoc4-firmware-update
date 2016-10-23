package com.yosanai.psoc4.firmware;

public class FirmwareUtils {

	private static final FirmwareUtils INSTANCE = new FirmwareUtils();

	public static FirmwareUtils getInstance() {
		return INSTANCE;
	}

	public String getMSB(String string) {
		StringBuilder msbString = new StringBuilder();

		for (int i = string.length(); i > 0; i -= 2) {
			String str = string.substring(i - 2, i);
			msbString.append(str);
		}
		return msbString.toString();
	}

	public String ByteArraytoHex(byte[] bytes) {
		if (bytes != null) {
			StringBuilder sb = new StringBuilder();
			for (byte b : bytes) {
				sb.append(String.format("%02X ", b));
			}
			return sb.toString().trim();
		}
		return "";
	}

	public int swap(int value) {
		int b1 = (value >> 0) & 0xff;
		int b2 = (value >> 8) & 0xff;
		int b3 = (value >> 16) & 0xff;
		int b4 = (value >> 24) & 0xff;

		return b1 << 24 | b2 << 16 | b3 << 8 | b4 << 0;
	}

	public int calculateCheckSumVerifyRow(int datalen, byte[] data) {
		int checkSum = 0;
		while (datalen-- > 0) {
			/**
			 * AND each value with 0xFF to remove the negative value for
			 * summation
			 */
			checkSum += (data[datalen] & 0xFF);
		}
		return checkSum;
	}
}
