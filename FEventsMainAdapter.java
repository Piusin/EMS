package com.example.piusin.ems;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Piusin on 4/24/2018.
 */

public class FEventsMainAdapter extends RecyclerView.Adapter<FEventsMainAdapter.MyViewHolder> {

    private Context mCtx;
    private String eventsNames;
    AppCompatActivity activity;

    ArrayList<FEventsDataProvider> data = new ArrayList<>();

    public FEventsMainAdapter(Context mCtx, ArrayList<FEventsDataProvider> data) {
        this.data = data;
        this.mCtx = mCtx;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.events_items, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final FEventsDataProvider fEventsDataProvider = data.get(position);
        holder.eventName.setText(data.get(position).getEventName());
        holder.eventImage.setImageResource(data.get(position).getEventImage());
        holder.eventCost.setText(data.get(position).getEventCost());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eventsNames = fEventsDataProvider.getEventName();
                if(eventsNames.equals("More")){
                    activity = (AppCompatActivity) v.getContext();
                    activity.getSupportFragmentManager().beginTransaction().replace(R.id.main_container, new FragmentEvents()).addToBackStack(null).commit();
                    activity.getSupportActionBar().setTitle("Events");
                }
                else{
                    Bundle bundle = new Bundle();
                    bundle.putString("eventId", fEventsDataProvider.getEventName());
                    FragmentEventDesc fragmentEventDesc = new FragmentEventDesc();
                    fragmentEventDesc.setArguments(bundle);
                    activity = (AppCompatActivity) v.getContext();
                    activity.getSupportFragmentManager().beginTransaction().replace(R.id.main_container, fragmentEventDesc).addToBackStack(null).commit();
                    activity.getSupportActionBar().setTitle("Event Desc");
                }

            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView eventImage;
        TextView eventName, eventCost;
        CardView cardView;

        private MyViewHolder(View itemView) {
            super(itemView);

            //itemView.setOnClickListener(this);
            eventImage = itemView.findViewById(R.id.event_image);
            eventName = itemView.findViewById(R.id.event_name);
            eventCost = itemView.findViewById(R.id.event_cost);
            cardView = itemView.findViewById(R.id.cardview_event);
        }


    }
}
