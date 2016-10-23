package com.yosanai.psoc4.firmware;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

@RunWith(BlockJUnit4ClassRunner.class)
public class FirmwareDataTest {

	@Test
	public void checkRead() throws Exception {
		FirmwareData data = new FirmwareData();
		data.parse(getClass().getResourceAsStream("/HelloApp.cyacd"));
		Assert.assertEquals("00", data.getCheckSumType());
		Assert.assertEquals("9E11340E", data.getSiliconId());
		Assert.assertEquals("00", data.getSiliconRev());
		Assert.assertEquals(22, data.getRows().size());
	}

}
