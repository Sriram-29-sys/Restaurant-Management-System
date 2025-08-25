package com.rms.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rms.entity.TableEntity;

public interface TableRepository extends JpaRepository<TableEntity, Long> {
	Optional<TableEntity> findByTableNumber(int tableNumber);
}
