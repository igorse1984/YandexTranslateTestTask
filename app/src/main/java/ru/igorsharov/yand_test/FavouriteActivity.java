package ru.igorsharov.yand_test;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class FavouriteActivity extends ListFragment {
    int counter = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        System.out.println("Сработал onCreateView");

        return inflater.inflate(R.layout.activity_favourite, null);
    }


    @Override
    public void onStart() {
        super.onStart();
        System.out.println("Сработал onStart");

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        System.out.println("Сработал onAttach");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("Сработал onCreate");

    }

    @Nullable
    @Override
    public View getView() {

        System.out.println("Сработал getView " + counter++);
            setListAdapter(new CustomListAdapter(TranslatedTextObject.translateFavourite, getActivity()));
        return super.getView();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        System.out.println("Сработал onActivityCreated");
    }

    @Override
    public void onResume() {
        super.onResume();
        System.out.println("Сработал onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        System.out.println("Сработал onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        System.out.println("Сработал onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        System.out.println("Сработал onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        System.out.println("Сработал onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        System.out.println("Сработал onDetach");
    }
}