package guru.springframework.controllers;

import guru.springframework.commands.RecipeCommand;
import guru.springframework.exceptions.NotFoundException;
import guru.springframework.services.RecipeService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class RecipeController {

    RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping
    @RequestMapping("/recipe/{id}/show")
    public String showById(@PathVariable String id, Model model){
        model.addAttribute("recipe", recipeService.findById(new Long(id)));

        return "recipe/show";
    }

    @GetMapping
    @RequestMapping("/recipe/new")
    public String newRecipe(Model model){
        model.addAttribute("recipe", new RecipeCommand());

        return "recipe/recipeform";
    }

    @GetMapping
    @RequestMapping("/recipe/{id}/update")
    public String updateRecipe(@PathVariable String id, Model model){
        model.addAttribute("recipe",  recipeService.findCommandById(new Long(id)));

        return "recipe/recipeform";
    }

    @PostMapping
    @RequestMapping("recipe")
    public String saveOrUpdate(@Valid @ModelAttribute("recipe") RecipeCommand command, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            bindingResult.getAllErrors().forEach(objectError -> System.out.println(objectError.toString()));
            return "recipe/recipeform";
        }

        RecipeCommand savedCommand = recipeService.saveRecipeCommand(command);

        return "redirect:/recipe/" +savedCommand.getId() +"/show";
    }

    @GetMapping
    @RequestMapping("recipe/{id}/delete")
    public String deleteRecipe(@PathVariable String id){
        recipeService.deleteById(new Long(id));

        return "redirect:/index";
    }
}
