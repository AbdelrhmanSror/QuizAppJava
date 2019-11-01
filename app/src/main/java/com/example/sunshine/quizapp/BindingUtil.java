package com.example.sunshine.quizapp;

import android.widget.CheckBox;
import android.widget.RadioButton;

import androidx.databinding.BindingAdapter;

import com.example.sunshine.quizapp.singleAnswerQuestion.SingleViewModel;


public class BindingUtil {
    @BindingAdapter("restoreCheckState")
    public static void checkedRadioButton(RadioButton radioButton, SingleViewModel singleViewModel)
    {
        if(singleViewModel.getSelectedRadioId()==radioButton.getId())
        {
            radioButton.setChecked(true);
        }
    }
}
