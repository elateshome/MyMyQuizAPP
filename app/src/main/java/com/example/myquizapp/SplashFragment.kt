package com.example.myquizapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.Navigation
import com.quizeapp.Quiz
import com.quizeapp.QuizDB
import com.quizeapp.utils.BaseFragment
import kotlinx.coroutines.launch

class SplashFragment : BaseFragment() {

    private lateinit var tv: TextView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_splash, container, false)
        tv = view.findViewById(R.id.logo)
        putToDB()
        return view
    }

    override fun onResume(){
        super.onResume()
        tv.postDelayed({
            Navigation.findNavController(requireView()).navigate(R.id.action_splashFragment_to_homeFragment)
        }, 1650)
    }

    fun putToDB(){
        val quiz1 = Quiz(1L,"1) What is Android? ","A. Android is a stack of software's for mobility", "B.  Google mobile device name","C. Virtual machine","D. None of the above","a","Answer: (a) Android is a stack of software's for mobility\n" +
                "\n" +
                "Explanation: Android is a stack of software applications for mobile devices, which includes an operating system, middleware applications, and some key applications.")
        val quiz2 = Quiz(2L,"2) What are the layouts available in android?", "A.  Linear Layout","B. Frame Layout","C. Table Layout","D. All of above"
                ,"d","Answer: (d) All of the above\n" +
                "\n" +
                "Explanation: Android is having Linear Layout(Horizontal and Vertical), Frame Layout, Table Layout, and Relative Layout.")
        val quiz3 = Quiz(3L,"3) What is ANR in android?","A. When the application is not responding ANR will occur.", "B. Dialog box is called as ANR.","C. When Android forcefully kills an application, it is called ANR","D. None of the above","a","(a) When the application is not responding ANR will occur\n" +
                "\n" +
                "Explanation: When an Application is not responding ANR will be occur, and it will stop the process or an application.")
        val quiz4 = Quiz(4L,"4) What is the use of content provider in Android?","A. To store the data in the database", "B. To share the data between applications","C. To send the data from an application to another application","D. None of the above","c","Answer: (c) To send the data from an application to another application\n" +
                "\n" +
                "Explanation: A content provider is used to share Data between applications")
        val quiz5 = Quiz(5L,"5) Which of the following android component displays the part of an activity on screen?","A. View", "B. Manifest","C. Intent","D. Fragment","d","Answer: (d) Fragment\n" +
                "\n" +
                "Explanation: Android Fragment is the part of activity; it is also known as sub-activity. There can be more than one fragment in an activity. Fragments represent multiple screens inside one activity.")
        val quiz6 = Quiz(6L,"6) Which of the following is the parent class of Activity?","A. context", "B. object","C. contextThemeWrapper","D. None of the above","c","Answer: (c) contextThemeWrapper\n" +
                "\n" +
                "Explanation: The android Activity is the subclass of ContextThemeWrapper class. Android Activity Lifecycle is controlled by 7 methods of android.app.Activity class. An activity is a single screen in android. It is like a window or frame of Java.")
        val quiz7 = Quiz(7L,"7) What is DDMS in android? -","A. Dalvik memory server", "B. Device memory server","C.  Dalvik monitoring services","D. Dalvik debug monitor services","d","Answer: (c) Dalvik debug monitor services\n" +
                "\n" +
                "Explanation: DDMS provides port forwarding, screen capturing, memory mapping, logcat, calls, SMS etc.")
        val quiz8 = Quiz(8L,"8) How can we kill an activity in android?","A. Using finish() method", "B. Using finishActivity(int requestCode)","C. Both (a) and (b)","D. Neither (a) nor (b)","c","Answer: (c) Both (a) and (b)\n" +
                "\n" +
                "Explanation: The finish() method is used to close the activity. Whereas the finishActivity(int requestCode) also closes the activity with requestCode.")
        val quiz9 = Quiz(9L,"9)  In which state the activity is, if it is not in focus, but still visible on the screen?","A. Stopped state", "B. Destroyed state","C. Paused state","D. Running state","c","Answer: (c) Paused state\n" +
                "\n" +
                "Explanation: When an activity is not in focus that is not interacting with the user but still visible on the screen, then the activity is in paused state.")
        val quiz10 = Quiz(10L,"10) On which of the following, developers can test the application, during developing the android applications?",
            "A. Third-party emulators", "B. Emulator included in Android SDK","C. Physical android phone","D. All of the above","d","Answer: (d) All of the above\n" +
                    "\n" +
                    "Explanation: We can use the Android emulator, physical android phone, or third-party emulator as a target device to execute and test our Android application.")
        val quiz11 = Quiz(11L,"11) What does API stand for?","A. Application Programming Interface", "B. Android Programming Interface","C. Android Page Interface","D. Application Page Interface","a","Answer: (a) Application Programming Interface\n" +
                "\n" +
                "Explanation: API stands for application program interface. It is a set of routines, protocols, and tools for building software and applications. It may be any type of system like a web-based system, operating system, or database system.")
        val quiz12 = Quiz(12L,"12) In which technique, we can refresh the dynamic content in android?","A. Java", "B. Ajax","C. Android","D. None of the Above","b","Answer: (b) Ajax\n" +
                "\n" +
                "Explanation: Using with Ajax technology, we can refresh the dynamic data in web pages.")
        val quiz13 = Quiz(13L,"13) Fragment in Android can be found through","A.  findByID()", "B. findFragmentByID()","C. getContext.findFragmentByID()","D. FragmentManager.findFragmentByID()","d","Answer: (d) FragmentManager.findFragmentByID()\n" +
                "\n" +
                "Explanation: Using FragmentManager.findFragmentByID(R.id.fragment), we can find the fragment/fragments which are placed on the layout.")
        val quiz14 = Quiz(14L,"14) How many levels of securities are there in android?","A. App level security and kernel level security", "B. Android level security","C. Java level security","D. None of the above","a","Answer: (a) App level security and kernel level security\n" +
                "\n" +
                "Explanation: Android is having two levels of securities, they are as App level security and kernel level security.")

        val quiz15 = Quiz(15L,"15) What are the main components in android?","A. Activity", "B. Services"
                ,"C. Broadcast Receiver","D. All of the above","d","Answer: (d) All of the above\n" +
                "\n" +
                "Explanation: The main components in android are Activity, services, Broadcast Receiver and content providers..")

        launch {
            context?.let {
                QuizDB(it)
                    .getQuizDao().deleteAllQuiz()
                QuizDB(it)
                    .getQuizDao().addQuizes(quiz1,quiz2,quiz3,quiz4,quiz5,quiz6,quiz7,quiz8,
                        quiz9,quiz10,quiz11,quiz12,quiz13,quiz14,quiz15)
            }
        }
    }

}