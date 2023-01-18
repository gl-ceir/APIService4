package com.ceir.CeirCode.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ceir.CeirCode.model.SubFeature;

public interface SubFeatureRepo extends JpaRepository<SubFeature, Long>{

	public List<SubFeature> findAll();
}
