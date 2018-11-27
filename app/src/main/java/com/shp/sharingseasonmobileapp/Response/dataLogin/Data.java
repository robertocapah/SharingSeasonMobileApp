package com.shp.sharingseasonmobileapp.Response.dataLogin;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class Data{

	@SerializedName("txtUsername")
	private String txtUsername;

	@SerializedName("txtEmpId")
	private String txtEmpId;

	@SerializedName("txtEmail")
	private String txtEmail;

	@SerializedName("txtEmployeeName")
	private String txtEmployeeName;

	public void setTxtUsername(String txtUsername){
		this.txtUsername = txtUsername;
	}

	public String getTxtUsername(){
		return txtUsername;
	}

	public void setTxtEmpId(String txtEmpId){
		this.txtEmpId = txtEmpId;
	}

	public String getTxtEmpId(){
		return txtEmpId;
	}

	public void setTxtEmail(String txtEmail){
		this.txtEmail = txtEmail;
	}

	public String getTxtEmail(){
		return txtEmail;
	}

	public void setTxtEmployeeName(String txtEmployeeName){
		this.txtEmployeeName = txtEmployeeName;
	}

	public String getTxtEmployeeName(){
		return txtEmployeeName;
	}

	@Override
 	public String toString(){
		return 
			"Data{" + 
			"txtUsername = '" + txtUsername + '\'' + 
			",txtEmpId = '" + txtEmpId + '\'' + 
			",txtEmail = '" + txtEmail + '\'' + 
			",txtEmployeeName = '" + txtEmployeeName + '\'' + 
			"}";
		}
}