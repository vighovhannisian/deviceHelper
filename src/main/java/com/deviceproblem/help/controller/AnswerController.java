package com.deviceproblem.help.controller;

import com.deviceproblem.help.model.Answer;
import com.deviceproblem.help.repository.AnswerRepository;
import com.deviceproblem.help.repository.QuestionRepository;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.persistence.Column;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Controller
public class AnswerController {
    @Value("D:\\mvc\\")
    private String imageUploadPath;
    @Autowired
    AnswerRepository answerRepository;
    @Autowired
    private QuestionRepository questionRepository;
    @RequestMapping(value = "/answerHome",method = RequestMethod.GET)
    public String answerHome(ModelMap map){
        map.addAttribute("addAnswer",new Answer());
        return "";
    }
    @RequestMapping(value = "/getAnswerByQuestionId",method = RequestMethod.GET)
    public String getAnswerByQuestionId(ModelMap map,@RequestParam("id")int id,Answer answer){
        List<Answer> findAllBYQuestionId=answerRepository.findAllByQuestionId(id);
        map.addAttribute("findAllAnswersByQuestionId",findAllBYQuestionId);
        map.addAttribute("addAnswer",new Answer());
        answer.setQuestionId(id);
        map.addAttribute("idAnswer",answer);

        return "answerResult";
    }
    @RequestMapping(value = "/addAnswer",method = RequestMethod.POST)
    public String addAnswer(@ModelAttribute("answer")Answer answer, @AuthenticationPrincipal UserDetails userDetails, @RequestParam("questionId")int id, @RequestParam("pict")MultipartFile[] files,RedirectAttributes redirectAttributes) throws IOException {
        File dir = new File(imageUploadPath);
        if (!dir.exists()) {
            dir.mkdir();
        }
        if (userDetails==null){
            return "redirect:/logins";
        }

        for (MultipartFile file : files) {
            String picName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            File picture = new File("D:\\deviceproblems\\" + picName);
            file.transferTo(picture);
            answer.setImage(picName);
            answer.setUserUsername(userDetails.getUsername());
            answer.setQuestionId(id);
            redirectAttributes.addAttribute("id", id);
        }
        answerRepository.save(answer);

        return "redirect:/getAnswerByQuestionId";

    }
    @RequestMapping(value = "/answer/image", method = RequestMethod.GET)
    public void getImageAsByteArray(HttpServletResponse response, @RequestParam("fileName") String fileName) throws IOException {
        InputStream in = new FileInputStream("D:\\deviceproblems\\" + fileName);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        IOUtils.copy(in, response.getOutputStream());
    }
}
