package com.qfedu.controller.after;

import com.github.pagehelper.PageInfo;
import com.qfedu.entry.Speaker;
import com.qfedu.service.SpeakerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/speaker")
public class SpeakerController {

    @Autowired
    SpeakerService speakerService;
    @RequestMapping("/list")
    public String showSpeakerList(Model model, @RequestParam(defaultValue = "1") int page,@RequestParam(defaultValue = "5") int pageSize) {

        List<Speaker> speakerList = speakerService.selectAllSpeaker(page, pageSize);
        //model.addAttribute("speakerList",speakerList);

        PageInfo<Speaker> pageInfo = new PageInfo<>(speakerList);

        model.addAttribute("pageInfo",pageInfo);

        return "behind/speakerList";
    }

    @RequestMapping("/delById")
    @ResponseBody
    public String delById(Integer id) {

        System.out.println(id);
        speakerService.deleteSpeakerById(id);

            return "success";

    }


    @RequestMapping("/showAddSpeaker")
    public String showAddSpeaker() {

        return "behind/addSpeaker";
    }


    @RequestMapping("/addSpeaker")
    @ResponseBody
    public String addSpeaker(Speaker speaker) {

        System.out.println(speaker);
        System.out.println("111111111111111");


        if (speaker.getId() == 0 ) {
            boolean isAdd = speakerService.addSpeakerToDb(speaker);
            if (isAdd) {
                return "success";
            }
        } else {

          boolean isupdate =  speakerService.updateSpeakerById(speaker);
            if (isupdate){
                return "success";
            }
        }



        return "false";
    }

    @RequestMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model) {

        List<Speaker> speakerList = speakerService.selectAllSpeaker();


        for (Speaker speaker1 : speakerList) {
            if (speaker1.getId() == id) {
                model.addAttribute("speaker",speaker1);

                System.out.println("1111111111111"+speaker1);
            }
        }
        return "behind/addSpeaker";
    }



}
