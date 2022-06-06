package com.mommylicious.mobile.data.preferences

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.mommylicious.mobile.MommylisiousApplication
import com.mommylicious.mobile.data.preferences.PrefConstants.PREF_CHILD_ID
import com.mommylicious.mobile.data.preferences.PrefConstants.PREF_KEY
import java.util.*


class Preferences private constructor() {
    private val mPrefs: SharedPreferences
    private val mEdit: SharedPreferences.Editor

    val childId: String?
        get() = instance.mPrefs.getString(PREF_CHILD_ID, "")

    fun saveChildId(value: String?) {
        mEdit.putString(PREF_CHILD_ID, value)
        mEdit.apply()
    }

    companion object {
        var INSTANCE: Preferences? = null
        val instance: Preferences
            get() {
                if (INSTANCE == null) INSTANCE = Preferences()
                return INSTANCE as Preferences
            }
    }

    init {
        val app: Application = MommylisiousApplication.instance
        mPrefs = app.getSharedPreferences(PREF_KEY, Context.MODE_PRIVATE)
        mEdit = mPrefs.edit()
    }
}