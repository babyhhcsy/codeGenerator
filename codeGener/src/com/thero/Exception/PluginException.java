package com.thero.Exception;

import com.thero.bean.ResultBean;
import com.thero.enums.PluginErrorEnum;

/**
 * Created by thridHero on 2017/1/1.
 */
public class PluginException extends RuntimeException{
    public ResultBean resultBean;
    public PluginException() {
    }

    public PluginException(String msg) {
    }

    public PluginException( int state,String msg) {
    }
    public PluginException(PluginErrorEnum pluginErrorEnum) {
        resultBean =new ResultBean(pluginErrorEnum.getState(),pluginErrorEnum.getMsg());
    }
    public ResultBean getResultBean(){
        return resultBean;
    }
}
