package com.harmony.harmony.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.harmony.harmony.ComposerListFragment;
import com.harmony.harmony.R;
import com.harmony.harmony.ComposerListFragment;
import com.harmony.harmony.models.Composer;


import java.util.ArrayList;


public class ComposerAdapter extends RecyclerView.Adapter<ComposerAdapter.ComposerHolder> {

    private ArrayList<Composer> mData;
    private ComposerListFragment mACtivity;
    private OnItemClickListener mlistener;
    public interface OnItemClickListener {
        void onItemClick(int position, long id);
    }
    public void setOnItemClickListener(OnItemClickListener listener) { mlistener=listener; }

    public ComposerAdapter(ArrayList<Composer> data, ComposerListFragment activity) {
        this.mData = data;
        this.mACtivity = activity;
    }

    @Override
    public ComposerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_composer, parent, false);


        return new ComposerHolder(view, mlistener);
    }



    @Override
    public void onBindViewHolder(ComposerHolder holder, int position) {
        Composer composer = mData.get(position);

        holder.setName(composer.getComposerName());

        Glide.with(mACtivity)
                .load(composer.getComposerImageUrl())
                .into(holder.composerImageView);

    }

    @Override
    public int getItemCount() {
        if (mData == null)
            return 0;
        return mData.size();
    }

    public class ComposerHolder extends RecyclerView.ViewHolder {

        ImageView composerImageView;
        TextView composerNameTextView;


        public ComposerHolder(View itemView, final OnItemClickListener listener) {
            super(itemView);

            composerImageView = (ImageView) itemView.findViewById(R.id.composer_img_id);
            composerNameTextView = (TextView) itemView.findViewById(R.id.composer_name_id);

            composerImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(listener !=null) {
                        int position = getAdapterPosition();
                        long id = getItemId();
                        if(position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position, id);
                        }
                    }
                }
            });

        }

        public void setName(String name) {
            composerNameTextView.setText(name);
        }


    }
}