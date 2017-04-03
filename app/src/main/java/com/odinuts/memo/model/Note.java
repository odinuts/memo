package com.odinuts.memo.model;

import java.util.concurrent.atomic.AtomicInteger;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Note extends RealmObject {
    public static final String FIELD_COUNT = "count";
    private static AtomicInteger INTEGER_COUNTER = new AtomicInteger(0);

    @PrimaryKey
    private int id;
    private String note;

    static void create(final Realm realm, final String noteText) {
//        Parent parent = realm.where(Parent.class).findFirst();
//        RealmList<Note> notes = parent.getNoteList();
//        realm.beginTransaction();
//        Note note = realm.createObject(Note.class, increment());
//        note.setNote(noteText);
//        realm.commitTransaction();
//        notes.add(note);

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Number currentId = realm.where(Note.class).max("id");
                int nextId;
                if (currentId == null) {
                    nextId = 1;
                } else {
                    nextId = currentId.intValue() + 1;
                }
                Note note = new Note();
                note.setId(nextId);
                note.setNote(noteText);
                realm.insertOrUpdate(note); // using insert API
            }
        });
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
