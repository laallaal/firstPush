package com.qfedu.mapper;

import com.qfedu.entry.Speaker;

import java.util.List;

public interface SpeakerMapper {

    List<Speaker> selectAllSpeaker();

    int deleteSpeakerById(Integer id);

    int addSpeakerToDb(Speaker speaker);

    int updateSpeakerById(Speaker speaker);
}
