package com.odinuts.memo.model;

import java.util.concurrent.atomic.AtomicInteger;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Note extends RealmObject {
    public static final String FIELD_COUNT = "count";
    private static AtomicInteger INTEGER_COUNTER = new AtomicInteger(0);

    @PrimaryKey
    private int id;
    private String note;

    static void create(Realm realm, String noteText) {
        Parent parent = realm.where(Parent.class).findFirst();
        RealmList<Note> notes = parent.getNoteList();
        realm.beginTransaction();
        Note note = realm.createObject(Note.class, increment());
        note.setNote(noteText);
        notes.add(note);
        realm.commitTransaction();
    }

    static void delete(Realm realm, long id) {
        Note note = realm.where(Note.class).equalTo(FIELD_COUNT, id).findFirst();
        // Otherwise it has been deleted already.
        if (note != null) {
            note.deleteFromRealm();
        }
    }

    private static Object increment() {
        return INTEGER_COUNTER.getAndIncrement();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
