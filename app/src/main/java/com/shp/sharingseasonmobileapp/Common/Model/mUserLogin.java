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


}
