package com.a1.controller;


import com.a1.model.Login;
import com.a1.model.Posting;
import com.a1.repository.LoginRepo;
import com.a1.repository.PostingRepo;
import com.a1.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
public class MainController {
    @Autowired
    LoginRepo loginRepo;
    @Autowired
    PostingRepo postingRepo;

    @PostConstruct
    public void init(){
       List<Login> loginList = FileService.parseLogins();
       List<Posting> postingList = FileService.getPostings(loginList);
       loginList.forEach(s-> loginRepo.save(s));
       postingList.forEach(s-> postingRepo.save(s));
    }

    @GetMapping("/")
    public String greeting(Map<String,Object> model){
        return "greeting";
    }

    @GetMapping("/month")
    public String month(Map<String,Object> model, @RequestParam String search, @RequestParam String filter){
        List<Posting> postings = postingRepo.findByMonth(search);
        model.put("search",search);
        model.put("url", "/month");
        model.put("result",filter(filter,postings));
        return "main";
    }

    @GetMapping("/day")
    public String day(Map<String,Object> model, @RequestParam String search, @RequestParam String filter){
        List<Posting> postings = postingRepo.findByDay(search);
        model.put("search",search);
        model.put("url", "/day");
        model.put("result",filter(filter,postings));
        return "main";
    }


    @GetMapping("/year")
    public String year(Map<String,Object> model, @RequestParam String search, @RequestParam String filter){
        List<Posting> postings = postingRepo.findByYear(search);
        model.put("search",search);
        model.put("url", "/year");
        model.put("result",filter(filter,postings));
        return "main";
    }


    @GetMapping("/quarter")
    public String quarter(Map<String,Object> model, @RequestParam String search, @RequestParam String filter){
        String[] month = quarter(search);
        List<Posting> postings = postingRepo.findByQuarter(month[0],month[1]);
        model.put("search",search);
        model.put("url", "/quarter");
        model.put("result",filter(filter,postings));
        return "main";
    }


    public static List filter(String filterReq, List<Posting> list){
       // boolean filter = Boolean.parseBoolean(filterReq);
        if (filterReq.equals("true")){
            list.removeIf(s-> s.isAuthorizedDelivery()!=true);
        }
        return list;
    }


    public static String[] quarter(String i){
        String[] month = new String[2];
        if(i.equals("1")){
            month[0] = "1";
            month[1] = "3";
            return month;
        }
        if(i.equals("2")){
            month[0] = "4";
            month[1] = "6";
            return month;
        }
        if(i.equals("3")){
            month[0] = "7";
            month[1] = "9";
            return month;
        }else{
            month[0] = "10";
            month[1] = "12";
            return month;
        }
    }
}
