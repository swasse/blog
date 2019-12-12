package be.ehb.blog.controller;

import be.ehb.blog.model.BlogPost;
import be.ehb.blog.model.BlogRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.validation.Valid;

/**
 * @author David Van steertegem
 * @version 0.0.1
* */

@Controller
public class IndexController {
    @Autowired
    private BlogRepo repo;

    @ModelAttribute("all")
    public Iterable<BlogPost> findAll(){
        return repo.findAllChronological();
    }

    @ModelAttribute("newPost")
    public BlogPost toSave(){
        return new BlogPost();
    }

    /**
     * method from <b>GET</b> http request to open index
     * @return shows the index page
     * */
    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public String showIndex(ModelMap map){
        return "index";
    }

    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.POST)
    public String save(@ModelAttribute("newPost") @Valid BlogPost newPost,
                       BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "index";
        }
        repo.save(newPost);
        return "redirect:/index";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete( @PathVariable(value = "id") int id){
        repo.deleteById(id);
        return "redirect:/index";
    }
}