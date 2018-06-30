package com.harmony.harmony.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.harmony.harmony.R;
import com.harmony.harmony.VideoListFragment;
import com.harmony.harmony.models.Video;

import java.util.ArrayList;



public class VideoAdapter extends RecyclerView.Adapter<com.harmony.harmony.adapters.VideoAdapter.VideoHolder> {

    private ArrayList<Video> mData;
    private VideoListFragment mACtivity;
    private OnItemClickListener mlistener;
    public interface OnItemClickListener {
        void onItemClick(int position, long id);
    }
    public void setOnItemClickListener(OnItemClickListener listener) { mlistener=listener; }

    public VideoAdapter(ArrayList<Video> data, VideoListFragment activity) {
        this.mData = data;
        this.mACtivity = activity;
    }

    @Override
    public VideoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_video, parent, false);


        return new VideoHolder(view, mlistener);
    }



    @Override
    public void onBindViewHolder(VideoHolder holder, int position) {
        Video video = mData.get(position);

        holder.setName(video.getTitle());

        Glide.with(mACtivity)
                .load(video.getImageurl())
                .into(holder.videoImageView);

    }

    @Override
    public int getItemCount() {
        if (mData == null)
            return 0;
        return mData.size();
    }

    public class VideoHolder extends RecyclerView.ViewHolder {

        ImageView videoImageView;
        TextView videoTitleTextView;


        public VideoHolder(View itemView, final OnItemClickListener listener) {
            super(itemView);

            videoImageView = (ImageView) itemView.findViewById(R.id.imageview_video);
            videoTitleTextView = (TextView) itemView.findViewById(R.id.textview_video_title);

            videoImageView.setOnClickListener(new View.OnClickListener() {
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
            videoTitleTextView.setText(name);
        }


    }
}