package control;

import entity.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.ui.ModelMap;

@Controller
public class LoginControl {

    private ApplicationContext context = null;
    private UserJDBCTemplate userJDBCTemplate = null;

    public LoginControl() {
        context = new ClassPathXmlApplicationContext("Beans.xml");
        userJDBCTemplate = (UserJDBCTemplate) context.getBean("userJDBCTemplate");
    }

    /*1 chức năng sẽ có 2 ham:
    1. Lấy dữ liệu từ form về
    2. Xử lý dữ liệu(2 thằng tự gọi nhau)
     */
// /*ĐÓng gói dữ liệ trên form thnahf đối tượng modelandview*/
//    @RequestMapping(value = "/login", method = RequestMethod.GET)
//    public ModelAndView userLogin() {
//        return new ModelAndView("login", "command", new User());
//    }
//
//    /*Hai method tự chuyển qua nhau*/
//    @RequestMapping(value = "/loginCheck", method = RequestMethod.POST)
//    public String checkUser(@ModelAttribute("SpringWeb") User user, ModelMap model) {
//        //Ép kiểu ngược
//        //ModelMap như sesion trong jsp chuyển dữ liệu.
//        model.addAttribute("username", user.getUsername());
//        if (userJDBCTemplate.checkLogin(user)) {
//            return "loginsuccess";
//            /*Tên trang jsp mà nó hiển thị*/
//        }
//        return "loginerror";
//        /**/
//    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView userAdd() {
        return new ModelAndView("add", "command", new User());
    }

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public String addUser(@ModelAttribute("SpringWeb") User user, ModelMap model) {
        model.addAttribute("username", user.getUsername());
        if (userJDBCTemplate.create(user)) {
            return "addsuccess";
        }
        return "adderror";
    }

    
    
}
