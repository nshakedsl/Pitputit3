package com.example.pitputitandroid.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.pitputitandroid.R;
import com.example.pitputitandroid.entities.Messege;
import com.example.pitputitandroid.viewmodels.MessegesViewModel;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;

public class MessegesListAdapter extends RecyclerView.Adapter<MessegesListAdapter.MessegeViewHolder> {

         class MessegeViewHolder extends RecyclerView.ViewHolder {
             private final TextView messegeContent;
             private final TextView messegeTime;

             private final RoundedImageView imgProfile;

             private MessegeViewHolder(View itemView){
                 super(itemView);
                 messegeContent = itemView.findViewById(R.id.ContentTextView);
                 messegeTime = itemView.findViewById(R.id.DateTextView);
                 imgProfile = itemView.findViewById(R.id.imageUser);
             }
         }
         private final LayoutInflater mInFlater;

        private List<Messege> messeges;

        public MessegesListAdapter(Context context){ mInFlater = LayoutInflater.from(context);}

         @Override
        public MessegeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
             View itemView = mInFlater.inflate(R.layout.messege_other_layout, parent, false);
             return new MessegeViewHolder(itemView);
         }

         @Override
         public void onBindViewHolder(MessegeViewHolder holder, int position) {
            if(messeges != null)
            {
                final Messege current = messeges.get(position);
                holder.messegeContent.setText(current.getContent());
                holder.messegeTime.setText(current.getTime());
               // holder.imgProfile.setImageBitmap(current.getImgProfile());
                Bitmap bitmap = current.getImgProfile(); // Assuming current.getImgProfile() returns a Bitmap object
                holder.imgProfile.setImageBitmap(bitmap);


            }
         }

         public void setMesseges(List<Messege> m){
            messeges = m;
            notifyDataSetChanged();
         }

         @Override
        public int getItemCount(){
            if (messeges!=null)
                return messeges.size();
            else return 0;
         }

         public List<Messege> getMesseges(){ return messeges; }

}



