package pl.dawid.springbootopenidwithgoogle.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Controller
public class GetInfo {

    @GetMapping("/login_panel")
    public String loginInfo(Model model, OAuth2AuthenticationToken authentication){

        Map<String, Object> userInfo = authentication.getPrincipal().getAttributes();

        String picture;
        String email;
        String name;

        if(authentication.getAuthorizedClientRegistrationId().equals("github")){
            picture = userInfo.get("avatar_url").toString();
            name = userInfo.get("login").toString();

            model.addAttribute("picture", picture);
            model.addAttribute("name", name);

        }else if(authentication.getAuthorizedClientRegistrationId().equals("google")){
            picture = userInfo.get("picture").toString();
            email = userInfo.get("email").toString();
            name = userInfo.get("name").toString();

            model.addAttribute("picture", picture);
            model.addAttribute("email", email);
            model.addAttribute("name", name);
        }

        return  "login-panel";
    }
}
