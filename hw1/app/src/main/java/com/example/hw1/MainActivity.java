package com.example.hw1;

import androidx.appcompat.app.AppCompatActivity;


import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.io.Serializable;

public class MainActivity extends AppCompatActivity
        implements ListFragment.listFragmentListener, ShowFragment.showFragmentIface {

    private DigitCell selectedCell = new DigitCell(0);
    private static final String LIST_FRAGMENT = "listFragment";
    private static final String SELECTED_CELL = "selectedCell";

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

        if (savedInstanceState == null) {
            listFrag = new ListFragment();
            FragmentTransaction fTrans = getFragmentManager().beginTransaction();
            fTrans.add(R.id.listFrame, listFrag);
            fTrans.commit();
        } else {
            listFrag = (ListFragment) getFragmentManager().getFragment(savedInstanceState, LIST_FRAGMENT);
            selectedCell = new DigitCell(savedInstanceState.getInt(SELECTED_CELL));
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        getFragmentManager().putFragment(outState, LIST_FRAGMENT, listFrag);
        outState.putInt(SELECTED_CELL, selectedCell.getValue());
    }

}
