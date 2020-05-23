package com.meimeitech.eapi.repository;

import com.meimeitech.eapi.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface TagRepository extends JpaRepository<Tag, String>, JpaSpecificationExecutor<Tag> {

    List<Tag> findAllByProjectId(String projectId);

}
