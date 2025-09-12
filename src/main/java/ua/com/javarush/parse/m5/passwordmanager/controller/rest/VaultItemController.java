package ua.com.javarush.parse.m5.passwordmanager.controller.rest;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.javarush.parse.m5.passwordmanager.entity.VaultItem;
import ua.com.javarush.parse.m5.passwordmanager.service.VaultItemService;

import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/vault")
public class VaultItemController {

  private final VaultItemService vaultItemService;

  @PostMapping("/create")
  public ResponseEntity<VaultItem> save(@RequestBody VaultItem item) {
    VaultItem save = vaultItemService.save(item);
    return new ResponseEntity<>(save, HttpStatus.CREATED);
  }

  @GetMapping("/{id}")
  public ResponseEntity<VaultItem> findById(@PathVariable Long id) {
    Optional<VaultItem> item = vaultItemService.findById(id);
    return item.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
  }

    @PutMapping("/{id}")
    public ResponseEntity<VaultItem> update(@PathVariable Long id, @RequestBody VaultItem updatedItemData) {
        return vaultItemService.update(id, updatedItemData)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

  @GetMapping
  public ResponseEntity<List<VaultItem>> findByLogin(@RequestParam String login) {
    List<VaultItem> byLogin = vaultItemService.findByLogin(login);

    if (byLogin.isEmpty()) {
      return ResponseEntity.notFound().build();
    }

    return ResponseEntity.ok(byLogin);
  }

  @GetMapping("/resource")
  public ResponseEntity<List<VaultItem>> findByResource(@RequestParam String resource) {
    List<VaultItem> byResource = vaultItemService.findByResource(resource);
    if (byResource.isEmpty()) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(byResource);
  }

  @GetMapping("/all")
  public ResponseEntity<List<VaultItem>> getAll() {
    return new ResponseEntity<>(vaultItemService.findAll(), HttpStatus.OK);
  }

  @DeleteMapping("{id}")
  public ResponseEntity<VaultItem> delete(@PathVariable Long id) {
    vaultItemService.deleteById(id);

    return ResponseEntity.ok().build();
  }
}
