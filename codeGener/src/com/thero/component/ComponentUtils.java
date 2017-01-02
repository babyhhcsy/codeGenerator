package com.thero.component;

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.components.ApplicationComponent;
import com.thero.action.ActionUtils;

/**
 * Created by thridHero on 2017/1/1.
 */
public class ComponentUtils<T extends ApplicationComponent> {
    private T t;
    public T getErroCommpment(AnActionEvent e){
        return (T)ActionUtils.getApplication(e).getComponent(t.getClass());
    }

}
