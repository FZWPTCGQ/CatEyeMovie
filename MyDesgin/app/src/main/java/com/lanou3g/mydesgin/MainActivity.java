package com.lanou3g.mydesgin;

import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
private Button mButton;
    private TextInputLayout mTextInputLayout;
    private EditText etName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mButton = (Button) findViewById(R.id.btn);
        mTextInputLayout = (TextInputLayout) findViewById(R.id.et_key);
        etName = (EditText) findViewById(R.id.et_name);
        mButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(mButton,"你猜我干嘛",3000).setAction("再次提示",new View.OnClickListener(){

                    @Override
                    public void onClick(View view) {
                        Snackbar.make(mButton,"你猜不到",2000).show();
                    }
                }).show();
            }
        });
    }
}
