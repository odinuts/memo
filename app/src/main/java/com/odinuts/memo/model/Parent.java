package com.odinuts.memo.model;

import io.realm.RealmList;
import io.realm.RealmObject;

public class Parent extends RealmObject {
    @SuppressWarnings("unused")
    private RealmList<Note> noteList;

    public RealmList<Note> getNoteList() {
        return noteList;
    }
}
