package tomerbu.edu.contactsproviderdemo.contacts;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.widget.Toast;

import java.util.ArrayList;

import tomerbu.edu.contactsproviderdemo.models.Contact;

public class MyContactsProvider {

    private final Context context;

    public ArrayList<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(ArrayList<Contact> contacts) {
        this.contacts = contacts;
    }

    private ArrayList<Contact> contacts = new ArrayList<>();

    public MyContactsProvider(Context context) {
        this.context = context;
        this.contacts = readContacts();
    }

    private ContentResolver getContentResolver() {
        return context.getContentResolver();
    }



    //Method the requires permission:
    private ArrayList<Contact> readContacts() {

        ArrayList<Contact> contacts = new ArrayList<>();

        Uri contactsURI = ContactsContract.Contacts.CONTENT_URI;

        Cursor cursor = getContentResolver().query(contactsURI, null, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                String id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
                String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                ArrayList<String> phones = getPhones(id);
                ArrayList<String> emails = getEmails(id);
                Contact c = new Contact(id, name, emails, phones);
                contacts.add(c);;
            } while (cursor.moveToNext());
        }

        return contacts;
    }

    private ArrayList<String> getPhones(String id) {
        ArrayList<String> phones = new ArrayList<>();

        Uri phoneURI = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;

        Cursor cursor = getContentResolver().query(phoneURI, null,
                ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=?",
                new String[]{id}, null
        );

        if (cursor != null && cursor.moveToFirst()) {
            do {
                String number = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                phones.add(number);

            } while (cursor.moveToNext());
        }
        return phones;
    }


    private ArrayList<String> getEmails(String id) {
        ArrayList<String> emails = new ArrayList<>();

        Uri emailUri = ContactsContract.CommonDataKinds.Email.CONTENT_URI;


        Cursor cursor = getContentResolver().query(emailUri, null,
                ContactsContract.CommonDataKinds.Email.CONTACT_ID + "=?",
                new String[]{id}, null
        );

        if (cursor != null && cursor.moveToFirst()) {
            do {
                String email = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Email.DATA));
                emails.add(email);

            } while (cursor.moveToNext());
        }
        return emails;
    }


}
