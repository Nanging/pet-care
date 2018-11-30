package com.stu.petc.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.stu.petc.beans.User;
import com.stu.petc.service.UserRedisService;

@Controller
public class UserController {
//    @Autowired
//    private UserRedisService userRedisService;
///** 
//     * @Title: UserController
//     * @Description: 初始化redis数据
//     * @return  
//     * @author mengfanzhu
//     * @throws 
//     */
//    @RequestMapping("/user/initRedisdata")
//    @ResponseBody
//    public String initRedisData(){
//        userRedisService.redisInitData();
//        return "success";
//    }
//    @RequestMapping("/user/getUserRedisByLoginName/{loginName}")
//    @ResponseBody
//    public Map<String,Object> getUserRedisByLoginName(@PathVariable String loginName){
//        Map<String,Object> result = new HashMap<String, Object>();
//        User user = userRedisService.getUserRedis(loginName);
//        if (null==user) {
//			System.out.println("[null user]");
//		}
//        result.put("name", user.getUsername());
//        result.put("role", user.getUser_role());
////        result.put("departmentName",user.getDepartment().getName());
////        result.put("roleName", user.getRoleList().get(0).getName());
//        return result;
//    }
}
