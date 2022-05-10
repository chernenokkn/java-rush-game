package com.game.service;

import com.game.dto.PlayerDto;
import com.game.entity.Player;
import java.util.List;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;

/**
 * Сервис для работы с игроками
 */
public interface PlayerService {


  /**
   * Получение списка игроков
   *
   * @param specification Поиск
   * @param page          Страницы
   */
  List<PlayerDto> getPlayers(Specification<Player> specification, PageRequest page);

  /**
   * Получение игрока
   *
   * @param id Идентификатор игрока
   */
  PlayerDto getPlayer(String id);

  /**
   * Добавление игрока
   *
   * @param playerDto Игрок
   */
  PlayerDto savePlayer(PlayerDto playerDto);

  /**
   * Редактирование игрока
   *
   * @param id        Идентификатор игрока
   * @param playerDto Игрок
   */
  PlayerDto updatePlayer(String id, PlayerDto playerDto);

  /**
   * Удаление игрока
   *
   * @param id Идентификатор игрока
   */
  void deletePlayer(String id);

  /**
   * Получение количество игроков
   *
   * @param specification Поиск
   */
  Integer getPlayersCount(Specification<Player> specification);
}
