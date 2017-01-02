package com.thero.action;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.editor.CaretModel;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.SelectionModel;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.TextRange;
import com.thero.Exception.PluginException;
import com.thero.component.AlertComponent;
import com.thero.enums.PluginErrorEnum;
import com.thero.log.Logger;
import com.thero.plugin.YouDaoTrans;
import com.thero.plugin.youdao.bean.TranslationBean;
import com.thero.plugin.youdao.bussiness.TransService.TransService;
import com.thero.popupBalloon.TPopUpBallon;
import org.apache.http.util.TextUtils;

import java.io.UnsupportedEncodingException;

/**
 * Created by thridHero on 2017/1/1.
 */
public class ECTranslation extends AnAction {
    private long latestClickTime;
    @Override
    public void actionPerformed(AnActionEvent e) {
        getTranslation(e);
    }
    /*翻译*/
    private void getTranslation(AnActionEvent event) {
        Project project = event.getData(PlatformDataKeys.PROJECT);
        Editor editor = event.getData(PlatformDataKeys.EDITOR);
        if (editor == null){
            return ;
        }
        if (editor == null || project == null)
            return;
        Editor mEditor = event.getData(PlatformDataKeys.EDITOR);
        if (null == mEditor) {
            return;
        }
        SelectionModel model = mEditor.getSelectionModel();
        String selectedText = model.getSelectedText();
        if (TextUtils.isEmpty(selectedText)) {
            selectedText = getCurrentWords(mEditor);
            if (TextUtils.isEmpty(selectedText)) {
                return;
            }
        }
        String queryText = strip(addBlanks(selectedText));
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                TranslationBean translationBean = TransService.doService(queryText);
                try {
                    TPopUpBallon.showPopupBalloon(editor,translationBean.toString());
                } catch (PluginException e) {
                    AlertComponent alertComponent = new AlertComponent();
                    alertComponent.errorMessage(e.getResultBean().getMsg());
                }
                Logger.info(translationBean.toString());
            }
        };
        /**
         * 如果想让runnable起到作用，就必须将该线程添加到action中
         * */
        WriteCommandAction.runWriteCommandAction(project,runnable);
    }
    /**
     * 获得当前选中的文字
     * */
    public String getCurrentWords(Editor editor) {
        Document document = editor.getDocument();
        CaretModel caretModel = editor.getCaretModel();
        int caretOffset = caretModel.getOffset();
        int lineNum = document.getLineNumber(caretOffset);
        int lineStartOffset = document.getLineStartOffset(lineNum);
        int lineEndOffset = document.getLineEndOffset(lineNum);
        String lineContent = document.getText(new TextRange(lineStartOffset, lineEndOffset));
        char[] chars = lineContent.toCharArray();
        int start = 0, end = 0, cursor = caretOffset - lineStartOffset;

        if (!Character.isLetter(chars[cursor])) {
            Logger.warn("Caret not in a word");
            return null;
        }

        for (int ptr = cursor; ptr >= 0; ptr--) {
            if (!Character.isLetter(chars[ptr])) {
                start = ptr + 1;
                break;
            }
        }

        int lastLetter = 0;
        for (int ptr = cursor; ptr < lineEndOffset - lineStartOffset; ptr++) {
            lastLetter = ptr;
            if (!Character.isLetter(chars[ptr])) {
                end = ptr;
                break;
            }
        }
        if (end == 0) {
            end = lastLetter + 1;
        }

        String ret = new String(chars, start, end-start);
        Logger.info("Selected words: " + ret);
        return ret;
    }

    public String addBlanks(String str) {
        String temp = str.replaceAll("_", " ");
        if (temp.equals(temp.toUpperCase())) {
            return temp;
        }
        String result = temp.replaceAll("([A-Z]+)", " $0");
        return result;
    }

    public String strip(String str) {
        return str.replaceAll("/\\*+", "")
                .replaceAll("\\*+/", "")
                .replaceAll("\\*", "")
                .replaceAll("//+", "")
                .replaceAll("\r\n", " ")
                .replaceAll("\\s+", " ");
    }

    public boolean isFastClick(long timeMillis) {
        long time = System.currentTimeMillis();
        long timeD = time - latestClickTime;
        if (0 < timeD && timeD < timeMillis) {
            return true;
        }
        latestClickTime = time;
        return false;
    }
}
