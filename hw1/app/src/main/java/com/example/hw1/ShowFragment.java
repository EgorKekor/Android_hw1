package com.example.hw1;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ShowFragment extends Fragment {
    public interface showFragmentIface {
        public DigitCell getSelectedCell();
    }

    private showFragmentIface iface;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            iface = (showFragmentIface) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement showFragmentIface");
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedData) {
        DigitCell selectedCell = iface.getSelectedCell();
        View v = inflater.inflate(R.layout.show_fragment, null);

        TextView text = v.findViewById(R.id.showBlock);
        text.setBackgroundColor(selectedCell.getColor());
        text.setText(String.valueOf(selectedCell.getValue()));

        return v;
    }
}
