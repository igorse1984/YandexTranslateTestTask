package ru.igorsharov.yand_test;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;


public class HistoryAdapter extends BaseAdapter {
    private ArrayList<TranslatedText> al;
    private Context c;

    public HistoryAdapter(ArrayList<TranslatedText> al, Context c) {
        this.al = al;
        this.c = c;
    }

    @Override
    public int getCount() {
        return al.size();
    }

    @Override
    public TranslatedText getItem(int position) {
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
    private void initFavourite(View v, TranslatedText tt) {
        View imFavourite = v.findViewById(R.id.imFavourite);
        if (tt.isFavourite())
            imFavourite.setVisibility(View.VISIBLE);
        else
            imFavourite.setVisibility(View.GONE);
    }

    // заполнение item-а
    private void fillView(View v, int position) {
        final TranslatedText tt = getItem(position);

        // запрашиваемый текст
        TextView tvRequestedText = (TextView) v.findViewById(R.id.tvRequestText);
        tvRequestedText.setText(tt.getRequestedText());

        // favourite
        initFavourite(v, tt);

        // текс перевода
        TextView tvTranslatedText = (TextView) v.findViewById(R.id.tvTranslatedText);
        tvTranslatedText.setText(tt.getTranslatedText());

        // возможность клика
        v.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (tt.isFavourite())
                    tt.setFavourite(false);
                else
                    tt.setFavourite(true);
                initFavourite(v, tt);
            }
        });
    }
}
