package com.wasifS3301912.edufinder

import android.content.Context


object StudentDetails {

    private const val PREFS_NAME = "EduFinder"

    fun saveStudentLoginDetails(context: Context, value: Boolean) {
        val sharedPref = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putBoolean("LOGIN_STATUS", value).apply()
    }

    fun getStudentLoginDetails(context: Context): Boolean {
        val sharedPref = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        return sharedPref.getBoolean("LOGIN_STATUS", false)
    }

    fun saveStudentEmail(context: Context, value: String) {
        val sharedPref = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putString("LOGIN_EMAIL", value).apply()
    }

    fun getStudentEmail(context: Context): String {
        val sharedPref = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        return sharedPref.getString("LOGIN_EMAIL", "")!!
    }

    fun saveStudentName(context: Context, value: String) {
        val sharedPref = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putString("LOGIN_NAME", value).apply()
    }

    fun getStudentName(context: Context): String {
        val sharedPref = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        return sharedPref.getString("LOGIN_NAME", "")!!
    }

}