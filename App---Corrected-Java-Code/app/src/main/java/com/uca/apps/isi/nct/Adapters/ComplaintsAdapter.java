package com.uca.apps.isi.nct.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import com.facebook.drawee.view.SimpleDraweeView;
import com.uca.apps.isi.nct.R;
import com.uca.apps.isi.nct.activities.DetailComplantActivity;
import com.uca.apps.isi.nct.models.Complaint;

public class ComplaintsAdapter extends RecyclerView.Adapter<ComplaintsAdapter.ViewHolder> {
    private List<Complaint> complaints;
    private Context context;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView title;
        public TextView description;
        public SimpleDraweeView picture;
        public CardView card;

        public ViewHolder(View view) {
            super(view);
            title = view.findViewById(R.id.title);
            description = view.findViewById(R.id.description);
            card = view.findViewById(R.id.card_view);
            picture = view.findViewById(R.id.imag);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public ComplaintsAdapter(Context context,List<Complaint> complaints) {
        this.complaints = complaints;
        this.context = context;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ComplaintsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_view_complaint, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // Complaint complaint = complaints.get(position);
        final Complaint complaint = complaints.get(position);
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.title.setText(complaint.getTitle());
        holder.description.setText(complaint.getDescription());


        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailComplantActivity.class);
                intent.putExtra("id", complaint.getId());
                context.startActivity(intent);

            }
        });


        /*if (complaint.getPictures().size() != 0) {
            holder.picture.setImageURI(complaint.getPictures().get(0).getUrl());
        }*/
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return complaints.size();
    }
}

