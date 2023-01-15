package com.quizeapp.utils

import android.os.Bundle
import androidx.fragment.app.Fragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

//all of my fragment inherited from the abstract class BaseFragment extends Fragment() and implements
//CoroutineScope.
//Kotlin Coroutines
//▪ A new way of managing background threads that can simplify code by reducing the need for
//callbacks. Coroutines are a Kotlin feature that convert async callbacks for long-running tasks, such as
//database or network access.
abstract class BaseFragment : Fragment(), CoroutineScope {
    private lateinit var job: Job
    override val coroutineContext: CoroutineContext

    //- Use this dispatcher to run a coroutine on the main Android thread. This
    //should be used only for interacting with the UI and performing quick work. Examples include
    //calling suspend functions, running Android UI framework operations, and
    //updating LiveData objects.
        get() = job + Dispatchers.Main
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        job = Job()
    }
    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }
}