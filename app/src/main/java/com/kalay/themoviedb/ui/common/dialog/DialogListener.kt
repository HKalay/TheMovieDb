package com.kalay.themoviedb.ui.common.dialog

interface DialogListener {

    fun onDialogShow(requestCode: Int) {
    }


    fun onDialogCancel(requestCode: Int) {
    }


    fun onDialogDismiss(requestCode: Int) {
    }
}