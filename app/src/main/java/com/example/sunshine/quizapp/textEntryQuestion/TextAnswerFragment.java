package com.example.sunshine.quizapp.textEntryQuestion;


import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.sunshine.quizapp.R;
import com.example.sunshine.quizapp.databinding.FragmentTextAnswerBinding;

/**
 * A simple {@link Fragment} subclass.
 */
public class TextAnswerFragment extends Fragment {
    FragmentTextAnswerBinding binding;
    TextAnswerViewModel textAnswerViewModel;

    public TextAnswerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         binding= DataBindingUtil.inflate(inflater,R.layout.fragment_text_answer, container, false);
         textAnswerViewModel= ViewModelProviders.of(this).get(TextAnswerViewModel.class);
         binding.setGame(textAnswerViewModel);
         nextListener();
         submitListener();
        return binding.getRoot();
    }
    private void nextListener() {
        /*
         * listener for next button clicks
         */
        binding.next.setOnClickListener(v ->
        {
            if (textAnswerViewModel.isGameFinished()) {
                binding.next.setText(getString(R.string.next_button));
                textAnswerViewModel.setGameFinished(false);
            }
            String answer=binding.answerText.getText().toString();
            if(!TextUtils.isEmpty(answer))
            {
                textAnswerViewModel.checkCorrectAnswer(answer);
                if (textAnswerViewModel.nextQuestion()) {
                    textAnswerViewModel.setCurrentQuestion();
                    /*
                     * telling data biding that the data has changed and it should accommodate it
                     */
                    binding.invalidateAll();
                    binding.answerText.setText(null);
                } else
                    Toast.makeText(getContext(), getString(R.string.nextMessage), Toast.LENGTH_LONG).show();

            } else
                Toast.makeText(getContext(), getString(R.string.NoAnswer), Toast.LENGTH_LONG).show();


        });
    }
    private void submitListener() {
        binding.submit.setOnClickListener(v ->
        {
            //set the the next button text to try again
            // to give the user freedom to reset game if he wanted to after game has finished
            binding.next.setText(getString(R.string.tryAgain));
            Toast.makeText(getContext(), getString(R.string.ResultMessage, textAnswerViewModel.getNumOfCorrectAnswers()), Toast.LENGTH_LONG).show();
            textAnswerViewModel.reset();
            textAnswerViewModel.setGameFinished(true);
        });

    }
}
