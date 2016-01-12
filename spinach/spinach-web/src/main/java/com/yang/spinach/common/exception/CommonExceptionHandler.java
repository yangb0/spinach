package com.yang.spinach.common.exception;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by CPR116 on 2016-01-12.
 */
public class CommonExceptionHandler implements HandlerExceptionResolver {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public ModelAndView resolveException(HttpServletRequest request,
                                         HttpServletResponse response, Object handler, Exception ex) {
        ModelAndView modelAndView = new ModelAndView();
        // TODO Auto-generated method stub
        String uri = request.getRequestURI();
        Map map = request.getParameterMap();
       String paramJson =  JSON.toJSONString(map);
        String msg = "请求:  "+uri+" \r\n 参数:  "+paramJson +"\r\n";
        logger.error(msg,ex);

        Map<String,Object> result = new HashMap<String, Object>();
        result.put("msg", "服务器抽风了!!");
        result.put("status",500);
        if (ex instanceof NumberFormatException) {
            //doSomething...
            result.put("msg", "数据转换错误!!");
        } else if (ex instanceof NullPointerException) {
            //doSomething...
        }

        //根据annotation 判断请求是返回数据还是页面
        HandlerMethod ha = (HandlerMethod)handler;
        Boolean isJsonResult = false;
        if(ha!=null){
            Method method = ha.getMethod();
            ResponseBody r = method.getAnnotation(ResponseBody.class);
            if(r!=null){
                isJsonResult=true;
            }
        }
        if(isJsonResult){
            MappingJackson2JsonView jsonView = new MappingJackson2JsonView();
            jsonView.setAttributesMap(result);
            modelAndView.setView(jsonView);
        }else{
            modelAndView.setViewName("/common/500");
            modelAndView.addAllObjects(result);
        }
        return modelAndView;
    }


}
