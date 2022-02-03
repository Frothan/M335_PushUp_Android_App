package com.example.pushup;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    ArrayList<String> users;
    ArrayList<String> time;
    ArrayList<String> pushups;
    Context context;

    public CustomAdapter(Context context, ArrayList<String> users, ArrayList<String> time, ArrayList<String> pushups) {
        this.context = context;
        this.users = users;
        this.time = time;
        this.pushups = pushups;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        // inflate the item Layout
        View v = inflater.inflate(R.layout.recycler_view, parent, false);
        // pass the view to View Holder
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        // set the data in items
        holder.name.setText(users.get(position));
        holder.time.setText(time.get(position));
        holder.pushups.setText(pushups.get(position));
    }


    @Override
    public int getItemCount() {
        return users.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name;// init the item view's
        public TextView time;
        public TextView pushups;

        public MyViewHolder(View itemView) {
            super(itemView);

            // get the reference of item view's
            name = (TextView) itemView.findViewById(R.id.oName);
            time = (TextView) itemView.findViewById(R.id.oTime);
            pushups = (TextView) itemView.findViewById(R.id.oAmount);

        }
    }
}
