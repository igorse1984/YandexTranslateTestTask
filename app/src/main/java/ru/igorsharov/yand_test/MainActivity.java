package ru.igorsharov.yand_test;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText et = null;
    TextView tv = null;
    ArrayList al = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et = (EditText) findViewById(R.id.editText);
        tv = (TextView) findViewById(R.id.textView);
        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // сохраняем текст, введенный до нажатия кнопки в переменную
                String strIn = et.getText().toString();
                // отправляем полученную строку в обработку
                new StartParsing().execute(strIn);

                String strOut = (String) al.get(0);

                tv.setText(strOut);
            }
        });

// слушатель EditText
//        et.setOnKeyListener(new View.OnKeyListener() {
//                                @Override
//                                public boolean onKey(View v, int keyCode, KeyEvent event) {
//                                    if (event.getAction() == KeyEvent.ACTION_DOWN &&
//                                            (keyCode == KeyEvent.KEYCODE_ENTER)) {
//                                        // сохраняем текст, введенный до нажатия Enter в переменную
//                                        String str = et.getText().toString();
//                                        // отправляем полученную строку в обработку
//                                        new StartParsing().execute(str);
//                                        tv.setText(str);
//                                        return true;
//                                    }
//                                    return false;
//                                }
//                            }
//
//
//        );

    }


    private class StartParsing extends AsyncTask<String, Void, Void> {

        @Override
        protected Void doInBackground(String... params) {
            // params[0] - текст, полученный из EditText

            GetJSON gj = new GetJSON();
//            System.out.println("!!!!" + gj.fetchItems(params[0]));
            return null;
        }

    }
}