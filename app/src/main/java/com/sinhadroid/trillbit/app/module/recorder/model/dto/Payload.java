package com.sinhadroid.trillbit.app.module.recorder.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class Payload{

	@JsonProperty("filename")
	private String filename;

	@JsonProperty("created")
	private String created;

	@JsonProperty("id")
	private int id;

	@JsonProperty("user")
	private String user;

	public void setFilename(String filename){
		this.filename = filename;
	}

	public String getFilename(){
		return filename;
	}

	public void setCreated(String created){
		this.created = created;
	}

	public String getCreated(){
		return created;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setUser(String user){
		this.user = user;
	}

	public String getUser(){
		return user;
	}

	@Override
 	public String toString(){
		return 
			"Payload{" + 
			"filename = '" + filename + '\'' + 
			",created = '" + created + '\'' + 
			",id = '" + id + '\'' + 
			",user = '" + user + '\'' + 
			"}";
		}
}