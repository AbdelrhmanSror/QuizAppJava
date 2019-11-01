package com.example.sunshine.quizapp.singleAnswerQuestion;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.sunshine.quizapp.R;
import com.example.sunshine.quizapp.databinding.FragmentQuestionSingleBinding;

import java.util.Objects;


/**
 * A simple {@link Fragment} subclass.
 */
public class SingleAnswerFragment extends Fragment {

    private FragmentQuestionSingleBinding binding;
    private SingleViewModel singleViewModel;
    private int selectedAnswerId;

    public SingleAnswerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment using dataBinding
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_question_single, container, false);
        singleViewModel = ViewModelProviders.of(Objects.requireNonNull(getActivity())).get(SingleViewModel.class);

        //restore state of radio button if configurations happened
        binding.setGame(singleViewModel);
        nextListener();
        submitListener();
        radioButtonListener();
        return binding.getRoot();
    }


    private void nextListener() {
        /*
         * listener for next button clicks
         */
        binding.next.setOnClickListener(v ->
        {
            if (singleViewModel.isGameFinished()) {
                binding.next.setText(getString(R.string.next_button));
                singleViewModel.setGameFinished(false);
            }
            /*
             * get the selected radio button id to decide which answer has been selected
             */
            int selectedRadioId = binding.questionRadioGroup.getCheckedRadioButtonId();
            if (selectedRadioId != -1) {
                switch (selectedRadioId) {
                    case R.id.firstAnswerRadioButton:
                        selectedAnswerId = 0;
                        break;
                    case R.id.secondAnswerRadioButton:
                        selectedAnswerId = 1;
                        break;
                    case R.id.thirdAnswerRadioButton:
                        selectedAnswerId = 2;
                        break;
                    case R.id.fourthAnswerRadioButton:
                        selectedAnswerId = 3;
                }
                singleViewModel.checkCorrectAnswer(selectedAnswerId);
                if (singleViewModel.nextQuestion()) {
                    singleViewModel.setCurrentQuestion();
                    /*
                     * telling data biding that the data has changed and it should accommodate it
                     */
                    binding.invalidateAll();
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
            Toast.makeText(getContext(), getString(R.string.ResultMessage, singleViewModel.getNumOfCorrectAnswers()), Toast.LENGTH_LONG).show();
            singleViewModel.reset();
            singleViewModel.setGameFinished(true);
        });

    }

    private void radioButtonListener() {
        /*
         * listener for changes of checked radio button to store
         * the selected radio id in viewModel so if configuration changes have happened i can restore it easily
         */

        binding.questionRadioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            singleViewModel.setSelectedRadioId(checkedId);
        });
    }

}
