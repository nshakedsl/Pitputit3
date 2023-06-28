package com.example.pitputitandroid.adapters;

import static com.example.pitputitandroid.PitputitAndroid.context;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pitputitandroid.R;
import com.example.pitputitandroid.api.UserAPI;
import com.example.pitputitandroid.entities.Message;
import com.example.pitputitandroid.entities.User;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;
import java.util.Objects;

public class MessegesListAdapter extends RecyclerView.Adapter<MessegesListAdapter.MessegeViewHolder> {

    class MessegeViewHolder extends RecyclerView.ViewHolder {
        private final TextView messegeContent;
        private final TextView messegeTime;
        private final TextView messegeContent1;
        private final TextView messegeTime1;
        private final RoundedImageView imgProfile;

        private MessegeViewHolder(View itemView, int meOther) {
            super(itemView);
            messegeContent = itemView.findViewById(R.id.ContentTextView);
            messegeTime = itemView.findViewById(R.id.DateTextView);
            messegeContent1 = itemView.findViewById(R.id.ContentTextView1);
            messegeTime1 = itemView.findViewById(R.id.DateTextView1);
            imgProfile = itemView.findViewById(R.id.imageUser1);
        }
    }

    private final LayoutInflater mInFlater;
    private int count = 0;
    private List<Message> messeges;

    public MessegesListAdapter(Context context) {
        mInFlater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public MessegeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView;

        itemView = mInFlater.inflate(R.layout.messege_shared_layout, parent, false);
        return new MessegeViewHolder(itemView, 0);


    }

    @Override
    public void onBindViewHolder(@NonNull MessegeViewHolder holder, int position) {
        if (messeges != null) {
            final Message current = messeges.get(position);
            holder.messegeContent.setText(current.getContent());
            holder.messegeTime.setText(current.getTime());
            try {
                holder.imgProfile.setImageBitmap(current.getSender().getProfilePicBitmap());
            } catch (Exception e) {
                //illegal image give, give default image
                int width = 100; // desired width of the bitmap
                int height = 100; // desired height of the bitmap
                Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
                holder.imgProfile.setImageBitmap(bitmap);
            }
            holder.messegeContent1.setText(current.getContent());
            holder.messegeTime1.setText(current.getTime());
            UserAPI userAPI = new UserAPI(context);
            String username = userAPI.getUsername();
            if (current.getUserName().equals(username)) {
                holder.messegeContent.setVisibility(View.GONE);
                holder.messegeTime.setVisibility(View.GONE);
                holder.imgProfile.setVisibility(View.GONE);
                holder.messegeTime1.setVisibility(View.VISIBLE);
                holder.messegeContent1.setVisibility(View.VISIBLE);
            } else {
                holder.messegeContent.setVisibility(View.VISIBLE);
                holder.messegeTime.setVisibility(View.VISIBLE);
                holder.imgProfile.setVisibility(View.VISIBLE);
                holder.messegeTime1.setVisibility(View.GONE);
                holder.messegeContent1.setVisibility(View.GONE);
            }
            if (Objects.equals(current.getContent(), username)) {
                count = 0;
            } else count++;
        }
    }

    public void setMesseges(List<Message> m) {
        messeges = m;
        notifyDataSetChanged();
    }

    public void AddMessage(Message m) {
        messeges.add(m);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (messeges != null)
            return messeges.size();
        else return 0;
    }

    public List<Message> getMesseges() {
        return messeges;
    }
}
/*
//
//             int position = -1; // Initialize position as -1 (invalid value)
//
//             // Find the position of the current message based on its viewType
//             for (int i = 0; i < messeges.size(); i++) {
//                 if (getItemViewType(i) == viewType) {
//                     position = i;
//                     break;
//                 }
//             }
//
//             if (position != -1) {
//                 Message currentMessage = messeges.get(position); // Retrieve the current message based on the position
//
//                 if (currentMessage.getUserName() != null && currentMessage.getUserName().equals("aa")) {
//                     itemView = mInFlater.inflate(R.layout.messege_layout, parent, false);
//                     return new MessegeViewHolder(itemView, 1);
//                 }
//             }


//             if(count%2==0){
//                 itemView = mInFlater.inflate(R.layout.messege_layout, parent, false);
//                 return new MessegeViewHolder(itemView, 1);
//             }else {
//                 itemView = mInFlater.inflate(R.layout.messege_other_layout, parent, false);
//                 return new MessegeViewHolder(itemView, 0);
//             }

//             Message currentMessage = messeges.get(count);
//
//             if(currentMessage.getUserName().equals("aa")){
//                 itemView = mInFlater.inflate(R.layout.messege_layout, parent, false);
//                 return new MessegeViewHolder(itemView, 1);
//             }else {
//                 itemView = mInFlater.inflate(R.layout.messege_other_layout, parent, false);
//                 return new MessegeViewHolder(itemView, 0);
//             }

//
//             Message currentMessage = messeges.get(viewType);
//                 if(currentMessage.getUserName()!=null){
//                     if (currentMessage.getUserName().equals("aa")) {
//                         itemView = mInFlater.inflate(R.layout.messege_layout, parent, false);
//                         return new MessegeViewHolder(itemView, 1);
//                     }
//                 }


//             itemView = mInFlater.inflate(R.layout.messege_other_layout, parent, false);
//             return new MessegeViewHolder(itemView, 0);
 */