import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.application.Application;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.editor.Editor;
import com.thero.component.AlertComponent;

/**
 * Created by thridHero on 2016/12/27.
 */
public class SayHelloAction extends AnAction {

    @Override

    public void actionPerformed(AnActionEvent e) {

        Application application = ApplicationManager.getApplication();

        AlertComponent myComponent = application.getComponent(AlertComponent.class);

        myComponent.sayHello();

    }

    @Override
    public void update(AnActionEvent e) {
        Editor editor = e.getData(PlatformDataKeys.EDITOR);
        if(editor!=null){
            e.getPresentation().setEnabled(true);
        }else{
            e.getPresentation().setEnabled(false);
        }
    }
}
