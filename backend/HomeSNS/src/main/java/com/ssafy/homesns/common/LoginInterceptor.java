package com.ssafy.homesns.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.ssafy.homesns.dto.UserDto;

@Component
public class LoginInterceptor implements HandlerInterceptor{ // HandlerInterceptorAdapter 는 deprecated
	@Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		System.out.println(">>>>>>>>>>>>>>>" + request.getRequestURI());
		
        HttpSession session = request.getSession();
        UserDto userDto = (UserDto) session.getAttribute("userDto");
        
        // 우리는 REST API를 쓰기때문에 OPTIONS를 쓰지 않지만, 브라우저가 알아서 쓰는 것이다
        // CORS문제 관련 REQUEST요청을 할때, OPTIONS로 물어본다
        // 이것이 막혀있으면, 요청 결과가 제대로 넘어가지 않을 것이다
        // 그렇기 때문에, 항상 브라우저가 발생시키는 options request를 무조건 수락한다
        if ( request.getMethod().equals("OPTIONS") ) {
        	return true;
        }
        if( userDto == null ) {
        	
        	Gson gson = new Gson();

			JsonObject jsonObject = new JsonObject();
			jsonObject.addProperty("result", "login");
			
			String jsonStr = gson.toJson(jsonObject);
			response.getWriter().write(jsonStr);

        	return false;
        }

        return true;
    }
}
