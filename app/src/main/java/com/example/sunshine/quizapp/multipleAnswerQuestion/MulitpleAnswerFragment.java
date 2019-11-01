package com.example.sunshine.quizapp.multipleAnswerQuestion;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sunshine.quizapp.R;
import com.example.sunshine.quizapp.databinding.FragmentQuestionMutlipleBinding;

import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 */
public class MulitpleAnswerFragment extends Fragment {

    private FragmentQuestionMutlipleBinding binding;
    private MulipleViewModel mulipleViewModel;


    public MulitpleAnswerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_question_mutliple, container, false);
        mulipleViewModel = ViewModelProviders.of(Objects.requireNonNull(getActivity())).get(MulipleViewModel.class);
        binding.setGame(mulipleViewModel);
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
            //checking if there is at least one check box is selected
            if (binding.checkBox.isChecked() || binding.checkBox1.isChecked() || binding.checkBox2.isChecked() || binding.checkBox3.isChecked()) {
                if (mulipleViewModel.isGameFinished()) {
                    binding.next.setText(getString(R.string.next_button));
                    mulipleViewModel.setGameFinished(false);
                }
                mulipleViewModel.checkCorrectAnswer();
                if (mulipleViewModel.nextQuestion()) {
                    mulipleViewModel.setCurrentQuestion();
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
            Toast.makeText(getContext(), getString(R.string.ResultMessage, mulipleViewModel.getNumOfCorrectAnswers()), Toast.LENGTH_LONG).show();
            mulipleViewModel.reset();
            mulipleViewModel.setGameFinished(true);
        });

    }

}
