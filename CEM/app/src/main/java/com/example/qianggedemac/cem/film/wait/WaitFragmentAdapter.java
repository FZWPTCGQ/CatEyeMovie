package com.example.qianggedemac.cem.film.wait;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import se.emilsjolander.stickylistheaders.StickyListHeadersAdapter;

/**
 * Created by qianggedemac on 16/12/22.
 */

public class WaitFragmentAdapter extends BaseAdapter implements StickyListHeadersAdapter {

    @Override
    public View getHeaderView(int position, View convertView, ViewGroup parent) {
        return null;
    }

    @Override
    public long getHeaderId(int position) {
        return 0;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        return null;
    }
}
