
package com.example.myquizapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.Navigation

import com.quizeapp.utils.BaseFragment


class ResultFragment : BaseFragment() {

    private lateinit var text_view_score: TextView
    private lateinit var result_analysis_btn: Button
    private lateinit var try_again_btn: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_result, container, false)
        text_view_score = view.findViewById(R.id.tv_score)
        val score = ResultFragmentArgs.fromBundle(requireArguments()).score
        val answers = ResultFragmentArgs.fromBundle(requireArguments()).answers
        val wrongAnswers = 15 - score
        val finalScore = "$score/15"
        val Result = String.format(
            "Total Questions: 15\n\nScore: %d\n\nWrong Answer: %d\n\nYour Score is: %s",
            score, wrongAnswers, finalScore
        )
        text_view_score.text = Result
        result_analysis_btn = view.findViewById(R.id.btn_result_analysis)
        try_again_btn = view.findViewById(R.id.btn_try_again)
        try_again_btn.setOnClickListener {
            Navigation.findNavController(requireView()).navigate(R.id.action_resultFragment_to_homeFragment)
        }
        result_analysis_btn.setOnClickListener {
            val action = ResultFragmentDirections.actionResultFragmentToAnswerFragment(answers)
            Navigation.findNavController(requireView()).navigate(action)
        }
        return view
    }


}