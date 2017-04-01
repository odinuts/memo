package com.odinuts.memo.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.odinuts.memo.R;
import com.odinuts.memo.model.Note;

import java.util.HashSet;
import java.util.Set;

import io.realm.OrderedRealmCollection;
import io.realm.RealmRecyclerViewAdapter;

public class MyRecyclerViewAdapter extends
        RealmRecyclerViewAdapter<Note, MyRecyclerViewAdapter.MyViewHolder> {

    private boolean inDeletionMode = false;
    private Set<Integer> notesToDelete = new HashSet<Integer>();

    public MyRecyclerViewAdapter(OrderedRealmCollection<Note> data) {
        super(data, true);
        setHasStableIds(true);
    }

    void enableDeletionMode(boolean enabled) {
        inDeletionMode = enabled;
        if (!enabled) {
            notesToDelete.clear();
        }
        notifyDataSetChanged();
    }

    Set<Integer> getNotesToDelete() {
        return notesToDelete;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_note, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final Note obj = getItem(position);
        holder.data = obj;
        holder.noteText.setText(obj.getNote());
    }

    @Override
    public long getItemId(int index) {
        return getItem(index).getId();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        public Note data;
        TextView noteText;

        MyViewHolder(View view) {
            super(view);
            noteText = (TextView) view.findViewById(R.id.text_view);
        }
    }
}