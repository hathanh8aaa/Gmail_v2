package com.example.gmail;

import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class GmailAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<Gmail> allGmails;
    List<Gmail> displayGmail;
    List<Gmail> searchResultGmail;
    boolean isShowingFavourite;
    boolean isShowingSearchResult;

    public GmailAdapter(List<Gmail> allGmails) {
        this.allGmails = allGmails;
        displayGmail = new ArrayList<>();
        displayGmail.addAll(allGmails);
        searchResultGmail = new ArrayList<>();
        isShowingFavourite = false;
        isShowingSearchResult = false;
    }

    public void search(String keyword){
        searchResultGmail.clear();
        displayGmail.clear();
        isShowingFavourite = false;
        isShowingSearchResult = true;
        for(Gmail g : allGmails){
            if(g.getName().contains(keyword) || g.getSubject().contains(keyword) || g.getContent().contains(keyword)){
                displayGmail.add(g);
                searchResultGmail.add(g);
            }
        }
        notifyDataSetChanged();
    }

    public void showFavourite(){
        displayGmail.clear();
        for(Gmail g:allGmails){
            if(g.isFavourite()) displayGmail.add(g);
        }
        isShowingFavourite = true;
        notifyDataSetChanged();
    }

    public void showAll(){
        isShowingFavourite = false;
        displayGmail.clear();
        if(isShowingSearchResult) displayGmail.addAll(searchResultGmail);
        else displayGmail.addAll(allGmails);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.gmail_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Gmail gmail = displayGmail.get(position);
        ViewHolder viewHolder = (ViewHolder) holder;

        viewHolder.txtViewFirstCharacter.setText(gmail.getName().substring(0,1));
        Drawable background = viewHolder.txtViewFirstCharacter.getBackground();
        background.setColorFilter(new PorterDuffColorFilter(gmail.getColor(), PorterDuff.Mode.SRC_ATOP));
        viewHolder.txtViewName.setText(gmail.getName());
        viewHolder.txtViewSubject.setText(gmail.getSubject());
        viewHolder.txtViewContent.setText(gmail.getContent());
        viewHolder.txtViewTime.setText(gmail.getTime());
        if(gmail.isFavourite()) viewHolder.imgViewStar.setImageResource(R.drawable.ic_star_favourite);
        else viewHolder.imgViewStar.setImageResource(R.drawable.ic_star_border);
   }

    @Override
    public int getItemCount() {
        return displayGmail.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtViewName, txtViewContent, txtViewTime, txtViewSubject, txtViewFirstCharacter;
        ImageView imgViewStar;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtViewFirstCharacter = itemView.findViewById(R.id.first_character);
            txtViewTime = itemView.findViewById(R.id.txt_time);
            txtViewSubject = itemView.findViewById(R.id.subject);
            txtViewName = itemView.findViewById(R.id.name);
            txtViewContent = itemView.findViewById(R.id.content_mail);
            imgViewStar = itemView.findViewById(R.id.star_favourite);

            imgViewStar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    boolean isFavourite = displayGmail.get(getAdapterPosition()).isFavourite();
                    displayGmail.get(getAdapterPosition()).setFavourite(!isFavourite);
                    if(isShowingFavourite) displayGmail.remove(getAdapterPosition());
                    notifyDataSetChanged();
                }
            });
        }
    }

}
