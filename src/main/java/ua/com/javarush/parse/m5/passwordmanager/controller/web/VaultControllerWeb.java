package ua.com.javarush.parse.m5.passwordmanager.controller.web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.com.javarush.parse.m5.passwordmanager.entity.VaultItem;
import ua.com.javarush.parse.m5.passwordmanager.service.VaultItemService;

import java.util.Optional;

@Controller
@RequestMapping("/vault-item")
@RequiredArgsConstructor
public class VaultControllerWeb {

  private final VaultItemService vaultItemService;

  @GetMapping("/{id}")
  public String get(@PathVariable Long id, Model model) {
    Optional<VaultItem> byId = vaultItemService.findById(id);
    byId.ifPresent(vaultItem -> {
      model.addAttribute("vault", vaultItem);
    });
    return "vault";
  }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Optional<VaultItem> byId = vaultItemService.findById(id);
        if (byId.isPresent()) {
            model.addAttribute("vault", byId.get());
            return "edit-vault";
        }
        return "redirect:/";
    }

    @PostMapping("/update/{id}")
    public String updateItem(@PathVariable Long id, @ModelAttribute("vault") VaultItem itemFromForm) {
        vaultItemService.update(id, itemFromForm);
        return "redirect:/";
    }
}
