package com.example.alpha;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
import java.util.List;

public class Custom_adapter extends RecyclerView.Adapter<Custom_adapter.MyView> implements Parcelable {

    private List<Cricket_live_scores> list;

    protected Custom_adapter(Parcel in) {
    }

    public static final Creator<Custom_adapter> CREATOR = new Creator<Custom_adapter>() {
        @Override
        public Custom_adapter createFromParcel(Parcel in) {
            return new Custom_adapter(in);
        }

        @Override
        public Custom_adapter[] newArray(int size) {
            return new Custom_adapter[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }

    public class MyView extends RecyclerView.ViewHolder {

        public TextView textView, textView1, textView2;

        public MyView(View view) {
            super(view);

            textView = (TextView) view.findViewById(R.id.t1);
            textView1 = (TextView) view.findViewById(R.id.t2);
            textView2 = (TextView) view.findViewById(R.id.description);
        }
    }


    public Custom_adapter(List<Cricket_live_scores> horizontalList) {
        this.list = horizontalList;
    }

    @Override
    public MyView onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card, parent, false);

        return new MyView(itemView);
    }

    @Override
    public void onBindViewHolder(final MyView holder, final int position) {

        holder.textView.setText(list.get(position).team1);
        holder.textView1.setText(list.get(position).team2);
        holder.textView2.setText(list.get(position).stat);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(v.getContext(),Cric_Match_Details.class);
                i.putExtra("matchkey",list.get(position));

               //i.putExtra("matchkey",list.get(position).m);
                v.getContext().startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

}