package com.odinuts.memo.model;

import io.realm.Realm;
import io.realm.RealmList;

public class NotesHelper {

    public static void addItem(Realm realm, final String title, final String desc) {
        Parent parent = realm.where(Parent.class).findFirst();
        RealmList<Note> notesList = parent.getNoteList();
        realm.beginTransaction();
        Note note = realm.createObject(Note.class, System.currentTimeMillis());
        note.setTitle(title);
        note.setDescription(desc);
        realm.commitTransaction();
        notesList.add(note);
    }

    public static void deleteItem(Realm realm, long id) {
        Note note = realm.where(Note.class).equalTo(Note.FIELD_COUNT, id).findFirst();
        if (note != null) {
            note.deleteFromRealm();
        }
    }
}