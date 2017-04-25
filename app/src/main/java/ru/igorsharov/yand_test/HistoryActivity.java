package ru.igorsharov.yand_test;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class HistoryActivity extends ListFragment {


    private CustomListAdapter adapter;

    private final String TAG = "HistoryActivity_LOG";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, "Сработал onCreateView");
        return inflater.inflate(R.layout.activity_history, container, false);
    }


    @Nullable
    @Override
    public View getView() {
        Log.d(TAG, "Сработал getView");
        setListAdapter(adapter);
        Log.d(TAG, "adapter: SET");
        return super.getView();
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d(TAG, "Сработал onActivityCreated");
        adapter = new CustomListAdapter(TranslatedTextObject.translate, getActivity());
        Log.d(TAG, "adapter: NEW");
    }
}

