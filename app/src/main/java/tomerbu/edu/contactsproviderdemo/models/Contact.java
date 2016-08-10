package tomerbu.edu.contactsproviderdemo.models;


import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Contact implements Parcelable {

    private String id;
    private String name;
    private ArrayList<String> emails;
    private ArrayList<String> phones;

    //Constructor:
    public Contact(String id, String name, ArrayList<String> emails, ArrayList<String> phones) {
        this.id = id;
        this.name = name;
        this.emails = emails;
        this.phones = phones;
    }

    //Getters and Setters
    public void setId(String id) {
        this.id = id;
    }

    public ArrayList<String> getEmails() {
        return emails;
    }

    public void setEmails(ArrayList<String> emails) {
        this.emails = emails;
    }

    public ArrayList<String> getPhones() {
        return phones;
    }

    public void setPhones(ArrayList<String> phones) {
        this.phones = phones;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.name);
        dest.writeStringList(this.emails);
        dest.writeStringList(this.phones);
    }

    protected Contact(Parcel in) {
        this.id = in.readString();
        this.name = in.readString();
        this.emails = in.createStringArrayList();
        this.phones = in.createStringArrayList();
    }

    public static final Parcelable.Creator<Contact> CREATOR = new Parcelable.Creator<Contact>() {
        @Override
        public Contact createFromParcel(Parcel source) {
            return new Contact(source);
        }

        @Override
        public Contact[] newArray(int size) {
            return new Contact[size];
        }
    };
}