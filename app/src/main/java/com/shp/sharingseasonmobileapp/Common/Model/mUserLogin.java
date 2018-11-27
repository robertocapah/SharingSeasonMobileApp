package com.shp.sharingseasonmobileapp.Common.Model;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * Created by Dewi Oktaviani on 10/10/2018.
 */

@DatabaseTable
public class mUserLogin implements Serializable {
    @DatabaseField(id = true)
    private String txtGuID;
    @DatabaseField
    private int IntUserID;
    @DatabaseField(columnName = "TxtUserName")
    private String TxtUserName;
    @DatabaseField(columnName = "TxtNick")
    private String TxtNick;
    @DatabaseField(columnName = "TxtEmpID")
    private String TxtEmpID;
    @DatabaseField(columnName = "TxtEmail")
    private String TxtEmail;
    @DatabaseField(columnName = "intRoleID")
    private int intRoleID;
    @DatabaseField(columnName = "txtRoleName")
    private String txtRoleName;
    @DatabaseField(columnName = "IntDepartmentID")
    private int IntDepartmentID;
    @DatabaseField(columnName = "IntLOBID")
    private int IntLOBID;
    @DatabaseField(columnName = "TxtCompanyCode")
    private String TxtCompanyCode;
    @DatabaseField
    private String dtLogOut;
    @DatabaseField
    private String dtLogIn;
    @DatabaseField(dataType = DataType.BYTE_ARRAY)
    private byte[] BlobImg;
    @DatabaseField
    private String txtFileName;


    public int getIntUserID() {
        return IntUserID;
    }

    public void setIntUserID(int intUserID) {
        IntUserID = intUserID;
    }

    public String getTxtUserName() {
        return TxtUserName;
    }

    public void setTxtUserName(String txtUserName) {
        TxtUserName = txtUserName;
    }

    public String getTxtNick() {
        return TxtNick;
    }

    public void setTxtNick(String txtNick) {
        TxtNick = txtNick;
    }

    public String getTxtEmpID() {
        return TxtEmpID;
    }

    public void setTxtEmpID(String txtEmpID) {
        TxtEmpID = txtEmpID;
    }

    public String getTxtEmail() {
        return TxtEmail;
    }

    public void setTxtEmail(String txtEmail) {
        TxtEmail = txtEmail;
    }

    public int getIntDepartmentID() {
        return IntDepartmentID;
    }

    public void setIntDepartmentID(int intDepartmentID) {
        IntDepartmentID = intDepartmentID;
    }

    public int getIntLOBID() {
        return IntLOBID;
    }

    public void setIntLOBID(int intLOBID) {
        IntLOBID = intLOBID;
    }

    public String getTxtCompanyCode() {
        return TxtCompanyCode;
    }

    public void setTxtCompanyCode(String txtCompanyCode) {
        TxtCompanyCode = txtCompanyCode;
    }

    public int getIntRoleID() {
        return intRoleID;
    }

    public void setIntRoleID(int intRoleID) {
        this.intRoleID = intRoleID;
    }

    public String getTxtRoleName() {
        return txtRoleName;
    }

    public void setTxtRoleName(String txtRoleName) {
        this.txtRoleName = txtRoleName;
    }

    public String getDtLogOut() {
        return dtLogOut;
    }

    public void setDtLogOut(String dtLogOut) {
        this.dtLogOut = dtLogOut;
    }

    public String getDtLogIn() {
        return dtLogIn;
    }

    public void setDtLogIn(String dtLogIn) {
        this.dtLogIn = dtLogIn;
    }

    public String getTxtGuID() {
        return txtGuID;
    }

    public void setTxtGuID(String txtGuID) {
        this.txtGuID = txtGuID;
    }

    public byte[] getBlobImg() {
        return BlobImg;
    }

    public void setBlobImg(byte[] blobImg) {
        BlobImg = blobImg;
    }

    public String getTxtFileName() {
        return txtFileName;
    }

    public void setTxtFileName(String txtFileName) {
        this.txtFileName = txtFileName;
    }
}
