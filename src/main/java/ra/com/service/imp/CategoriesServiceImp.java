package ra.com.service.imp;

import ra.com.entity.Categories;
import ra.com.repository.CategoriesRepository;
import ra.com.repository.imp.CategoriesRepositoryImp;
import ra.com.service.CategoriesService;

import java.util.List;

public class CategoriesServiceImp implements CategoriesService {
    private CategoriesRepository categoriesRepository;

    public CategoriesServiceImp() {
        categoriesRepository = new CategoriesRepositoryImp();
    }

    @Override
    public List<Categories> findAll() {
        return categoriesRepository.findAll();
    }

    @Override
    public Categories findById(int catalogId) {
        return categoriesRepository.findById(catalogId);
    }

    @Override
    public boolean save(Categories catalog) {
        return categoriesRepository.save(catalog);
    }

    @Override
    public boolean update(Categories catalog) {
        return categoriesRepository.update(catalog);
    }

    @Override
    public boolean delete(int catalogId) {
        return categoriesRepository.delete(catalogId);
    }
}
