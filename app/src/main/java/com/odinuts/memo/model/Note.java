package com.odinuts.memo.model;

import java.util.Random;
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

    public String getIdString() {
        return Integer.toString(id);
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

    static void create(Realm realm) {
        create(realm, false);
    }

    static void create(Realm realm, boolean randomlyInsert) {
        Parent parent = realm.where(Parent.class).findFirst();
        RealmList<Note> notes = parent.getNoteList();
        Note note = realm.createObject(Note.class, increment());
        if (randomlyInsert && notes.size() > 0) {
            Random rand = new Random();
            notes.listIterator(rand.nextInt(notes.size())).add(note);
        } else {
            notes.add(note);
        }
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
}
