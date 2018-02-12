package com.okta.example.server.dao;

import com.okta.example.server.model.TieCraft;

import java.util.Collection;

public interface TieCraftDao {

    Collection<TieCraft> listTieCrafts();

    TieCraft getTieCraft(String serial);
}
