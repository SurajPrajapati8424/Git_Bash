package com.dr.psycho.ryuk.Interfaces;

public interface EditImageFragmentListener {
    void onBrightnessChanged(int brightness);
    void onSaturationChanged(int saturation);
    void onConstratsChanged(int constrats);
    void onEditStart();
    void onEditComplete();
}
