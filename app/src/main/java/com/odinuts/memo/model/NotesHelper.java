package com.odinuts.memo.model;

import io.realm.Realm;
import io.realm.RealmList;

public class NotesHelper {

    public static void addItem(Realm realm, final String title, final String desc) {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Parent parent = realm.where(Parent.class).findFirst();
                RealmList<Note> notesList = parent.getNoteList();
                Note note = realm.createObject(Note.class, System.currentTimeMillis());
                note.setTitle(title);
                note.setDescription(desc);
                notesList.add(note);
            }
        });
    }

    public static void deleteItem(Realm realm, final long id) {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Note note = realm.where(Note.class).equalTo(Note.FIELD_COUNT, id).findFirst();
                if (note != null) {
                    note.deleteFromRealm();
                }
            }
        });
    }
}