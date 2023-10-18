package bussiness.service;

import bussiness.entity.Catalog;
import bussiness.entity.User;

import java.util.List;

public interface ICatalogService extends IGeneric<Catalog, Long> {
    long getNewId();
    List<Catalog> findCatalogByName(String name);
}
