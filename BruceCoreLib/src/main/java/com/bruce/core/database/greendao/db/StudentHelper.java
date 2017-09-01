package com.bruce.core.database.greendao.db;

import com.bruce.core.database.greendao.entity.Student;

import org.greenrobot.greendao.AbstractDao;

/**
 * Created by Bruce on 2017/8/17.
 * <p>
 * function:一个实体类对应一个实现类--该类是学生表(Student)的具体实现
 */

public class StudentHelper extends BaseDbHelper<Student, Long> {

    public StudentHelper(AbstractDao dao) {
        super(dao);
    }
}
