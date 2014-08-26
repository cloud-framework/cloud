package com.cloud.open.web.json.base;
 
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.view.AbstractView;

import cn.egame.common.exception.ErrorCodeBase;
import cn.egame.common.exception.ExceptionCommonBase;

import com.cloud.valueobject.vo.ReturnResult;
import com.google.gson.Gson;
/**
 * <b>function:</b>扩展AbstractView 实现JSON-lib视图
 * @author hoojo
 * @createDate 2011-4-28 下午05:26:43
 * @file MappingJsonlibVeiw.java
 * @package com.hoo.veiw.xml
 * @project SpringMVC4View
 * @blog http://blog.csdn.net/IBM_hoojo
 * @email hoojo_@126.com
 * @version 1.0
 */
public class JsonView extends AbstractView {
    public static final String DEFAULT_CONTENT_TYPE = "application/json";
    public static final String HTML_CONTENT_TYPE = "text/html";
    public static final String DEFAULT_CHAR_ENCODING = "UTF-8";
    private String encodeing = DEFAULT_CHAR_ENCODING;
     
    private Object jsonData = null;
    private Map<String, Object> _jsonDataMap = new HashMap<String, Object>();
     
    public void setEncodeing(String encodeing) {
        this.encodeing = encodeing;
    }
    private Set<String> renderedAttributes;
    public JsonView() {
        setContentType(DEFAULT_CONTENT_TYPE);
    }
    public JsonView(Object data) {
        setContentType(DEFAULT_CONTENT_TYPE);
        this.jsonData = data;
    }
    @Override
    protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
    	ReturnResult result = new ReturnResult();
    	PrintWriter out = response.getWriter();
    	try {
	        response.setCharacterEncoding(encodeing);
	        response.setContentType(getContentType());
	        
	        if(jsonData != null){
	//            out.print(new Gson().toJson(jsonData));
	        	result.setExt(jsonData);
	        	out.print(new Gson().toJson(result));
	        }else if(!_jsonDataMap.isEmpty()){
	//            out.print(new Gson().toJson(_jsonDataMap));
	        	result.setExt(jsonData);
	        	out.print(new Gson().toJson(result));
	        }else {
	//            model = filterModel(model);
	//            out.print(new Gson().toJson(model));
	        	result.setExt(jsonData);
	        	out.print(new Gson().toJson(result));
	        }
    	} catch (Exception e) {
    		result.setCode(ExceptionCommonBase.parseErrorCode(e,
					ErrorCodeBase.UnDefinedDatabaseError));
			result.setText(e.getMessage());
			out.print(new Gson().toJson(result));
    	}
    }
     
    /**
     * Filters out undesired attributes from the given model.
     * <p>Default implementation removes {@link BindingResult} instances and entries not included in the {@link
     * #setRenderedAttributes(Set) renderedAttributes} property.
     */
    protected Map<String, Object> filterModel(Map<String, Object> model) {
        Map<String, Object> result = new HashMap<String, Object>(model.size());
        Set<String> renderedAttributes =
                !CollectionUtils.isEmpty(this.renderedAttributes) ? this.renderedAttributes : model.keySet();
        for (Map.Entry<String, Object> entry : model.entrySet()) {
            if (!(entry.getValue() instanceof BindingResult) && renderedAttributes.contains(entry.getKey())) {
                result.put(entry.getKey(), entry.getValue());
            }
        }
 
        return result;
 
    }
     
    public void setJsonData(Object jsonData){
        this.jsonData = jsonData;
    }
     
    public static JsonView returnJson(Object jsonData){
        JsonView jsonView = new JsonView();
        jsonView.setJsonData(jsonData);
        return jsonView;
    }
     
    public JsonView put(String key, Object value)
    {
        _jsonDataMap.put(key, value);
        return this;
    }
    
    public static void main(String[] args) {
		try {
			System.out.println("111111");
		} catch (Exception e) {
			System.out.println("222222");
		} finally{
			System.out.println("333333");
		}
		
		System.out.println("444444");
	}
}