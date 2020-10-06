package com.hcl.traning.restfulwebservices.dao;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hcl.traning.restfulwebservices.model.Post;
import com.hcl.traning.restfulwebservices.model.User;

/**
 * @author Manjeet Kumar
 *
 * Jun 28, 2020
 */

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {

}
