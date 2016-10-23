package com.yosanai.psoc4.firmware;

import java.util.Timer;
import java.util.TimerTask;

import org.apache.commons.codec.binary.Hex;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

@RunWith(BlockJUnit4ClassRunner.class)
public class FirmwareUpdaterTest {

	public static final String[] REQUESTS = { "01 38 00 00 C7 FF 17" };
	public static final String[] RESPONSES = { "01 00 08 00 9E 11 34 0E 00 32 01 01 D2 FE 17" };

	@Test
	public void checkUpdate() throws Exception {
		FirmwareData data = new FirmwareData();
		data.parse(getClass().getResourceAsStream("/HelloApp.cyacd"));
		FirmwareUpdater updater = new FirmwareUpdater(new FirmwareComm() {
			int index = 0;
			Timer timer = new Timer(true);

			@Override
			public void write(byte[] data) {
				System.out.println(Hex.encodeHexString(data));
				Assert.assertEquals(REQUESTS[index], FirmwareUtils.getInstance().ByteArraytoHex(data));
				timer.schedule(new TimerTask() {

					@Override
					public void run() {
						//updater.handleData();
					}
				}, 1000);
			}
		}, new FirmwareUpdateProgress() {
		});
		updater.startUpdate(null, data);
	}

}
