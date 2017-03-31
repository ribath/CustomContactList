package com.example.ribath.nupuit;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    String owner_name, owner_number;
    RecyclerView recyclerView;
    List<ContactClass> list;
    ContactAdapter mAdapter;
    TextView ownername, ownernumber;
    int i=0, limit=10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        ownername = (TextView)findViewById(R.id.ownerName);
        ownernumber = (TextView)findViewById(R.id.ownerNumber);
        list= new ArrayList<>();

        Cursor c = getApplication().getContentResolver().query(ContactsContract.Profile.CONTENT_URI, null, null, null, null);
        c.moveToFirst();
        owner_name = c.getString(c.getColumnIndex("display_name"));
        Log.i("Main", owner_name);
        ownername.setText(owner_name);

        String main_data[] = {"data1", "is_primary", "data3", "data2", "data1", "is_primary", "photo_uri", "mimetype"};
        Object object = getContentResolver().query(Uri.withAppendedPath(android.provider.ContactsContract.Profile.CONTENT_URI, "data"),
                main_data, "mimetype=?",
                new String[]{"vnd.android.cursor.item/phone_v2"},
                "is_primary DESC");
        if (object != null) {
            do {
                if (!((Cursor) (object)).moveToNext())
                    break;
                // This is the phoneNumber
                owner_number= ((Cursor) (object)).getString(4);
                Log.i("Main", owner_number);
                ownernumber.setText(owner_number);
            } while (true);
            ((Cursor) (object)).close();
        }

        addContactstoDB();
        addmore();

        mAdapter = new ContactAdapter();
        mAdapter.setData(list);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();

        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(this, recyclerView ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        // do whatever
                        Toast.makeText(MainActivity.this, "postion "+position+" clicked",
                                Toast.LENGTH_LONG).show();
                        if (position==list.size()+1)
                        {
                            limit = limit+10;
                            addmore();
                            mAdapter.setData(list);
                            mAdapter.notifyDataSetChanged();
                        }
                    }

                    @Override public void onLongItemClick(View view, int position) {
                        // do whatever
                    }
                })
        );

    }

    public void addmore()
    {
        DBHelper dbHelper = OpenHelperManager.getHelper(this, DBHelper.class);
        try {
            Dao<ContactClass, String> ContactClassDao = dbHelper.getContactClassDao();
            List<ContactClass> Mlist = ContactClassDao.queryForAll();
            while(i<limit)
            {
                list.add(Mlist.get(i));
                i++;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addContactstoDB()
    {
        Cursor phones = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,null,null, null);
        while (phones.moveToNext())
        {
            String name=phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            String phoneNumber = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            ContactClass contactClass = new ContactClass(name, phoneNumber);

            DBHelper dbHelper = OpenHelperManager.getHelper(this, DBHelper.class);
            try {
                Dao<ContactClass, String> ContactClassDao = dbHelper.getContactClassDao();
                ContactClassDao.create(contactClass);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        phones.close();
    }
}
