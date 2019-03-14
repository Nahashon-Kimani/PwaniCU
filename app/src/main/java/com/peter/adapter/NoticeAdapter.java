package com.peter.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.peter.R;

import java.util.ArrayList;

public class NoticeAdapter extends RecyclerView.Adapter<NoticeAdapter.MyHolder> {
    Context context;
    ArrayList<NoticeContract> list;

    public NoticeAdapter(Context context, ArrayList<NoticeContract> list) {
        this.list = list;
        this.context = context;

    }


    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);

        return new MyHolder(inflater.inflate(R.layout.notice_strip, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder myHolder, int position) {
        myHolder.noticeTitle.setText(list.get(position).getNoticeTitle());
        myHolder.noticeDesc.setText(list.get(position).getNoticeDescription());
        myHolder.noticeCategory.setText(list.get(position).getCategory());
        myHolder.noticeDate.setText(list.get(position).getEventDate());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    class MyHolder extends RecyclerView.ViewHolder {
        TextView noticeTitle, noticeDesc, noticeCategory, noticeDate;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            noticeTitle = itemView.findViewById(R.id.notice_title);
            noticeDesc = itemView.findViewById(R.id.notice_desc);
            noticeCategory = itemView.findViewById(R.id.notice_category);
            noticeDate = itemView.findViewById(R.id.notice_date);
        }
    }
}
