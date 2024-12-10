package ru.example.hibernateRepeatable;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.example.hibernateRepeatable.entity.User;


/**
 * User - класс сущности для которой создается репозиторий
 * Long - тип идентификатора сущности поля. Тип поля Id
 */
public interface UserRepository extends JpaRepository<User, Long> {

    @Transactional
    @Override
    public List<UserDto> getAllUsers() {
        return repository.findAll()
                .stream()
                .map(UserDto::from)
                .collect(Collectors.toList());
    }
}
