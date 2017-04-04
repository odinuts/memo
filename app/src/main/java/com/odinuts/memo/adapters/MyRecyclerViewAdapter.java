package com.odinuts.memo.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.odinuts.memo.R;
import com.odinuts.memo.model.Note;

import io.realm.OrderedRealmCollection;
import io.realm.RealmRecyclerViewAdapter;

public class MyRecyclerViewAdapter extends
        RealmRecyclerViewAdapter<Note, MyRecyclerViewAdapter.MyViewHolder> {

    public MyRecyclerViewAdapter(OrderedRealmCollection<Note> data) {
        super(data, true);
        setHasStableIds(true);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_note, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final Note noteItem = getItem(position);
        holder.note = noteItem;
        holder.noteTitle.setText((noteItem.getTitle()));
        holder.noteDescription.setText(noteItem.getDescription());
    }

    @Override
    public long getItemId(int index) {
        return getItem(index).getId();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        public Note note;
        public TextView noteTitle;
        public TextView noteDescription;

        MyViewHolder(View view) {
            super(view);
            noteTitle = (TextView) view.findViewById(R.id.note_title_text_view);
            noteDescription = (TextView) view.findViewById(R.id.note_desc_text_view);
        }
    }
}