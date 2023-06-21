package com.example.pitputitandroid.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.pitputitandroid.R;
import com.example.pitputitandroid.entities.Messege;
import com.example.pitputitandroid.viewmodels.MessegesViewModel;

import java.util.List;

public class MessegesListAdapter extends RecyclerView.Adapter<MessegesListAdapter.MessegeViewHolder> {

         class MessegeViewHolder extends RecyclerView.ViewHolder {
             private final TextView messegeContent;
             private final TextView messegeDisplayName;
             private final TextView messegeUserName;
             private final TextView messegeTime;

             private MessegeViewHolder(View itemView){
                 super(itemView);
                 messegeContent = itemView.findViewById(R.id.messegeContent);
                 messegeDisplayName = itemView.findViewById(R.id.messegeDisplayName);
                 messegeUserName = itemView.findViewById(R.id.messegeUserName);
                 messegeTime = itemView.findViewById(R.id.messegeTime);
             }
         }
         private final LayoutInflater mInFlater;

        private List<Messege> messeges;

        public MessegesListAdapter(Context context){ mInFlater = LayoutInflater.from(context);}

         @Override
        public MessegeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
             View itemView = mInFlater.inflate(R.layout.messege_item, parent, false);
             return new MessegeViewHolder(itemView);
         }

         @Override
         public void onBindViewHolder(MessegeViewHolder holder, int position) {
            if(messeges != null)
            {
                final Messege current = messeges.get(position);
                holder.messegeContent.setText(current.getContent());
                holder.messegeTime.setText(current.getTime());
                holder.messegeUserName.setText(current.getUserName());

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



