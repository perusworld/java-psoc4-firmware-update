package com.yosanai.psoc4.firmware;

import java.util.ArrayList;
import java.util.List;

public class FirmwareUpdater {

	public static final byte COMMAND_START_BYTE = 0x01;
	public static final byte COMMAND_END_BYTE = 0x17;

	public static final byte VERIFY_CHECK_SUM = 0x31;
	public static final byte GET_FLASH_SIZE = 0x32;
	public static final byte ENTER_BOOTLOADER = 0x38;
	public static final byte SEND_DATA = 0x37;
	public static final byte PROGRAM_ROW = 0x39;
	public static final byte VERIFY_ROW = 0x3A;
	public static final byte EXIT_BOOTLOADER = 0x3B;

	private static final int RADIX = 16;
	private static final int ADDITIVE_OP = 8;

	protected FirmwareComm comm;
	protected FirmwareUpdateProgress progress;
	protected FirmwareData firmwareData;
	protected Checksum checksum = new Checksum();

	public FirmwareUpdater(FirmwareComm comm, FirmwareUpdateProgress progress) {
		super();
		this.comm = comm;
		this.progress = progress;
	}

	public void startUpdate(char[] securityKey, FirmwareData firmwareData) {
		this.firmwareData = firmwareData;
		if (null == securityKey) {
			comm.write(createCommand(ENTER_BOOTLOADER, 0, null));
		} else {

		}
	}

	public void handleData(byte[] data) {

	}

	byte[] createCommand(byte command, int dataLength, FlashRow row) {
		byte[] ret = null;
		List<Byte> bytes = new ArrayList<Byte>();
		bytes.add(COMMAND_START_BYTE);
		bytes.add(command);
		bytes.add((byte) dataLength);
		bytes.add((byte) (dataLength >> ADDITIVE_OP));
		String checkSum = Integer
				.toHexString(checksum.calculate(Integer.parseInt(firmwareData.getCheckSumType(), RADIX), bytes));
		long checksum = Long.parseLong(checkSum, RADIX);
		bytes.add((byte) checksum);
		bytes.add((byte) (checksum >> ADDITIVE_OP));
		bytes.add(COMMAND_END_BYTE);
		ret = new byte[bytes.size()];
		for (int index = 0; index < bytes.size(); index++) {
			ret[index] = bytes.get(index);
		}
		return ret;
	}

	void getFlashSize() {

	}

	void programRow(int index) {

	}

	void verifyRow(int index) {

	}

	void verifyChecksum() {

	}

	void exitBootLoader() {

	}

	void onEnterBootLoader() {

	}

	void onGetFlashSize() {

	}

	void onProgramRow() {

	}

	void onVerifyRow() {

	}

	void onVerifyChecksum() {

	}
}
