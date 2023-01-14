package com.khem.appspring.springphoneshop.service.serviceimpl;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.Predicate;

import org.apache.commons.collections4.MapUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.khem.appspring.springphoneshop.Util.PageUtil;
import com.khem.appspring.springphoneshop.exception.ResourceNotFoundException;
import com.khem.appspring.springphoneshop.model.Model;
import com.khem.appspring.springphoneshop.repository.ModelRepository;
import com.khem.appspring.springphoneshop.service.ModelService;
import com.khem.appspring.springphoneshop.specification.ModelFilter;
import com.khem.appspring.springphoneshop.specification.ModelSpecification;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ModelServiceimpl implements ModelService {

    private final ModelRepository modelRepository;

    // private final BrandService brandService;

    @Override
    public Model save(Model entity) {

        
        // brandService.getById(entity.getBrand.getId());
        // Model model = ModelMapper.INSTANCE.toModel(entity);
        return modelRepository.save(entity);
    }

    @Override
    public Model getById(Long id) {
        return modelRepository.findById(id)
                // .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND,String.format("model
                // not found for id=%d", id)));
                .orElseThrow(() -> new ResourceNotFoundException("Model", id));

    }
    /*
     * @Override
     * public List<Model> getModel(Map<String, String> param) {
     * ModelFilter modelFilter = new ModelFilter();
     * if(param.containsKey("modelId")){
     * modelFilter.setModelId(MapUtils.getInteger(param, "modelId"));
     * }
     * if(param.containsKey("modelName")){
     * modelFilter.setModelName(MapUtils.getString(param, "modelName"));
     * 
     * }
     * if(param.containsKey("brandId")){
     * modelFilter.setBrandId(MapUtils.getInteger(param, "brandId"));
     * }
     * if(param.containsKey("brandName")){
     * modelFilter.setBrandName(MapUtils.getString(param, "brandName"));
     * }
     * 
     * ModelSpecification modelSpecification = new ModelSpecification(modelFilter);
     * 
     * return modelRepository.findAll(modelSpecification,Sort.by(Order.asc("id")));
     * }
     */

    public List<Model> getModel_Old(Map<String, String> param) {
        /*
         * Specification<Model> specification = new Specification<Model>() {
         * 
         * @Override
         * public Predicate toPredicate(Root<Model> model, CriteriaQuery<?> query,
         * CriteriaBuilder cb) {
         * if(param.containsKey("name")){
         * String modelName = param.get("name");
         * Predicate like = cb.like(model.get("name"),"%" +modelName+"%");
         * return like;
         * }
         * 
         * return null;
         * }
         * 
         * };
         */
        Specification<Model> specification = (model, query, cb) -> {
            if (param.containsKey("name")) {
                String modelName = param.get("name");
                Predicate like = cb.like(model.get("name"), "%" + modelName + "%");
                return like;
            }
            return null;
        };
        return modelRepository.findAll(specification, Sort.by(Order.asc("id")));

    }



    @Override
    public Page<Model> getModels(Map<String, String> param) {

        Pageable pageable = PageUtil.getPageable(param);

        ModelFilter modelFilter = new ModelFilter();
        if (param.containsKey("modelId")) {
            modelFilter.setModelId(MapUtils.getInteger(param, "modelId"));
        }
        if (param.containsKey("modelName")) {
            modelFilter.setModelName(MapUtils.getString(param, "modelName"));

        }
        if (param.containsKey("brandId")) {
            modelFilter.setBrandId(MapUtils.getInteger(param, "brandId"));
        }
        if (param.containsKey("brandName")) {
            modelFilter.setBrandName(MapUtils.getString(param, "brandName"));
        }

        ModelSpecification modelSpecification = new ModelSpecification(modelFilter);

        Page<Model> page = modelRepository.findAll(modelSpecification, pageable);

        page.getNumberOfElements();
        return page;
    }

}
