package com.example.ribath.nupuit;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Ribath on 3/31/2017.
 */

public class ContactAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public List<ContactClass> contactList;
    String owner_name, owner_number;
    private static final int TYPE_FOOTER = 1;
    private static final int TYPE_ITEM = 2;
    String TAG = "Adapter";

    public ContactAdapter() {
        Log.i(TAG, "Adapter Called");
    }

    public void setData(List<ContactClass> contactList)
    {
        this.contactList = contactList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_ITEM)
        {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_list_row, parent, false);
            return new MyViewHolder(itemView);
        }
        else if (viewType == TYPE_FOOTER) {
            //Inflating footer view
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_list_footer, parent, false);
            return new FooterViewHolder(itemView);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MyViewHolder) {
            if(position!=contactList.size())
            {
                ContactClass contactClass = contactList.get(position);
                MyViewHolder myViewHolder = (MyViewHolder)holder;
                Log.i(TAG, "name = " + contactClass.getContact_name());
                Log.i(TAG, "name = " + contactClass.getContact_number());
                myViewHolder.name.setText(contactClass.getContact_name());
                myViewHolder.number.setText(contactClass.getContact_number());
            }
        }
        else if (holder instanceof FooterViewHolder) {
            FooterViewHolder footerViewHolder = (FooterViewHolder)holder;

            footerViewHolder.footerText.setText("LOAD MORE");
        }
    }

    @Override
    public int getItemCount() {
        return contactList.size()+2;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, number;
        public MyViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name);
            number = (TextView) itemView.findViewById(R.id.number);
        }
    }

    private class FooterViewHolder extends RecyclerView.ViewHolder {
        TextView footerText;

        public FooterViewHolder(View view) {
            super(view);
            footerText = (TextView) view.findViewById(R.id.footer_textview);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == contactList.size() + 1) {
            return TYPE_FOOTER;
        }
        return TYPE_ITEM;
    }
}
