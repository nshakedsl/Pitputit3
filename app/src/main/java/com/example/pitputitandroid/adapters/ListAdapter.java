package com.example.pitputitandroid.adapters;

import android.content.Context;
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

public class ListAdapter extends ArrayAdapter<Contact> {

    private Context context;
    private ArrayList<Contact> contacts;

    public ListAdapter(Context context, ArrayList<Contact> contacts) {
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
        TextView textName = listItemView.findViewById(R.id.textViewContactName);
        TextView textMessage = listItemView.findViewById(R.id.textViewLastMessage);
        TextView textDate = listItemView.findViewById(R.id.textViewDate);



        // Set the contact information to the views
        textName.setText(currentContact.getUsername());
        textMessage.setText(currentContact.getLastMessageContent());
        textDate.setText(currentContact.getCreatedLastMessage());

        return listItemView;
    }
}
