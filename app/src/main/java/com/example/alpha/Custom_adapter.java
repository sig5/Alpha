//package com.example.alpha;
//
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//
//import java.util.ArrayList;
//
//public class Custom_adapter extends RecyclerView.Adapter<Custom_adapter.MyViewHolder> {
//    private ArrayList<String> scores;
//    @NonNull
//    @Override
//    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View score_View= LayoutInflater.from(parent.getContext()).inflate(R.layout.live_scores,parent,false);
//        return new MyViewHolder(score_View);
//    }
//    public Custom_adapter(ArrayList<String> scores) {
//        this.scores = scores;
//    }
//    @Override
//    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return 0;
//    }
//
//    public class MyViewHolder extends RecyclerView.ViewHolder {
//        TextView text;
//        public MyViewHolder(@NonNull View itemView) {
//            super(itemView);
//            text=(TextView) itemView.findViewById(R.id.text);
//        }
//    }
//}


package com.example.alpha;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Custom_adapter extends RecyclerView.Adapter<Custom_adapter.MyView> {

    private List<String> list;

    public class MyView extends RecyclerView.ViewHolder {

        public TextView textView;

        public MyView(View view) {
            super(view);

            textView = (TextView) view.findViewById(R.id.text);

        }
    }


    public Custom_adapter(List<String> horizontalList) {
        this.list = horizontalList;
    }

    @Override
    public MyView onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.live_scores, parent, false);

        return new MyView(itemView);
    }

    @Override
    public void onBindViewHolder(final MyView holder, final int position) {

        holder.textView.setText(list.get(position));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

}