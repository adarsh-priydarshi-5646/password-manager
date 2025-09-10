package ua.com.javarush.parse.m5.passwordmanager.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.com.javarush.parse.m5.passwordmanager.entity.VaultItem;
import ua.com.javarush.parse.m5.passwordmanager.repository.VaultItemRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VaultItemService {

  private final VaultItemRepository vaultItemRepository;

  public VaultItem save(VaultItem vaultItem) {
    return vaultItemRepository.save(vaultItem);
  }

  public List<VaultItem> findAll() {
    return vaultItemRepository.findAll();
  }

  public Optional<VaultItem> findById(Long id){
    return vaultItemRepository.findById(id);
  }

  public List<VaultItem> findByLogin(String login){
    return vaultItemRepository.findVaultItemByLogin(login);
  }

  public List<VaultItem> findByResource(String resource){ return  vaultItemRepository.findVaultItemByResource(resource);}

  public void deleteById(Long id){
    vaultItemRepository.deleteById(id);
  }

}
