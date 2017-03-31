package com.odinuts.memo.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;

import com.odinuts.memo.R;
import com.odinuts.memo.model.NotesHelper;

import io.realm.Realm;

public class TakeNote extends AppCompatActivity {
    private EditText mEditText;
    private Realm mRealm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_note);
        mRealm = Realm.getDefaultInstance();
        mEditText = (EditText) findViewById(R.id.edit_text);
        mEditText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {

                    NotesHelper.addItemAsync(mRealm);
                    return true;
                }
                return false;
            }
        });
    }
}
