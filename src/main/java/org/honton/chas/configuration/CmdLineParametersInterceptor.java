package org.honton.chas.configuration;

import javax.annotation.PostConstruct;
import javax.annotation.Priority;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import com.beust.jcommander.JCommander;

@Priority(Interceptor.Priority.LIBRARY_BEFORE)
@CmdLineParameters @Interceptor
public class CmdLineParametersInterceptor {

    static private String[] arguments;

    public static void setCmdLineArguments(String... arguments) {
        CmdLineParametersInterceptor.arguments = arguments;
    }

    /**
     * After an instance annotated with {@link CmdLineParameters} is
     * constructed, inject any attributes with command line arguments.
     * 
     * @param ctx
     *            The invocation context.
     */
    @PostConstruct
    public void injectCmdLineParameters(InvocationContext ctx) {
        Object target = ctx.getTarget();
        JCommander cmd = new JCommander(target);
        cmd.setAcceptUnknownOptions(true);
        cmd.parse(arguments);
    }
}
