package com.laureanray.codesimulatorandchecker.ui.root;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.laureanray.codesimulatorandchecker.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SubmittedFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SubmittedFragment extends Fragment {
    public SubmittedFragment() { }

    public static SubmittedFragment newInstance() {
        return new SubmittedFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.submitted_fragment, container, false);
    }
}
