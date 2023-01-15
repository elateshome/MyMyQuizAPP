package com.example.myquizapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.quizeapp.Quiz
import com.quizeapp.QuizDB

import com.quizeapp.utils.BaseFragment
import com.quizeapp.utils.toast
import kotlinx.coroutines.launch

class HomeFragment : BaseFragment() {

    private lateinit var question_text_view: TextView
    private lateinit var score_text_view: TextView
    private var homeViewModel: ScoreViewModel? = null
    private lateinit var current_quiz: Quiz
    private lateinit var questions: List<Quiz>
    private var quesion_starting_index = 0
    private var selected_quize_choice: String? = null
    private var answers: MutableList<String> = mutableListOf()
    private lateinit var radioGroup: RadioGroup

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_home, container, false)
        val skipBtn = view.findViewById<Button>(R.id.btn_skip)
        val nextBtn = view.findViewById<Button>(R.id.btn_next)
        question_text_view = view.findViewById(R.id.tv_question)
        score_text_view = view.findViewById(R.id.tv_score)
        homeViewModel = ViewModelProvider(this).get(ScoreViewModel::class.java)
        val scoreLiveData: MutableLiveData<Int> = homeViewModel!!.initialScore()
        scoreLiveData.observe(viewLifecycleOwner) {
            score_text_view.text = String.format("%d/15", it)
        }

        //▪ launch is a function that creates a coroutine
        //and dispatches the execution of its function
        //body to the corresponding dispatcher
        //By calling this launch method, can execute
        //the DB tasks under CoroutineScope.

        launch {
            context?.let {
                questions = QuizDB(it).getQuizDao().getAllQuizes() // the DB call happen under the coroutine scope.
                changeQuestion(view)
            }
        }
        skipBtn.setOnClickListener {
            changeQuestion(view)
        }
        nextBtn.setOnClickListener {
            if (selected_quize_choice != null){
                evaluation(selected_quize_choice!!)
                changeQuestion(view)
            } else context?.toast(getString(R.string.provide_answer))

        }
        radioGroup = view.findViewById(R.id.question_radio)
        radioGroup.setOnCheckedChangeListener(this::radioGrouphandler)
        return view
    }

    private fun changeQuestion(view: View) {

        val selectedAns = if(selected_quize_choice!=null) selected_quize_choice else ""
            answers.add(selectedAns!!)


        if (quesion_starting_index == 15) {
            val action = HomeFragmentDirections.actionHomeFragmentToResultFragment(
                score = homeViewModel?.finalScore()?.value!!, answers = answers.toTypedArray()
            )
            Navigation.findNavController(requireView()).navigate(action)
            return
        }
        current_quiz = questions[quesion_starting_index]
        question_text_view.text = current_quiz.question
        val radioGroup = view.findViewById(R.id.question_radio) as RadioGroup
        val questionChoices = listOf(current_quiz.a, current_quiz.b, current_quiz.c, current_quiz.d)
        for (i in 0 until radioGroup.childCount) {
            (radioGroup.getChildAt(i) as RadioButton).text = questionChoices[i]
        }
        quesion_starting_index++
        selected_quize_choice = null
        radioGroup.clearCheck()
    }


    private fun evaluation(ans: String) {
        if (current_quiz.answer == ans) {
            homeViewModel!!.currentScore()
        }
    }

    private fun radioGrouphandler(group: RadioGroup, checkedId: Int) {
        when (checkedId) {
            R.id.q_a -> selected_quize_choice = "a"
            R.id.q_b -> selected_quize_choice = "b"
            R.id.q_c -> selected_quize_choice = "c"
            R.id.q_d -> selected_quize_choice = "d"
        }
    }


}