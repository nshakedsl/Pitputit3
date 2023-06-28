package com.example.pitputitandroid.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pitputitandroid.R;
import com.example.pitputitandroid.entities.Chat;

import java.util.List;

public class ChatListAdapter extends RecyclerView.Adapter<ChatListAdapter.ChatViewHolder> {
    private final LayoutInflater inflater;
    private List<Chat> chats;

    class ChatViewHolder extends RecyclerView.ViewHolder {
        private final ImageView imageView;
        private final TextView nameTextView;
        private TextView lastMessageTextView;
        private TextView dateTextView;

        ChatViewHolder(View view) {
            super(view);
            imageView = itemView.findViewById(R.id.imageUser);
            nameTextView = itemView.findViewById(R.id.textViewContactName);
            lastMessageTextView = itemView.findViewById(R.id.textViewLastMessage);
            dateTextView = itemView.findViewById(R.id.textViewDate);
        }
    }

    @NonNull
    @Override
    public ChatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list_item_contact, parent, false);
        return new ChatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChatViewHolder holder, int position) {
        if (chats != null) {
            final Chat current = chats.get(position);
            if (current != null) {
                holder.nameTextView.setText(current.getUser().getUsername());
                if (current.getLastMessage() != null) {
                    holder.lastMessageTextView.setText(current.getLastMessage().getContent());
                    holder.dateTextView.setText(current.getLastMessage().getCreated());
                } else {
                    holder.lastMessageTextView.setText("");
                    holder.dateTextView.setText("");
                }
                try {
                    holder.imageView.setImageBitmap(current.getUser().getProfilePicBitmap());
                } catch (Exception e) {
                    //illegal image give, give default image
                    int width = 100; // desired width of the bitmap
                    int height = 100; // desired height of the bitmap
                    Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
                    holder.imageView.setImageBitmap(bitmap);
                }
            }
        }
    }

    public void setChats(List<Chat> chats) {
        this.chats = chats;
        notifyDataSetChanged();
    }

    public void AddContact(Chat c) {
        chats.add(c);
        notifyDataSetChanged();
    }

    public ChatListAdapter(Context context) {
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getItemCount() {
        if (chats != null)
            return chats.size();
        else return 0;
    }

    public List<Chat> getContacts() {
        return chats;
    }

}