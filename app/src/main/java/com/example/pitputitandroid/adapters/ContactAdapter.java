package com.example.pitputitandroid.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.pitputitandroid.R;
import com.example.pitputitandroid.entities.Contact;

import java.util.ArrayList;

public class ContactAdapter extends ArrayAdapter<Contact> {

    private Context context;
    private ArrayList<Contact> contacts;

    public ContactAdapter(Context context, ArrayList<Contact> contacts) {
        super(context, 0, contacts);
        this.context = context;
        this.contacts = contacts;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(context).inflate(R.layout.list_item_contact, parent, false);
        }

        Contact currentContact = contacts.get(position);

        // Find the views within the list_item_contact.xml layout
        ImageView imageView = listItemView.findViewById(R.id.imageUser);
        TextView nameTextView = listItemView.findViewById(R.id.textViewContactName);
        TextView lastMessageTextView = listItemView.findViewById(R.id.textViewLastMessage);
        TextView dateTextView = listItemView.findViewById(R.id.textViewDate);

        // Set the contact information to the views
        //imageView.setImageBitmap(currentContact.getPicProfileBit());
        nameTextView.setText(currentContact.getUsername());
        lastMessageTextView.setText(currentContact.getLastMessageContent());
        dateTextView.setText(currentContact.getCreatedLastMessage());



        return listItemView;
    }
}
