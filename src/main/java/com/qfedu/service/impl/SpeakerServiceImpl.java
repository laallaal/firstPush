package com.qfedu.service.impl;

import com.github.pagehelper.PageHelper;
import com.qfedu.entry.Speaker;
import com.qfedu.mapper.SpeakerMapper;
import com.qfedu.service.SpeakerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpeakerServiceImpl implements SpeakerService {

    @Autowired
    SpeakerMapper mapper;


    @Override
    public List<Speaker> selectAllSpeaker() {

        List<Speaker> list = mapper.selectAllSpeaker();

        return list;


    }

    @Override
    public List<Speaker> selectAllSpeaker(int page, int pageSize) {


        PageHelper.startPage(page,pageSize);

        List<Speaker> list = mapper.selectAllSpeaker();

        for (Speaker speaker : list) {
            System.out.println(speaker);
        }

        return list;


    }

    @Override
    public boolean deleteSpeakerById(Integer id) {

        int rows = mapper.deleteSpeakerById(id);

        return rows > 0 ? true : false;
    }

    @Override
    public boolean addSpeakerToDb(Speaker speaker) {

        int rows = mapper.addSpeakerToDb(speaker);

        return rows > 0 ? true : false;
    }

    @Override
    public boolean updateSpeakerById(Speaker speaker) {

        int rows = mapper.updateSpeakerById(speaker);

        return rows > 0 ? true : false;
    }


}
