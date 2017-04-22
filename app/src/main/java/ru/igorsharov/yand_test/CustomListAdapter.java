package ru.igorsharov.yand_test;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;


public class CustomListAdapter extends BaseAdapter {
    private ArrayList<TranslatedTextObject> al;
    private Context c;

    public CustomListAdapter(ArrayList<TranslatedTextObject> al, Context c) {
        this.al = al;
        this.c = c;
    }

    @Override
    public int getCount() {
        return al.size();
    }

    @Override
    public TranslatedTextObject getItem(int position) {
        return al.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    // главный метод адаптера
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null)
            convertView = LayoutInflater.from(c).inflate(
                    R.layout.listitem_history, null);
        fillView(convertView, position);
        return convertView;
    }

    // инициализация элемента favourite для повторного использования
    private void initFavourite(View v, TranslatedTextObject tt) {
        View imFavourite = v.findViewById(R.id.imFavourite);
        if (tt.isFavourite()) {
            imFavourite.setVisibility(View.VISIBLE);
        } else
            imFavourite.setVisibility(View.GONE);
    }

    // заполнение item-а
    private void fillView(View v, int position) {
        final TranslatedTextObject tt = getItem(position);

        // отображение передаваемого на перевод текста
        TextView tvRequestedText = (TextView) v.findViewById(R.id.tvRequestText);
        tvRequestedText.setText(tt.getRequestedText());

        // отображение флага избранного
        initFavourite(v, tt);

        // отображение текса перевода
        TextView tvTranslatedText = (TextView) v.findViewById(R.id.tvTranslatedText);
        tvTranslatedText.setText(tt.getTranslatedText());

        // добавление возможности клика по item-у
        v.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (tt.isFavourite()) {
                    tt.setFavourite(false);
                    TranslatedTextObject.translateFavourite.remove(tt);
                } else {
                    tt.setFavourite(true);
                    TranslatedTextObject.translateFavourite.add(tt);
                }

                initFavourite(v, tt);
            }
        });
    }
}
