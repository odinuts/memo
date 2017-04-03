package com.odinuts.memo.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.odinuts.memo.R;
import com.odinuts.memo.model.NotesHelper;

import io.realm.Realm;

public class TakeNote extends AppCompatActivity {
    private EditText mEditText;
    private Realm mRealm;
    private Menu mMenu;
    private String noteText;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_note);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        mRealm = Realm.getDefaultInstance();
        mEditText = (EditText) findViewById(R.id.edit_text);
//        mEditText.setOnKeyListener(new View.OnKeyListener() {
//            @Override
//            public boolean onKey(View v, int keyCode, KeyEvent event) {
//                if (event.getAction() == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
//
//                    NotesHelper.addItemAsync(mRealm);
//                    return true;
//                }
//                return false;
//            }
//        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.mMenu = menu;
        getMenuInflater().inflate(R.menu.note_options, mMenu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_add:
                NotesHelper.addItemAsync(mRealm, mEditText.getText().toString());
                Log.d("ADD", "Here");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}