package com.game.service.impl;

import com.game.dto.PlayerDto;
import com.game.entity.Player;
import com.game.exception.BadRequestException;
import com.game.exception.PlayerNotFoundException;
import com.game.mapper.PlayerMapper;
import com.game.repository.PlayerRepository;
import com.game.service.PlayerService;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class PlayerServiceImpl implements PlayerService {

  private final PlayerRepository playerRepository;

  public PlayerServiceImpl(PlayerRepository playerRepository) {
    this.playerRepository = playerRepository;
  }

  @Override
  public List<PlayerDto> getPlayers(Specification<Player> specification, PageRequest page) {
    return playerRepository.findAll(specification, page).getContent().stream()
        .map(PlayerMapper::playerToDto).collect(Collectors.toList());
  }

  @Override
  public PlayerDto getPlayer(String id) {
    return PlayerMapper.playerToDto(getPlayerById(id));
  }

  @Override
  public PlayerDto savePlayer(PlayerDto playerDto) {
    if (playerDto.getName() == null
        || playerDto.getTitle() == null
        || playerDto.getRace() == null
        || playerDto.getBirthday() == null
        || playerDto.getProfession() == null
        || playerDto.getExperience() == null) {
      throw new BadRequestException("Please fill in all required fields");
    }
    validatePlayer(playerDto);
    if (playerDto.getBanned() == null) {
      playerDto.setBanned(false);
    }
    Player player = PlayerMapper.dtoToPlayer(playerDto);

    Integer level = calculateLevel(player);
    player.setLevel(level);
    Integer untilNextLevel = calculateUntilNextLevel(player);
    player.setUntilNextLevel(untilNextLevel);

    return PlayerMapper.playerToDto(playerRepository.save(player));
  }

  @Override
  public PlayerDto updatePlayer(String id, PlayerDto playerDto) {
    validatePlayer(playerDto);

    Player changedPlayer = getPlayerById(id);

    if (playerDto.getName() != null) {
      changedPlayer.setName(playerDto.getName());
    }

    if (playerDto.getBirthday() != null) {
      changedPlayer.setBirthday(playerDto.getBirthday());
    }

    if (playerDto.getLevel() != null) {
      changedPlayer.setLevel(playerDto.getLevel());
    }

    if (playerDto.getTitle() != null) {
      changedPlayer.setTitle(playerDto.getTitle());
    }

    if (playerDto.getBanned() != null) {
      changedPlayer.setBanned(playerDto.getBanned());
    }

    if (playerDto.getExperience() != null) {
      changedPlayer.setExperience(playerDto.getExperience());
    }

    if (playerDto.getProfession() != null) {
      changedPlayer.setProfession(playerDto.getProfession());
    }
    if (playerDto.getRace() != null) {
      changedPlayer.setRace(playerDto.getRace());
    }

    Integer level = calculateLevel(changedPlayer);
    changedPlayer.setLevel(level);
    Integer untilNextLevel = calculateUntilNextLevel(changedPlayer);
    changedPlayer.setUntilNextLevel(untilNextLevel);

    return PlayerMapper.playerToDto(playerRepository.save(changedPlayer));
  }

  @Override
  public void deletePlayer(String id) {
    playerRepository.delete(getPlayerById(id));
  }

  @Override
  public Integer getPlayersCount(Specification<Player> specification) {
    return playerRepository.findAll(specification).size();
  }

  private Player getPlayerById(String id) {
    return playerRepository.findById(isValidId(id))
        .orElseThrow(() -> new PlayerNotFoundException("Player is not found"));
  }

  private Long isValidId(String id) {
    try {
      long longId = Long.parseLong(id);
      if (longId <= 0) {
        throw new Exception();
      }
      return longId;
    } catch (Exception e) {
      throw new BadRequestException("ID is incorrect");
    }
  }

  private void validatePlayer(PlayerDto playerDto) {
    if (playerDto.getName() != null && (playerDto.getName().length() < 1
        || playerDto.getName().length() > 12)) {
      throw new BadRequestException("The player name is too long or absent");
    }

    if (playerDto.getTitle() != null && playerDto.getTitle().length() > 30) {
      throw new BadRequestException("The title is too long or absent");
    }

    if (playerDto.getExperience() != null && (playerDto.getExperience() < 0
        || playerDto.getExperience() > 10000000)) {
      throw new BadRequestException("The Experience size is out of range");
    }

    if (playerDto.getBirthday() != null) {
      Calendar date = Calendar.getInstance();
      date.setTime(playerDto.getBirthday());
      if (date.get(Calendar.YEAR) < 2000 || date.get(Calendar.YEAR) > 3000) {
        throw new BadRequestException("The date of player Birthday is out of range");
      }
    }
  }

  private Integer calculateLevel(Player player) {
    BigDecimal level = BigDecimal.valueOf(
        (Math.sqrt(2500 + 200 * player.getExperience()) - 50) / 100);
    return level.intValue();
  }

  private Integer calculateUntilNextLevel(Player player) {
    BigDecimal untilNextLevel = new BigDecimal(
        50 * (player.getLevel() + 1) * (player.getLevel() + 2)
            - player.getExperience());
    return untilNextLevel.intValue();
  }
}
