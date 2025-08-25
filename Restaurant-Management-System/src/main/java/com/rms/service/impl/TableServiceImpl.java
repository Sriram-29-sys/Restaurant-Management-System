package com.rms.service.impl;

import com.rms.entity.TableEntity;
import com.rms.repository.TableRepository;
import com.rms.service.TableService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class TableServiceImpl implements TableService {

    private final TableRepository tableRepository;

    public TableServiceImpl(TableRepository tableRepository) {
        this.tableRepository = tableRepository;
    }

    @Override
    public TableEntity addTable(TableEntity table) {
        return tableRepository.save(table);
    }

    @Override
    public Optional<TableEntity> findByTableNumber(int tableNumber) {
        return tableRepository.findByTableNumber(tableNumber);
    }

    @Override
    public List<TableEntity> getAllTables() {
        return tableRepository.findAll();
    }

    @Override
    public TableEntity updateTableStatus(Long id, TableEntity.Status status) {
        return tableRepository.findById(id)
                .map(t -> {
                    t.setStatus(status);
                    return tableRepository.save(t);
                }).orElseThrow(() -> new RuntimeException("Table not found"));
    }
}
