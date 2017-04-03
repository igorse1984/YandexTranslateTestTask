package ru.igorsharov.yand_test;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final EditText et = (EditText) findViewById(R.id.editText);
        final TextView tv = (TextView) findViewById(R.id.textView);
        et.setOnKeyListener(new View.OnKeyListener()
                            {
                                @Override
                                public boolean onKey(View v, int keyCode, KeyEvent event)
                                {
                                    if(event.getAction() == KeyEvent.ACTION_DOWN &&
                                            (keyCode == KeyEvent.KEYCODE_ENTER))
                                    {
                                        // сохраняем текст, введенный до нажатия Enter в переменную
                                        String str = et.getText().toString();
                                        // отправляем полученную строку в обработку
                                        new StartParsing().execute(str);
                                        tv.setText(str);
                                        return true;
                                    }
                                    return false;
                                }
                            }
        );


    }


    private class StartParsing extends AsyncTask<String, Void, Void> {

        @Override
        protected Void doInBackground(String... params) {
            new GetJSON().fetchItems(params[0]);
            return null;
        }

    }
}