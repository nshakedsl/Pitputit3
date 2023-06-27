package com.example.pitputitandroid.adapters;

//import static androidx.appcompat.graphics.drawable.DrawableContainerCompat.Api21Impl.getResources;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.BaseAdapter;

import androidx.annotation.ContentView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pitputitandroid.R;
import com.example.pitputitandroid.Utils;
import com.example.pitputitandroid.entities.Contact;
import com.example.pitputitandroid.entities.Message;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.ArrayList;
import java.util.List;

public class ContactListAdapter extends ArrayAdapter<Contact> {


public ContactListAdapter (Context context, ArrayList<Contact> contactList){
    super(context, R.layout.list_item_contact, contactList);

}

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Contact contact =getItem(position);
        if(convertView == null) {
        convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_contact, parent, false);
        }


        ImageView imageUser = convertView.findViewById(R.id.imageUser);
        TextView textViewContactName = convertView.findViewById(R.id.textViewContactName);
        TextView textViewLastMessage = convertView.findViewById(R.id.textViewLastMessage);
        TextView textViewDate = convertView.findViewById(R.id.textViewDate);


        //int resourceId = R.drawable.user;

        imageUser.setImageResource(R.drawable.user);
        textViewContactName.setText(contact.getUsername());
        textViewLastMessage.setText(contact.getLastMessageContent());
        textViewDate.setText(contact.getCreatedLastMessage());


        return super.getView(position, convertView, parent);


    }
}



