package com.dr.psycho.ryuk.Interfaces;

public interface EditImageFragmentListener {
    void onBrightnessChanged(int brightness);
    void onSaturationChanged(int saturation);
    void onContrastChanged(int contrast);
    void onEditStart();
    void onEditComplete();
}
