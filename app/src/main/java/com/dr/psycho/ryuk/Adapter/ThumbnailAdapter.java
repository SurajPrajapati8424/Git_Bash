package com.dr.psycho.ryuk.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.dr.psycho.ryuk.FilterListFragment;
import com.dr.psycho.ryuk.Interfaces.FilterListFragmentListener;
import com.dr.psycho.ryuk.R;
import com.zomato.photofilters.utils.ThumbnailItem;

import java.util.List;

public class ThumbnailAdapter extends RecyclerView.Adapter<ThumbnailAdapter.MyViewHolder> {

    private List<ThumbnailItem>  thumbnailItems;
    private FilterListFragmentListener listener;
    private Context context;
    private int selectIndex = 0;

    public ThumbnailAdapter(List<ThumbnailItem> thumbnailItems, FilterListFragmentListener listener, Context context) {
        this.thumbnailItems = thumbnailItems;
        this.listener = listener;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        Replaced context with "parent.getContext()"
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.thumbnail_item, parent,false);
        return new MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        ThumbnailItem thumbnailItem = thumbnailItems.get(position);

        holder.thumbnail.setImageBitmap(thumbnailItem.image);
        holder.thumbnail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onFilterSelected(thumbnailItem.filter);
                selectIndex = position;
                notifyDataSetChanged();
            }
        });

        holder.filter_name.setText(thumbnailItem.filterName);

        if (selectIndex == position)
            holder.filter_name.setTextColor(ContextCompat.getColor(context,R.color.selected_filter));
        else
            holder.filter_name.setTextColor(ContextCompat.getColor(context,R.color.normal_filter));
    }

    /**
     * Returns the total number of items in the data set held by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    @Override
    public int getItemCount() {
        return thumbnailItems.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
            ImageView thumbnail;
            TextView filter_name;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            thumbnail = (ImageView) itemView.findViewById(R.id.thumbnail);
            filter_name = (TextView) itemView.findViewById(R.id.filter_name);

        }
    }
}
