package com.qfedu.mapper;


import com.qfedu.entry.Subject;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface SubjectMapper {



    @Select("select * from subject")
    @Results(id = "subjectMap",
            value = {
            @Result(id = true, property = "id", column = "id"),
            @Result( property = "subjectName", column = "subject_name"),
            }
    )
    List<Subject> selectAllSubject();


    @Select("select * from subject where id = #{id}")
    @ResultMap("subjectMap")
    Subject selectSubjectById(int id);
}
