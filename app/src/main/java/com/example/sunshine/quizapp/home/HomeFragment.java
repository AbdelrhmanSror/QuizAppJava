package com.example.sunshine.quizapp.home;


import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import com.example.sunshine.quizapp.R;
import com.example.sunshine.quizapp.databinding.FragmentHomeBinding;
import java.util.Objects;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        FragmentHomeBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        // get value of preference using shared preference to decide which fragment to open
        SharedPreferences preferences=PreferenceManager.getDefaultSharedPreferences(getContext());
        int value=Integer.valueOf(Objects.requireNonNull(preferences.getString(getString(R.string.category), "1")));
        switch (value)
        {
            case 1:binding.playButton.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_homeFragment_to_singleAnswerFragment));
            break;
            case 2:binding.playButton.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_homeFragment_to_mulitpleAnswerFragment));
            break;
            case 3:binding.playButton.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_homeFragment_to_textAnswerFragment));
            break;
        }


        setHasOptionsMenu(true);
        return binding.getRoot();
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.settings,menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return NavigationUI.onNavDestinationSelected(item,Navigation.findNavController(getView())) ||super.onOptionsItemSelected(item);
    }


}
