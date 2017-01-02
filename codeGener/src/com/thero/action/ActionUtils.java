package com.thero.action;

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.application.Application;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.ApplicationComponent;

/**
 * Created by thridHero on 2017/1/1.
 */
public class ActionUtils {
    /**
     * 获取application对象
     * */
    public  static Application getApplication(AnActionEvent e){
        return ApplicationManager.getApplication();
    }


}
