package com.deviceproblem.help.controller;

import com.deviceproblem.help.model.Question;
import com.deviceproblem.help.model.Sentence;
import com.deviceproblem.help.model.User;
import com.deviceproblem.help.model.UserType;
import com.deviceproblem.help.repository.CategoryRepository;
import com.deviceproblem.help.repository.QuestionRepository;
import com.deviceproblem.help.repository.UserRepository;
import com.deviceproblem.help.security.CurrentUser;
import com.deviceproblem.help.util.EmailServiceImpl;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

@Controller
public class MainController {
    private User user;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private QuestionRepository questionRepository;
    @Value("D:\\mvc\\")
    private String imageUploadPath;
    @Autowired
    private EmailServiceImpl emailService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private CategoryRepository categoryRepository;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String main(ModelMap map) {
        map.addAttribute("allCategories", categoryRepository.findAll());
        map.addAttribute("allQuestions", questionRepository.findAll());
        return "index";

    }

    @RequestMapping(value = "/getQuestionByCategory", method = RequestMethod.GET)
    public String getProduct(ModelMap map, @RequestParam("category") String category) {
        List<Question> products = questionRepository.findAllByCategoryName(category);
        map.addAttribute("questionByCategory", products);
        return "categoryResult";

    }

    @RequestMapping(value = "/loginSuccess", method = RequestMethod.GET)
    public String loginSuccess() {

        CurrentUser principal = (CurrentUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal.getUser().getUserType() == UserType.ADMIN) {
            return "redirect:/userHome";
        } else {
            if (principal.getUser().getUserType() == UserType.USER) {
                return "redirect:/userHome";
            }
            return "redirect:/";
        }
    }

    @RequestMapping(value = "/loginPage", method = RequestMethod.GET)
    public String login() {
        if (user == null) {
            return "loginError";

        }

        return null;
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String registration(ModelMap map) {
        map.addAttribute("addUser", new User());
        return "registerHome";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String register(@ModelAttribute(name = "user") User user, @RequestParam(value = "image") MultipartFile file) throws IOException {
        File dir = new File(imageUploadPath);
        if (!dir.exists()) {
            dir.mkdir();
        }
        String picName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        File picture = new File("D:\\deviceproblems\\" + picName);
        file.transferTo(picture);
        user.setImage(picName);
        user.setUserType(UserType.USER);
        user.setToken(UUID.randomUUID().toString());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        String url = String.format("http://localhost:8080/verify?token=%s&email=%s", user.getToken(), user.getEmail());
        String text = String.format("Dear %s Thank you, you have successfully registered to our Device Problem, Please visit by link in order to activate your profile. %s", user.getName(), url);
        emailService.sendSimpleMessage(user.getEmail(), "Welcome", text);
        return "redirect:/register";
    }

    @RequestMapping(value = "/verify", method = RequestMethod.GET)
    public String verify(@RequestParam("token") String token, @RequestParam("email") String email) {
        User oneByEmail = userRepository.findOneByEmail(email);
        if (oneByEmail != null) {
            if (oneByEmail.getToken() != null && oneByEmail.getToken().equals(token)) {
                oneByEmail.setToken(null);
                oneByEmail.setVerify(true);
                userRepository.save(oneByEmail);
            }
        }
        return "redirect:/";
    }

    @RequestMapping(value = "/user/image", method = RequestMethod.GET)
    public void getImageAsByteArray(HttpServletResponse response, @RequestParam("fileName") String fileName) throws IOException {
        InputStream in = new FileInputStream("D:\\deviceproblems\\" + fileName);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        IOUtils.copy(in, response.getOutputStream());
    }

    @RequestMapping(value = "logins", method = RequestMethod.GET)
    public String log() {
        return "login";
    }

}
