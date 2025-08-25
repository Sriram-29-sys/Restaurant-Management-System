package com.rms.service;

import com.rms.entity.TableEntity;
import java.util.List;
import java.util.Optional;

public interface TableService {
	TableEntity addTable(TableEntity table);

	Optional<TableEntity> findByTableNumber(int tableNumber);

	List<TableEntity> getAllTables();

	TableEntity updateTableStatus(Long id, TableEntity.Status status);
}
