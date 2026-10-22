package com.oficioya.worker.service;

import com.oficioya.worker.exception.BusinessRuleException;
import com.oficioya.worker.mapper.TradeCategoryMapper;
import com.oficioya.worker.model.TradeCategoryModel;
import com.oficioya.worker.repository.TradeCategoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TradeCategoryService {

    private static final Logger log = LoggerFactory.getLogger(TradeCategoryService.class);

    private final TradeCategoryRepository categoryRepository;
    private final TradeCategoryMapper categoryMapper;

    public TradeCategoryService(TradeCategoryRepository categoryRepository,
                                TradeCategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    public List<TradeCategoryModel> findAll() {
        log.debug("Listing trade categories");
        return categoryMapper.toModelList(categoryRepository.findAll());
    }

    public TradeCategoryModel create(TradeCategoryModel model) {
        if (categoryRepository.findByNameIgnoreCase(model.getName()).isPresent()) {
            log.warn("Trade category already exists: {}", model.getName());
            throw new BusinessRuleException("Trade category already exists: " + model.getName());
        }
        model.setId(null);
        TradeCategoryModel saved = categoryMapper.toModel(
                categoryRepository.save(categoryMapper.toEntity(model)));
        log.info("Trade category created with id: {}", saved.getId());
        return saved;
    }
}