package com.shp.sharingseasonmobileapp.Response.dataApotek;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class RecordsItem{

	@SerializedName("code")
	private String code;

	@SerializedName("name")
	private String name;

	@SerializedName("status")
	private String status;

	public void setCode(String code){
		this.code = code;
	}

	public String getCode(){
		return code;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}

	@Override
 	public String toString(){
		return 
			"RecordsItem{" + 
			"code = '" + code + '\'' + 
			",name = '" + name + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}