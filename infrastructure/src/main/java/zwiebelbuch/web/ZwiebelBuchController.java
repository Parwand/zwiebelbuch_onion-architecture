package zwiebelbuch.web;

import druckservice.BuchService;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import zwiebelbuch.buch.Buch;

@Controller
public class ZwiebelBuchController implements WebMvcConfigurer {
  private final BuchService buchService;

  public ZwiebelBuchController(BuchService buchService) {
    this.buchService = buchService;
  }

  @GetMapping("/")
  public String index(Model model){
    List<Buch> buchList = buchService.findAll();
    model.addAttribute("buchList", buchList);
    return "index";
  }
  @PostMapping("/druck")
  public String drucken(String buch) {
    UUID id = UUID.fromString(buch);
    boolean erfolg = buchService.drucken(id);
    if (erfolg) return "redirect:/ok";
    return "redirect:/failed";
  }

  @PostMapping("/freigeben")
  public String freigeben(String buchId, String uuid, RedirectAttributes redirectAttributes) {
    buchService.freigeben(uuid);
    redirectAttributes.addAttribute("buchId", buchId);
    return "redirect:/freigabe";
  }

  @Override
  public void addViewControllers(ViewControllerRegistry registry) {
    registry.addViewController("/ok").setViewName("ok");
    registry.addViewController("/failed").setViewName("failed");
  }
  @GetMapping("/freigabe")
  public String ok(@ModelAttribute("buchId") String buchId, Model m){
    m.addAttribute("buchId", buchId);
    return "freigabe";
  }

  @GetMapping("/kapitels")
  public String kapitels(String buchId, Model model){
    Buch buch = buchService.getBuch(UUID.fromString(buchId));
    model.addAttribute("buch", buch);
    return "kapitels";
  }

}
