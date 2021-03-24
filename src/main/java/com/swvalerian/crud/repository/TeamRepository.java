package com.swvalerian.crud.repository;

import com.swvalerian.crud.model.Team;

import java.io.File;
import java.util.List;

public class TeamRepository implements TeamRepositoryInterface {
    final private File file = new File("src\\main\\resources\\files\\teams.txt");

    @Override
    public List<Team> getAll() {
        return null;
    }

    @Override
    public Long getId(Long aLong) {
        return null;
    }
}
