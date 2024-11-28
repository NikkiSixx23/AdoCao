package com.example.adocao.Model;

import android.text.Editable;
import android.text.TextWatcher;

public class MaskTelefone implements TextWatcher {
    private final String mask;
    private boolean isUpdating;

    public MaskTelefone(String mask) {
        this.mask = mask;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (isUpdating) return;

        String unmasked = s.toString().replaceAll("\\D", "");
        StringBuilder masked = new StringBuilder();
        int i = 0;

        for (char c : mask.toCharArray()) {
            if (c == '#' && i < unmasked.length()) {
                masked.append(unmasked.charAt(i));
                i++;
            } else if (i < unmasked.length()) {
                masked.append(c);
            }
        }

        isUpdating = true;
        ((Editable) s).replace(0, s.length(), masked);
        isUpdating = false;
    }

    @Override
    public void afterTextChanged(Editable s) {
    }
}
