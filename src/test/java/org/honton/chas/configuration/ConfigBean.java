package org.honton.chas.configuration;

import com.beust.jcommander.Parameter;

import lombok.Data;

@Data
@CmdLineParameters
public class ConfigBean {
    @Parameter(names = {"-s", "--string"}, description = "A string parameter")
	private String string = "some default";
    
    @Parameter(names = {"-l", "--long"}, description = "A long parameter", required = true)
	private Long longValue;
}
