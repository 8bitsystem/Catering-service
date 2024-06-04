package com.Catering_Server.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Catering_Server.Entity.Items;

public interface ItemsRepository extends JpaRepository<Items, Long> {
	
}
