package org.honton.chas.configuration;

import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;
import org.junit.Assert;
import org.junit.Test;

import com.beust.jcommander.ParameterException;

public class CmdLineParametersTest {

    @Test
    public void testExtraInjections() {
        CmdLineParametersInterceptor.setCmdLineArguments("-l", "10", "--string", "a string", "--ignored");
        Weld weld = new Weld();
        try {
            WeldContainer container = weld.initialize();
            ConfigBean instance = container.select(ConfigBean.class).get();
            Assert.assertEquals(10L, instance.getLongValue(), .1);
            Assert.assertEquals("a string", instance.getString());
        } finally {
            weld.shutdown();
        }
    }

    @Test(expected = ParameterException.class)
    public void testMissingRequiredInjection() {
        CmdLineParametersInterceptor.setCmdLineArguments("-s", "b string");
        Weld weld = new Weld();
        try {
            WeldContainer container = weld.initialize();
            container.select(ConfigBean.class).get();
        } finally {
            weld.shutdown();
        }
    }

    @Test
    public void testMissingInjection() {
        CmdLineParametersInterceptor.setCmdLineArguments("-l", "40");
        Weld weld = new Weld();
        try {
            WeldContainer container = weld.initialize();
            ConfigBean instance = container.select(ConfigBean.class).get();
            Assert.assertEquals(40L, instance.getLongValue(), .1);
            Assert.assertEquals("some default", instance.getString());
        } finally {
            weld.shutdown();
        }
    }
}
