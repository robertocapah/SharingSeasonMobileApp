package com.shp.sharingseasonmobileapp.Common.Model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by ASUS on 11/27/2018.
 */
@DatabaseTable
public class tResep {
    @DatabaseField(generatedId = true)
    private int intResepId;
    @DatabaseField
    private String txtResep;

    public int getIntResepId() {
        return intResepId;
    }

    public void setIntResepId(int intResepId) {
        this.intResepId = intResepId;
    }

    public String getTxtResep() {
        return txtResep;
    }

    public void setTxtResep(String txtResep) {
        this.txtResep = txtResep;
    }
}
