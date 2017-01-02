package com.thero.component;

import com.intellij.openapi.components.ApplicationComponent;
import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.ui.Messages;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

/**
 * Created by thridHero on 2016/12/27.
 */
public class AlertComponent implements ApplicationComponent,Configurable {
    public AlertComponent() {

    }

    public void initComponent() {

        // TODO: insert component initialization logic here

    }

    public void disposeComponent() {
        // TODO: insert component disposal logic here
    }

    @NotNull
    public String getComponentName() {

        return "AlertComponent";
    }

    public void sayHello() {

        // Show dialog with message

        Messages.showMessageDialog(

                "Hello World!",

                "Sample",

                Messages.getInformationIcon()

        );
    }

    public void errorMessage(String errorMessage) {

        // Show dialog with message

        Messages.showMessageDialog(

                errorMessage,

                "错误！",

                Messages.getInformationIcon()

        );
    }
    public void alertSelected(String value){
        Messages.showMessageDialog(value,"Sample",Messages.getInformationIcon());
    }
    @Nls
    @Override
    public String getDisplayName() {
        return null;
    }

    @Nullable
    @Override
    public String getHelpTopic() {
        return null;
    }

    @Nullable
    @Override
    public JComponent createComponent() {
        return null;
    }

    @Override
    public boolean isModified() {
        return false;
    }

    @Override
    public void apply() throws ConfigurationException {

    }

    @Override
    public void reset() {

    }

    @Override
    public void disposeUIResources() {

    }
}
