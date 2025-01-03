package com.wasifS3301912.edufinder

import android.content.Context


object StudentDetails {


    fun saveStudentLoginDetails(context: Context, value: Boolean) {
        val sharedLogin = context.getSharedPreferences("EduFinder", Context.MODE_PRIVATE)
        val sharedEditor = sharedLogin.edit()
        sharedEditor.putBoolean("FINDER_IN", value).apply()
    }

    fun getStudentLoginDetails(context: Context): Boolean {
        val sharedLogin = context.getSharedPreferences("EduFinder", Context.MODE_PRIVATE)
        return sharedLogin.getBoolean("FINDER_IN", false)
    }

    fun saveStudentEmail(context: Context, value: String) {
        val sharedEmail = context.getSharedPreferences("EduFinder", Context.MODE_PRIVATE)
        val sharedEditor = sharedEmail.edit()
        sharedEditor.putString("FINDER_EMAIL", value).apply()
    }

    fun getStudentEmail(context: Context): String {
        val sharedEmail = context.getSharedPreferences("EduFinder", Context.MODE_PRIVATE)
        return sharedEmail.getString("FINDER_EMAIL", "")!!
    }

    fun saveStudentName(context: Context, value: String) {
        val sharedName = context.getSharedPreferences("EduFinder", Context.MODE_PRIVATE)
        val sharedEditor = sharedName.edit()
        sharedEditor.putString("FINDER_NAME", value).apply()
    }

    fun getStudentName(context: Context): String {
        val sharedName = context.getSharedPreferences("EduFinder", Context.MODE_PRIVATE)
        return sharedName.getString("FINDER_NAME", "")!!
    }

}