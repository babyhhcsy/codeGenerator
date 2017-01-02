package com.thero.plugin.youdao.bussiness.TransService;

import com.google.gson.Gson;
import com.thero.Exception.PluginException;
import com.thero.enums.PluginErrorEnum;
import com.thero.log.Logger;
import com.thero.plugin.youdao.bean.TranslationBean;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by thridHero on 2017/1/1.
 */
public class TransService {
    private static final String HOST = "fanyi.youdao.com";
    private static final String PATH = "/openapi.do";
    private static final String PARAM_KEY_FROM = "keyfrom";
    private static final String PARAM_KEY = "key";
    private static final String PARAM_TYPE = "type";
    private static final String TYPE = "data";
    private static final String PARAM_DOC_TYPE = "doctype";
    private static final String DOC_TYPE = "json";
    private static final String PARAM_CALL_BACK = "callback";
    private static final String CALL_BACK = "show";
    private static final String PARAM_VERSION = "version";
    private static final String VERSION = "1.1";
    private static final String PARAM_QUERY = "q";
    //replace your own key, see http://fanyi.youdao.com/openapi?path=data-mode
    private static final String KEY_FROM = "intellijTrans";
    private static final String KEY = "961536271";
    /**
     * 使用有道接口翻译内容，返回TranslationBean对象
     * */
    public static TranslationBean doService( String mQuery) {
        try {
            URI uri = createTranslationURI(mQuery);
            RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(5000).setConnectTimeout(5000)
                    .setConnectionRequestTimeout(5000).build();
            HttpGet httpGet = new HttpGet(uri);
            httpGet.setConfig(requestConfig);
            HttpClient client = HttpClients.createDefault();
            HttpResponse response = client.execute(httpGet);
            int status = response.getStatusLine().getStatusCode();
            if (status >= 200 && status < 300) {
                HttpEntity resEntity = response.getEntity();
                String json = EntityUtils.toString(resEntity, "UTF-8");
                Gson gson = new Gson();
                TranslationBean translation = gson.fromJson(json, TranslationBean.class);
                Logger.info(translation.toString());
                return translation;
            } else {
                return null;
            }
        } catch (IOException e) {
            throw new PluginException(PluginErrorEnum.FAIL_IOEXCEPTION.getState(), PluginErrorEnum.FAIL_IOEXCEPTION.getMsg());
        } catch (URISyntaxException e) {
            throw new PluginException(PluginErrorEnum.FAIL_URISYNTAXEXCEPTION.getState(), PluginErrorEnum.FAIL_URISYNTAXEXCEPTION.getMsg());
        }
    }
    public static URI createTranslationURI(String query) throws URISyntaxException {
        URIBuilder builder = new URIBuilder();
        builder.setScheme("http")
                .setHost(HOST)
                .setPath(PATH)
                .addParameter(PARAM_KEY_FROM, KEY_FROM)
                .addParameter(PARAM_KEY, KEY)
                .addParameter(PARAM_TYPE, TYPE)
                .addParameter(PARAM_VERSION, VERSION)
                .addParameter(PARAM_DOC_TYPE, DOC_TYPE)
                .addParameter(PARAM_CALL_BACK, CALL_BACK)
                .addParameter(PARAM_QUERY, query);
        return builder.build();
    }
}
