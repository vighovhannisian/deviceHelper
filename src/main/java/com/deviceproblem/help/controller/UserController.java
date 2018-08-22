package com.deviceproblem.help.controller;

import com.deviceproblem.help.model.Picture;
import com.deviceproblem.help.model.Question;
import com.deviceproblem.help.model.User;
import com.deviceproblem.help.repository.AnswerRepository;
import com.deviceproblem.help.repository.CategoryRepository;
import com.deviceproblem.help.repository.QuestionRepository;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@Controller
public class UserController {
    @Value("D:\\mvc\\")
    private String imageUploadPath;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private AnswerRepository answerRepository;
    @Autowired
    private QuestionRepository questionRepository;
    @RequestMapping(value = "/userHome",method = RequestMethod.GET)
    public String userHome(ModelMap map){
        map.addAttribute("addQuestion",new Question());
        map.addAttribute("allCategories",categoryRepository.findAll());
        return "user";
    }
    @RequestMapping(value = "/addQuestion",method = RequestMethod.POST)
    public String addQuestion(@ModelAttribute("question") Question question, @AuthenticationPrincipal UserDetails userDetails, @RequestParam(value = "pic") MultipartFile[] files) throws IOException {
        File dir = new File(imageUploadPath);
        if (!dir.exists()) {
            dir.mkdir();
        }
        for (MultipartFile file : files) {
            String picName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            File picture = new File("D:\\deviceproblems\\" + picName);
            file.transferTo(picture);
            question.getImage().setPicUrl(picName);
        }
        question.setUserUsername(userDetails.getUsername());
        questionRepository.save(question);
        return "redirect:/userHome";

    }

    @RequestMapping(value = "/question/image", method = RequestMethod.GET)
    public void getImageAsByteArray(HttpServletResponse response, @RequestParam("fileName") String fileName) throws IOException {
        InputStream in = new FileInputStream("D:\\deviceproblems\\" + fileName);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        IOUtils.copy(in, response.getOutputStream());
    }

    @GetMapping("/deleteAnswer")
    public String deleteProduct(@RequestParam("id") int id,User user,@AuthenticationPrincipal UserDetails userDetails) {

        if (user.getEmail().equals(userDetails.getUsername())) {
            answerRepository.delete(id);
        }

        return "redirect:/userHome";
    }

}
