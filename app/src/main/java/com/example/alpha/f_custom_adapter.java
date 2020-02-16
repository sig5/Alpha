package com.example.alpha;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public class f_custom_adapter extends RecyclerView.Adapter<f_custom_adapter.MyView> {

    private List<f_matchlive> list;

    public class MyView extends RecyclerView.ViewHolder {

        public TextView textView, textView1, textView2,textView3,textView4,textView5,textView6,textView7,textView8;

        public MyView(View view) {
            super(view);

            textView = (TextView) view.findViewById(R.id.team1);
            textView1 = (TextView) view.findViewById(R.id.team2);
            textView2 = (TextView) view.findViewById(R.id.score);
            textView3 = (TextView) view.findViewById(R.id.possession);
            textView4 = (TextView) view.findViewById(R.id.fauls);
            textView5 = (TextView) view.findViewById(R.id.corners);
            textView6 = (TextView) view.findViewById(R.id.redc);
            textView7 = (TextView) view.findViewById(R.id.yellowc);
            textView8 = (TextView) view.findViewById(R.id.penalty);




        }
    }

    @Override
    public MyView onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.f_card2, parent, false);

        return new MyView(itemView);
    }

    public f_custom_adapter(List<f_matchlive> data)
    {
        this.list=data;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyView holder, int position) {
        holder.textView.setText(list.get(position).local_team_name);
        holder.textView1.setText(list.get(position).visitor_team_name);
        holder.textView2.setText(list.get(position).full_time);
        holder.textView3.setText(list.get(position).possession);
        holder.textView4.setText(list.get(position).fauls);
        holder.textView5.setText(list.get(position).corner);
        holder.textView6.setText(list.get(position).red);
        holder.textView7.setText(list.get(position).yellow);
        holder.textView8.setText(list.get(position).penalty);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(holder.textView4.getVisibility()==View.GONE)
                {holder.textView4.setVisibility(View.VISIBLE);
                holder.textView5.setVisibility(View.VISIBLE);
                holder.textView6.setVisibility(View.VISIBLE);
                holder.textView7.setVisibility(View.VISIBLE);
                holder.textView8.setVisibility(View.VISIBLE);
                holder.textView3.setVisibility(View.VISIBLE);
            }
                else
                {
                    {holder.textView4.setVisibility(View.GONE);
                        holder.textView5.setVisibility(View.GONE);
                        holder.textView6.setVisibility(View.GONE);
                        holder.textView7.setVisibility(View.GONE);
                        holder.textView8.setVisibility(View.GONE);
                        holder.textView3.setVisibility(View.GONE);
                    }


                }
            }
        });


    }


    @Override
    public int getItemCount() {
        return list.size();
    }

}