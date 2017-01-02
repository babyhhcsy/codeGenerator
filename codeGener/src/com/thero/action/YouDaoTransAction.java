package com.thero.action;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.application.Application;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.editor.CaretModel;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.SelectionModel;
import com.intellij.openapi.project.Project;
import com.thero.Exception.PluginException;
import com.thero.component.AlertComponent;
import com.thero.enums.PluginErrorEnum;
import com.thero.plugin.YouDaoTrans;

import java.io.UnsupportedEncodingException;

/**
 * Created by thridHero on 2016/12/29.
 */
public class YouDaoTransAction extends AnAction {
    @Override
    public void actionPerformed(AnActionEvent anActionEvent) {
        Editor editor = anActionEvent.getData(PlatformDataKeys.EDITOR);
        if (editor == null){
            return ;
        }
        SelectionModel selectionModel = editor.getSelectionModel();
        CaretModel caretModel = editor.getCaretModel();
        Project project = anActionEvent.getData(PlatformDataKeys.PROJECT);
        if (editor == null || project == null)
            return;
        //Application application = ApplicationManager.getApplication();

        /*AlertComponent myComponent = application.getComponent(AlertComponent.class);
        myComponent.alertSelected(selectionModel.getSelectedText());
        */

        Document document = editor.getDocument();
        //得到选中字符串的起始和结束位置
        int startOffset = selectionModel.getSelectionStart();
        int endOffset = selectionModel.getSelectionEnd();

        int maxOffset = document.getTextLength() -1;
    //计算选中字符串所在的行号，并通过行号得到下一行的第一个字符的起始偏移量
        int curLineNumber = document.getLineNumber(endOffset);
        int nextLineStartOffset = document.getLineStartOffset(curLineNumber + 1);

        Application application = ActionUtils.getApplication(anActionEvent);
        AlertComponent myComponent =new AlertComponent();

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                String SelectText = selectionModel.getSelectedText();
                if(SelectText!=null){
                    //删除选中的内容
                    document.deleteString(startOffset,endOffset);
                    try {
                        document.insertString(startOffset, YouDaoTrans.trans(SelectText));
                    } catch (UnsupportedEncodingException e) {
                        myComponent.errorMessage(PluginErrorEnum.FAIL_NOT_SUPPORT_LANGULAGE.getMsg());
                    }catch (PluginException e){
                        myComponent.errorMessage( e.getResultBean().getMsg());
                    }
                }
            }
        };
        WriteCommandAction.runWriteCommandAction(project,runnable);



    }
}
