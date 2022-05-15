package com.wxjsxy.factory;

import com.wxjsxy.dao.StudentDao;
import com.wxjsxy.idao.IServer;
import com.wxjsxy.server.StudentServer;

public class Factory {

    public static IServer addStudent()
    {
        return new StudentDao();
    }

    public static StudentServer addStudentServer()
    {
        return new StudentServer();
    }
}
