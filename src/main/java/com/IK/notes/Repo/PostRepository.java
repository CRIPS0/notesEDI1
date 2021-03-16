package com.IK.notes.Repo;

import com.IK.notes.Models.Post;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface PostRepository extends CrudRepository <Post, Long>
{
    List<Post> findByTitleContainingOrMtextContaining(String title, String mtext);

}
