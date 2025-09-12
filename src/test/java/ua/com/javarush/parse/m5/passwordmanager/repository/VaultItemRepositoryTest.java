package ua.com.javarush.parse.m5.passwordmanager.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ua.com.javarush.parse.m5.passwordmanager.entity.VaultItem;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class VaultItemRepositoryTest {

    @Autowired
    private VaultItemRepository repository;

    @Test
    void whenSaved_thenFindsByLogin() {
        VaultItem vaultItem = new VaultItem();
        vaultItem.setLogin("testuser");
        vaultItem.setPassword("password");
        repository.save(vaultItem);

        List<VaultItem> found = repository.findVaultItemByLogin("testuser");

        assertThat(found).hasSize(1);
        assertThat(found.getFirst().getLogin()).isEqualTo("testuser");
    }
}
