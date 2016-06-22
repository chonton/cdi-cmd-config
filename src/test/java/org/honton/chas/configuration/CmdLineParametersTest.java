package org.honton.chas.configuration;

import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;
import org.junit.Assert;
import org.junit.Test;

public class CmdLineParametersTest {

	@Test
	public void testExtraInjections() {
	    CmdLineParametersInterceptor.setCmdLineArguments("-l", "10", "--string", "a string", "--ignored");
	    WeldContainer container = new Weld().initialize();
	    ConfigBean instance = container.select(ConfigBean.class).get();
	    Assert.assertEquals(10L, instance.getLongValue(), .1);   
        Assert.assertEquals("a string", instance.getString());
	}

    @Test
    public void testMissingInjections() {
        CmdLineParametersInterceptor.setCmdLineArguments("-l", "10");
        WeldContainer container = new Weld().initialize();
        ConfigBean instance = container.select(ConfigBean.class).get();
        Assert.assertEquals(10L, instance.getLongValue(), .1);   
        Assert.assertEquals("a string", instance.getString());
    }
}
