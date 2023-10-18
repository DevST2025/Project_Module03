package bussiness.service.imp;

import bussiness.config.IOFile;
import bussiness.entity.Catalog;
import bussiness.entity.User;
import bussiness.service.ICatalogService;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class CatalogService implements ICatalogService {
    List<Catalog> catalogs;

    public CatalogService() {
        this.catalogs = IOFile.readFromFile(IOFile.CATALOG_PATH);
    }

    @Override
    public long getNewId() {
        return catalogs.stream().map(Catalog::getCatalogId).max(Comparator.naturalOrder()).orElse(0L) + 1;
    }

    @Override
    public List<Catalog> findCatalogByName(String name) {
        return catalogs.stream().filter(catalog -> catalog.getCatalogName().toLowerCase().contains(name.toLowerCase())).collect(Collectors.toList());
    }

    @Override
    public List<Catalog> findAll() {
        return catalogs;
    }

    @Override
    public Catalog findById(Long id) {
        return catalogs.stream().filter(catalog -> catalog.getCatalogId() == id).findFirst().orElse(null);
    }

    @Override
    public boolean save(Catalog catalog) {
        if (findById(catalog.getCatalogId()) != null) {
            // User is Exist
            catalogs.set(catalogs.indexOf(findById(catalog.getCatalogId())), catalog);
        } else {
            // Add new account
            catalogs.add(catalog);
        }
        //Add to file
        IOFile.writeToFile(IOFile.CATALOG_PATH, catalogs);
        return true;
    }

    @Override
    public void delete(Long id) {
        catalogs.remove(findById(id));
        //Update file
        IOFile.writeToFile(IOFile.CATALOG_PATH, catalogs);
    }
}
