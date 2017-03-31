package com.odinuts.memo.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.TextView;

import com.odinuts.memo.R;
import com.odinuts.memo.adapters.DividerItemDecoration;
import com.odinuts.memo.adapters.MyRecyclerViewAdapter;
import com.odinuts.memo.model.NotesHelper;
import com.odinuts.memo.model.Parent;

import io.realm.Realm;

public class MainActivity extends AppCompatActivity {
    private Toolbar mToolbar;
    private FloatingActionButton mFab;
    private TextView mTextView;
    private RecyclerView mRecyclerView;
    private Realm mRealm;
    private MyRecyclerViewAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        mRealm = Realm.getDefaultInstance();

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        setUpRecyclerView();

        mTextView = (TextView) findViewById(R.id.text_view);

        mFab = (FloatingActionButton) findViewById(R.id.add_fab);
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TakeNote.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mRecyclerView.setAdapter(null);
        mRealm.close();
    }

    private void setUpRecyclerView() {
        mAdapter = new MyRecyclerViewAdapter(mRealm.where(Parent.class).findFirst().getNoteList());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL_LIST));

        TouchHelperCallback touchHelperCallback = new TouchHelperCallback();
        ItemTouchHelper touchHelper = new ItemTouchHelper(touchHelperCallback);
        touchHelper.attachToRecyclerView(mRecyclerView);
    }

    private class TouchHelperCallback extends ItemTouchHelper.SimpleCallback {

        TouchHelperCallback() {
            super(ItemTouchHelper.UP | ItemTouchHelper.DOWN, ItemTouchHelper.LEFT |
                    ItemTouchHelper.RIGHT);
        }

        @Override
        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder,
                              RecyclerView.ViewHolder target) {
            return true;
        }

        @Override
        public void onSwiped(final RecyclerView.ViewHolder viewHolder, int direction) {
            NotesHelper.deleteItemAsync(mRealm, viewHolder.getItemId());
        }

        @Override
        public boolean isLongPressDragEnabled() {
            return true;
        }
    }
}