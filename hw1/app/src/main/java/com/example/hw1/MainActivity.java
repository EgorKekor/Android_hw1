package com.example.hw1;

import androidx.appcompat.app.AppCompatActivity;


import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity
        implements ListFragment.listFragmentListener, ShowFragment.showFragmentIface {

    private DigitCell selectedCell;

    @Override
    public DigitCell getSelectedCell() {
        return selectedCell;
    }


    @Override
    public void cellClick(DigitCell cell) {
        selectedCell = cell;
        showFrag = new ShowFragment();
        FragmentTransaction fTrans = getFragmentManager().beginTransaction();
        fTrans.replace(R.id.listFrame, showFrag);
        fTrans.addToBackStack(null);
        fTrans.commit();
    }

    ListFragment listFrag;
    ShowFragment showFrag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listFrag = new ListFragment();

        FragmentTransaction fTrans = getFragmentManager().beginTransaction();
        fTrans.add(R.id.listFrame, listFrag);
        fTrans.commit();
    }
}
