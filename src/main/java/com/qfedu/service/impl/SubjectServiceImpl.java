package com.qfedu.service.impl;

import com.qfedu.entry.Subject;
import com.qfedu.mapper.SubjectMapper;
import com.qfedu.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectServiceImpl implements SubjectService {


    @Autowired
    SubjectMapper mapper;

    @Override
    public List<Subject> selectAllSubject() {

        List<Subject> subjectList = mapper.selectAllSubject();

        return subjectList;
    }

    @Override
    public Subject selectSubjectById(int id) {

        Subject subject = mapper.selectSubjectById(id);

        return subject;
    }

}
