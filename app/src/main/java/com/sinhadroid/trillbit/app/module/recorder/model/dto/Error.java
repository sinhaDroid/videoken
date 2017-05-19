package com.sinhadroid.trillbit.app.module.recorder.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class Error{

	@JsonProperty("code")
	private int code;

	public void setCode(int code){
		this.code = code;
	}

	public int getCode(){
		return code;
	}

	@Override
 	public String toString(){
		return 
			"Error{" + 
			"code = '" + code + '\'' + 
			"}";
		}
}