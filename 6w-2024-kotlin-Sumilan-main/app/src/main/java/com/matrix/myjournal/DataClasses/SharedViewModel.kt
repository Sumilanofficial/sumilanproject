package com.matrix.myjournal.DataClasses

import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel() {
    var title: String? = null
    var questionsListSize:Int = 0
}
