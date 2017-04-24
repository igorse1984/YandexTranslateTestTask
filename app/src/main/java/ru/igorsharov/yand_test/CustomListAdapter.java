package ru.igorsharov.yand_test;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


public class CustomListAdapter extends BaseAdapter {
    private final String TAG = "CustomListAdapter_LOG";
    private ArrayList<TranslatedTextObject> arrayList;
    private Context context;
    ViewHolder viewHolder;

    public CustomListAdapter(ArrayList<TranslatedTextObject> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }


    @Override
    public int getCount() {
        Log.d(TAG, "getCount");
        return arrayList.size();
    }

    @Override
    public TranslatedTextObject getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        Log.d(TAG, "getItemId");
        TranslatedTextObject tt = getItem(position);
        initFavourite(viewHolder, tt);
        return position;
    }

    static class ViewHolder {
        TextView txtRequest;
        TextView txtTranslate;
        ImageView imgFavourite;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.d(TAG, "getView");
//        ViewHolder viewHolder;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.listitem_history, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.txtRequest = (TextView) convertView.findViewById(R.id.tvRequestText);
            viewHolder.txtTranslate = (TextView) convertView.findViewById(R.id.tvTranslatedText);
            viewHolder.imgFavourite = (ImageView) convertView.findViewById(R.id.imFavourite);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        fillView(viewHolder, position);

        return convertView;
    }


    // измененние отображения флага избранного
    private void initFavourite(ViewHolder viewHolder, TranslatedTextObject tt) {
        Log.d(TAG, "initFavourite");
        if (tt.isFavourite()) {
            viewHolder.imgFavourite.setVisibility(View.VISIBLE);
        } else
            viewHolder.imgFavourite.setVisibility(View.GONE);
    }

    // заполнение item-а
    private void fillView(final ViewHolder viewHolder, int position) {
        Log.d(TAG, "fillView");
        TranslatedTextObject tt = getItem(position);
        // отображение передаваемого на перевод текста
        viewHolder.txtRequest.setText(tt.getRequestedText());

        // отображение флага избранного
        initFavourite(viewHolder, tt);

        // отображение текса перевода
        viewHolder.txtTranslate.setText(tt.getTranslatedText());

    }
}
