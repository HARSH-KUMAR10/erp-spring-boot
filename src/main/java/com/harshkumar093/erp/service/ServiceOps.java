package com.harshkumar093.erp.service;

import com.harshkumar093.erp.util.Response;

import java.util.List;

public interface ServiceOps<Obj> {
    Response<Long> create(Obj obj);

    Response<List<Obj>> readAll();

    Response<Obj> read(long id);

    Response<Obj> update(Obj obj);

    Response<Obj> delete(long id);

}
