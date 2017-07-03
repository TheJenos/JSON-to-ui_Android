package com.thanura.jsontoui;

import android.view.View;

/**
 * Created by Thanura on 7/2/2017.
 */

public abstract class _onClick implements View.OnClickListener {
    public String script = "";
    _onClick(String Script){
        this.script = Script;
    }
}
