package com.example.ribath.nupuit;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by Ribath on 3/31/2017.
 */

@DatabaseTable(tableName = "ContactList")
public class ContactClass {

    @DatabaseField()
    private String contact_name;

    @DatabaseField()
    private String contact_number;

    public ContactClass(String contact_name, String contact_number) {
        this.contact_name = contact_name;
        this.contact_number = contact_number;
    }

    public String getContact_name() {
        return contact_name;
    }

    public String getContact_number() {
        return contact_number;
    }

    public ContactClass() {
    }
}
