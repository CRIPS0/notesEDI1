package com.IK.notes.Controllers;

import com.IK.notes.Models.Post;
import com.IK.notes.Repo.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class MainController
{
    @Autowired
    private PostRepository postRepository;

    @GetMapping("/")
    public String notesMai(Model model)
    {
        Iterable<Post> posts = postRepository.findAll();
        model.addAttribute("posts", posts);
        return "notes";
    }

    @GetMapping("/notes")
    public String notesMain(Model model)
    {
        Iterable<Post> posts = postRepository.findAll();
        model.addAttribute("posts", posts);
        return "notes";
    }

    @PostMapping("/")
    public String notePostAdd(@RequestParam String title, @RequestParam String mtext, Model model)
    {
        Post post = new Post(title, mtext);
        postRepository.save(post);
        return "redirect:/";
    }

    @GetMapping("/notes/{id}")
    public String notesDetalis(@PathVariable(value = "id") long id, Model model)
    {
        if(!postRepository.existsById(id))
        {
            return "redirect:/notes";
        }
        Optional <Post> post = postRepository.findById(id);
        ArrayList <Post> res = new ArrayList<>();
        post.ifPresent(res::add);
        model.addAttribute("post", res);
        return "notes-detalis";
    }

    @GetMapping("/notes/{id}/edit")
    public String notesEdit(@PathVariable(value = "id") long id, Model model)
    {
        if(!postRepository.existsById(id))
        {
            return "redirect:/notes";
        }
        Optional <Post> post = postRepository.findById(id);
        ArrayList <Post> res = new ArrayList<>();
        post.ifPresent(res::add);
        model.addAttribute("post", res);
        return "notes-edit";
    }




    @PostMapping("filter")
    public String filter(@RequestParam String filter, Model model)
    {
        List<Post> result = postRepository.findByTitleContainingOrMtextContaining(filter, filter);
        model.addAttribute("posts", result);
            return "notes";
    }
}
