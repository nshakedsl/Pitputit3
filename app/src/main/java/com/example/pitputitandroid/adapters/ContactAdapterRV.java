package com.example.pitputitandroid.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pitputitandroid.ChatsActivity;
import com.example.pitputitandroid.R;
import com.example.pitputitandroid.entities.Chat;
import com.example.pitputitandroid.entities.Contact;
import com.example.pitputitandroid.entities.Message;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;
import java.util.Objects;

public class ContactAdapterRV extends RecyclerView.Adapter<ContactAdapterRV.ContactViewHolder> {


    class ContactViewHolder extends RecyclerView.ViewHolder {
        private final ImageView imageView;
        private final TextView nameTextView;
        private final TextView lastMessageTextView;
        private final TextView dateTextView;



//        ImageView imageView = listItemView.findViewById(R.id.imageUser);
//        TextView nameTextView = listItemView.findViewById(R.id.textViewContactName);
//        TextView lastMessageTextView = listItemView.findViewById(R.id.textViewLastMessage);
//        TextView dateTextView = listItemView.findViewById(R.id.textViewDate);
//
//        nameTextView.setText(currentContact.getUsername());
//        lastMessageTextView.setText(currentContact.getLastMessageContent());
//        dateTextView.setText(currentContact.getCreatedLastMessage());

        private ContactViewHolder(View itemView, int meOther) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageUser);
            nameTextView = itemView.findViewById(R.id.textViewContactName);
            lastMessageTextView = itemView.findViewById(R.id.textViewLastMessage);
            dateTextView = itemView.findViewById(R.id.textViewDate);

        }


    }

        private final LayoutInflater mInFlater;
        private int count = 0;
        private List<Chat> contacts;

        public ContactAdapterRV(Context context) {
            mInFlater = LayoutInflater.from(context);
        }

        @NonNull
        @Override
        public ContactAdapterRV.ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            View itemView;

            itemView = mInFlater.inflate(R.layout.list_item_contact, parent, false);
            return new ContactAdapterRV.ContactViewHolder(itemView, 0);
        }

        @Override
        public void onBindViewHolder(@NonNull ContactAdapterRV.ContactViewHolder holder, int position) {
            if (contacts != null) {


                final Chat current = contacts.get(position);
                holder.nameTextView.setText(current.getUser().getUsername());
                holder.lastMessageTextView.setText(current.getLastMessage().getContent());
                // holder.imgProfile.setImageBitmap(current.getImgProfile());
                holder.imageView.setImageBitmap(current.getUser().getProfilePicBitmap());
                 Bitmap bitmap = current.getUser().getProfilePicBitmap(); // Assuming current.getImgProfile() returns a Bitmap object
                 holder.imageView.setImageBitmap(bitmap);
                holder.dateTextView.setText(current.getLastMessage().getCreated());



                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Get the clicked item position
                        int clickedPosition = holder.getAdapterPosition();

                        // Get the chat associated with the clicked item
                        Chat clickedContact = contacts.get(clickedPosition);
                        Chat clickedChat = clickedContact;

                        // Start the ChatActivity with the clicked chat
                        Intent intent = new Intent(v.getContext(), ChatsActivity.class);
                        intent.putExtra("chatId", clickedChat.getId()); // Pass any necessary data to the ChatActivity
                        intent.putExtra("userName", clickedChat.getUser().getUsername());
                        v.getContext().startActivity(intent);
                    }
                });

            }
        }

        public void setContacts(List<Chat> l) {
            contacts = l;
            notifyDataSetChanged();
        }

        public void AddContact(Chat c) {
            contacts.add(c);
            notifyDataSetChanged();
        }

        @Override
        public int getItemCount() {
            if (contacts != null)
                return contacts.size();
            else return 0;
        }

        public List<Chat> getContacts() {
            return contacts;
        }



    }





