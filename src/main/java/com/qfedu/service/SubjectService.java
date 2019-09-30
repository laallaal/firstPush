package com.qfedu.service;

import com.qfedu.entry.Subject;

import java.util.List;

public interface SubjectService {

    List<Subject> selectAllSubject();


    Subject selectSubjectById(int id);
}
