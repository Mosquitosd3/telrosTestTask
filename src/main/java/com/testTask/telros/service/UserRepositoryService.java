package com.testTask.telros.service;

import com.testTask.telros.model.User;
import org.springframework.stereotype.Service;
import com.testTask.telros.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Сервисный компонент для работы с пользователями через UserRepository.
 */
@Service
public class UserRepositoryService {
    private UserRepository repository;

    /**
     * Конструктор класса.
     *
     * @param repository Репозиторий пользователей для взаимодействия с базой данных.
     */
    public UserRepositoryService(UserRepository repository) {
        this.repository = repository;
    }

    /**
     * Сохраняет пользователя в базе данных.
     *
     * @param user Пользователь для сохранения.
     * @return Сохраненный пользователь.
     */
    public User saveUser(User user) {
        return repository.save(user);
    }

    /**
     * Возвращает всех пользователей из базы данных.
     *
     * @return Список всех пользователей.
     */
    public List<User> findAllUser() {
        List<User> rsl = new ArrayList<>();
        repository.findAll().forEach(rsl::add);
        return rsl;
    }

    public Optional<User> userFindByEmail(String email) {
        return repository.findByEmail(email);
    }

    /**
     * Находит пользователя по его ID.
     *
     * @param id ID пользователя.
     * @return Пользователь, если найден, иначе пустое значение.
     */
    public Optional<User> findUserById(long id) {
        return repository.findById(id);
    }

    /**
     * Обновляет информацию о пользователе.
     *
     * @param id   ID пользователя.
     * @param user Пользователь с обновленными данными.
     * @return Обновленный пользователь.
     */
    public User updateUser(long id, User user) {
        user.setId(id);
        return repository.save(user);
    }

    /**
     * Удаляет пользователя из базы данных по его ID.
     *
     * @param id ID пользователя для удаления.
     */
    public void deleteUser(long id) {
        repository.deleteById(id);
    }
}