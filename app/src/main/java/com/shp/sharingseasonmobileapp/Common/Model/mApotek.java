package com.shp.sharingseasonmobileapp.Common.Model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * Created by ASUS on 11/27/2018.
 */
@DatabaseTable
public class mApotek implements Serializable {
    @DatabaseField(id = true)
    private String intApotekId;
    @DatabaseField
    private String txtApotekName;

    public String getIntApotekId() {
        return intApotekId;
    }

    public void setIntApotekId(String intApotekId) {
        this.intApotekId = intApotekId;
    }

    public String getTxtApotekName() {
        return txtApotekName;
    }

    public void setTxtApotekName(String txtApotekName) {
        this.txtApotekName = txtApotekName;
    }
}
