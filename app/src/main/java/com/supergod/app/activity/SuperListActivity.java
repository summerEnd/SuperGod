package com.supergod.app.activity;

import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.supergod.R;
import com.supergod.util.FileUtil;

import java.util.List;

public class SuperListActivity extends ListActivity implements AdapterView.OnItemLongClickListener {

    protected String fileName = null;
    protected List data = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_list_actvity);
    }

    public void enableDelete() {

    }

    public void enableEdit() {

    }

    public void enableCache(String fileName, List data) {
        this.fileName = fileName;
        this.data = data;
        if (data != null) {
            FileUtil util = new FileUtil();
            List temp = (List) util.readCache(this, fileName);
            data.addAll(data);
            BaseAdapter adapter=getAdapter();
            if (adapter!=null)adapter.notifyDataSetChanged();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (data!=null)new FileUtil().saveCache(getApplicationContext(),fileName,data);
    }

    protected BaseAdapter getAdapter(){
        return (BaseAdapter) getListAdapter();
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        return false;
    }
}
