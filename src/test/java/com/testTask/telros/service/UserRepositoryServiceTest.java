package com.testTask.telros.service;

import com.testTask.telros.model.User;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import com.testTask.telros.repository.UserRepository;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Тесты для класса UserRepositoryService.
 */
@SpringBootTest
class UserRepositoryServiceTest {

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private UserRepositoryService userService;

    /**
     * Вспомогательный метод для создания тестового пользователя.
     *
     * @return Тестовый пользователь.
     */
    private User createUser() {
        User user = new User();
        user.setId(1);
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setEmail("john.doe@example.com");
        return user;
    }

    /**
     * Тест сохранения пользователя.
     */
    @Test
    void testSaveUser() {
        // Подготовка данных
        User user = createUser();

        // Мокирование поведения репозитория
        when(userRepository.save(user)).thenReturn(user);

        // Вызов метода сервиса для сохранения пользователя
        User savedUser = userService.saveUser(user);

        // Проверка, что метод save репозитория был вызван один раз с данным пользователем
        verify(userRepository, times(1)).save(user);

        // Проверка, что возвращенный пользователь совпадает с ожидаемым
        assertEquals(user, savedUser);
    }

    /**
     * Тест получения всех пользователей.
     */
    @Test
    void testFindAllUsers() {
        // Подготовка данных
        List<User> userList = new ArrayList<>();
        userList.add(createUser());

        // Мокирование поведения репозитория
        when(userRepository.findAll()).thenReturn(userList);

        // Вызов метода сервиса для получения всех пользователей
        List<User> result = userService.findAllUser();

        // Проверка, что количество полученных пользователей соответствует ожидаемому
        assertEquals(userList.size(), result.size());
        // Проверка, что полученный пользователь совпадает с ожидаемым
        assertEquals(userList.get(0), result.get(0));
    }

    /**
     * Тест поиска пользователя по ID.
     */
    @Test
    void testFindUserById() {
        // Подготовка данных
        long userId = 1;
        User user = createUser();

        // Мокирование поведения репозитория
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        // Вызов метода сервиса для поиска пользователя по ID
        Optional<User> result = userService.findUserById(userId);

        // Проверка, что пользователь был найден
        assertTrue(result.isPresent());
        // Проверка, что полученный пользователь совпадает с ожидаемым
        assertEquals(user, result.get());
    }

    /**
     * Тест обновления пользователя.
     */
    @Test
    void testUpdateUser() {
        // Подготовка данных
        long userId = 1;
        User updatedUser = createUser();

        // Мокирование поведения репозитория
        when(userRepository.save(updatedUser)).thenReturn(updatedUser);

        // Вызов метода сервиса для обновления пользователя
        User result = userService.updateUser(userId, updatedUser);

        // Проверка, что полученный пользователь совпадает с ожидаемым
        assertEquals(updatedUser, result);
    }

    /**
     * Тест удаления пользователя.
     */
    @Test
    void testDeleteUser() {
        // Подготовка данных
        long userId = 1;

        // Вызов метода сервиса для удаления пользователя
        userService.deleteUser(userId);

        // Проверка, что метод deleteById репозитория был вызван с правильным ID
        verify(userRepository).deleteById(userId);
    }

    /**
     * Тест получения всех пользователей, когда список пустой.
     */
    @Test
    void testFindAllUsersEmptyList() {
        // Проверяем, что метод findAllUser возвращает пустой список, если в репозитории нет пользователей
        when(userRepository.findAll()).thenReturn(new ArrayList<>());

        // Вызов метода сервиса для получения всех пользователей
        List<User> result = userService.findAllUser();

        // Проверка, что список пользователей пустой
        assertTrue(result.isEmpty());
    }

    /**
     * Тест поиска пользователя по ID, когда пользователь не найден.
     */
    @Test
    void testFindUserByIdNotFound() {
        // Проверяем, что метод findUserById возвращает пустой Optional, если пользователь не найден
        long userId = 1;
        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        // Вызов метода сервиса для поиска пользователя по ID
        Optional<User> result = userService.findUserById(userId);

        // Проверка, что Optional пустой
        assertFalse(result.isPresent());
    }
}