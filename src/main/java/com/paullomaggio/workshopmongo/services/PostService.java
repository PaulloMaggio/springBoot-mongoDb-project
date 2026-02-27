package com.paullomaggio.workshopmongo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paullomaggio.workshopmongo.domain.Post;
import com.paullomaggio.workshopmongo.repository.PostRepository;
import com.paullomaggio.workshopmongo.services.exception.ObjectNotFoundException;

@Service
public class PostService {

    @Autowired
    private PostRepository repo;

    public Post findById(String id) {
        return repo.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
    }
    
    public List<Post> findByTitle(String text){
    	return repo.findByTitleContainingIgnoreCase(text);
    }
}