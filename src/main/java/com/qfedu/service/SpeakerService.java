package com.qfedu.service;

import com.qfedu.entry.Speaker;

import java.util.List;

public interface SpeakerService {
    List<Speaker> selectAllSpeaker();


    List<Speaker> selectAllSpeaker(int page, int pageSize);

    boolean deleteSpeakerById(Integer id);

    boolean addSpeakerToDb(Speaker speaker);


    boolean updateSpeakerById(Speaker speaker);
}
