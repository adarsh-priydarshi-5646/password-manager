package ua.com.javarush.parse.m5.passwordmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.com.javarush.parse.m5.passwordmanager.entity.VaultItem;

public interface VaultItemRepository extends JpaRepository<VaultItem, Long> {
}
