package ru.igorsharov.yand_test;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FavouriteActivity extends ListFragment {
    private final String TAG = "FavouriteActivity_LOG";
    private CustomListAdapter adapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_favourite, container, false);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        adapter = new CustomListAdapter(TranslatedTextObject.translateFavourite, getActivity());
        Log.d(TAG, "adapter: CREATE");
    }

    @Nullable
    @Override
    public View getView() {
        Log.d(TAG, "adapter: SETin");
        setListAdapter(adapter);
        Log.d(TAG, "adapter: SETout");
        return super.getView();
    }

}