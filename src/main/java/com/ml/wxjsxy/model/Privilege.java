package com.ml.wxjsxy.model;

import java.util.ArrayList;
import java.util.List;

public class Privilege {
    private String name;
    private List<Privilege> list = new ArrayList<Privilege>();

    public Privilege(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Privilege> getList() {
        return list;
    }

    public void setList(Privilege privilege) {
        list.add(privilege);
    }
}
