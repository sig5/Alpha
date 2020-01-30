package com.example.alpha;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Custom_adapter extends RecyclerView.Adapter<Custom_adapter.MyView> {

    private List<Cricket_live_scores> list;

    public class MyView extends RecyclerView.ViewHolder {

        public TextView textView,textView1;

        public MyView(View view) {
            super(view);

            textView = (TextView) view.findViewById(R.id.text);
            textView1 = (TextView) view.findViewById(R.id.text11);

        }
    }


    public Custom_adapter(List<Cricket_live_scores> horizontalList) {
        this.list = horizontalList;
    }

    @Override
    public MyView onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.live_scores, parent, false);

        return new MyView(itemView);
    }

    @Override
    public void onBindViewHolder(final MyView holder, final int position) {

        holder.textView.setText(list.get(position).team1);
        holder.textView1.setText(list.get(position).team2);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

}