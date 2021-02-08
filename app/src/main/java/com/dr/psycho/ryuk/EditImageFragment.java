package com.dr.psycho.ryuk;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;

import com.dr.psycho.ryuk.Interfaces.EditImageFragmentListener;

public class EditImageFragment extends Fragment implements SeekBar.OnSeekBarChangeListener {

    private EditImageFragmentListener listener;
    SeekBar seekbar_brightness,seekbar_contrast,seekbar_saturation;

    public EditImageFragment(){

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View itemView = inflater.inflate(R.layout.fragment_edit_image, container, false);

        seekbar_brightness = (SeekBar) itemView.findViewById(R.id.seekbar_brightness);
        seekbar_contrast = (SeekBar) itemView.findViewById(R.id.seekbar_contrast);
        seekbar_saturation = (SeekBar) itemView.findViewById(R.id.seekbar_saturation);

        seekbar_brightness.setMax(200);
        seekbar_brightness.setProgress(100);

        seekbar_contrast.setMax(20);
        seekbar_contrast.setProgress(0);

        seekbar_saturation.setMax(30);
        seekbar_saturation.setProgress(10);

        seekbar_brightness.setOnSeekBarChangeListener(this);
        seekbar_contrast.setOnSeekBarChangeListener(this);
        seekbar_saturation.setOnSeekBarChangeListener(this);

        return  itemView;
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        if (listener != null)
        {
            if (seekBar.getId() == R.id.seekbar_brightness){
                listener.onBrightnessChanged(progress-100);
            }
            else if (seekBar.getId() == R.id.seekbar_contrast){
                progress += 10;
                float value = .10f*progress;
                listener.onContrastChanged((int) value);
            }
            else if (seekBar.getId() == R.id.seekbar_saturation){
                float value = .10f*progress;
                listener.onSaturationChanged((int) value);
            }
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        if (listener != null)
            listener.onEditStart();
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        if (listener != null)
            listener.onEditComplete();
    }

    public void resetControls(){
        seekbar_brightness.setProgress(100);
        seekbar_contrast.setProgress(0);
        seekbar_saturation.setProgress(10);
    }
}