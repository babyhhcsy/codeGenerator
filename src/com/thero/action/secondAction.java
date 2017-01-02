//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.thero.action;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.editor.CaretModel;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.SelectionModel;
import com.intellij.openapi.project.Project;
import com.thero.plugin.YouDaoTrans;

public class secondAction extends AnAction {
    public secondAction() {
    }

    public void actionPerformed(AnActionEvent anActionEvent) {
        Editor editor = (Editor)anActionEvent.getData(PlatformDataKeys.EDITOR);
        if(editor != null) {
            final SelectionModel selectionModel = editor.getSelectionModel();
            CaretModel caretModel = editor.getCaretModel();
            Project project = (Project)anActionEvent.getData(PlatformDataKeys.PROJECT);
            if(editor != null && project != null) {
                final Document document = editor.getDocument();
                final int startOffset = selectionModel.getSelectionStart();
                final int endOffset = selectionModel.getSelectionEnd();
                int maxOffset = document.getTextLength() - 1;
                int curLineNumber = document.getLineNumber(endOffset);
                document.getLineStartOffset(curLineNumber + 1);
                Runnable runnable = new Runnable() {
                    public void run() {
                        String SelectText = selectionModel.getSelectedText();
                        if(SelectText != null) {
                            document.deleteString(startOffset, endOffset);
                            document.insertString(startOffset, YouDaoTrans.trans(SelectText));
                        }

                    }
                };
                WriteCommandAction.runWriteCommandAction(project, runnable);
            }
        }
    }
}
