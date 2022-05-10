package com.game.util;

import com.game.controller.PlayerOrder;
import com.game.entity.Player;
import com.game.entity.Profession;
import com.game.entity.Race;
import java.util.Date;
import org.springframework.data.jpa.domain.Specification;

public class PlayerSpecification {

  public static Specification<Player> nameFilter(String name) {
    return (root, query, criteriaBuilder) -> name == null ? null
        : criteriaBuilder.like(root.get("name"), "%" + name + "%");
  }

  public static Specification<Player> titleFilter(String title) {
    return (root, query, criteriaBuilder) -> title == null ? null
        : criteriaBuilder.like(root.get("title"), "%" + title + "%");
  }

  public static Specification<Player> professionFilter(Profession profession) {
    return (root, query, criteriaBuilder) -> profession == null ? null
        : criteriaBuilder.equal(root.get("profession"), profession);
  }

  public static Specification<Player> raceFilter(Race race) {
    return (root, query, criteriaBuilder) -> race == null ? null
        : criteriaBuilder.equal(root.get("race"), race);
  }

  public static Specification<Player> bannedFilter(Boolean isBanned) {
    return (root, query, criteriaBuilder) -> {
      if (isBanned == null) {
        return null;
      }
      if (isBanned) {
        return criteriaBuilder.isTrue(root.get("banned"));
      } else {
        return criteriaBuilder.isFalse(root.get("banned"));
      }
    };
  }

  public static Specification<Player> levelFilter(Double min, Double max) {
    return (root, query, criteriaBuilder) -> {
      if (min == null && max == null) {
        return null;
      }
      if (min == null) {
        return criteriaBuilder.lessThanOrEqualTo(root.get("level"), max);
      }
      if (max == null) {
        return criteriaBuilder.greaterThanOrEqualTo(root.get("level"), min);
      }
      return criteriaBuilder.between(root.get("level"), min, max);
    };
  }

  public static Specification<Player> experienceFilter(Double min, Double max) {
    return (root, query, criteriaBuilder) -> {
      if (min == null && max == null) {
        return null;
      }
      if (min == null) {
        return criteriaBuilder.lessThanOrEqualTo(root.get("experience"), max);
      }
      if (max == null) {
        return criteriaBuilder.greaterThanOrEqualTo(root.get("experience"), min);
      }
      return criteriaBuilder.between(root.get("experience"), min, max);
    };
  }

  public static Specification<Player> dateFilter(Long after, Long before) {
    return (root, query, criteriaBuilder) -> {
      if (after == null && before == null) {
        return null;
      }
      if (after == null) {
        Date before1 = new Date(before);
        return criteriaBuilder.lessThanOrEqualTo(root.get("birthday"), before1);
      }
      if (before == null) {
        Date after1 = new Date(after);
        return criteriaBuilder.greaterThanOrEqualTo(root.get("birthday"), after1);
      }

      Date before1 = new Date(before - 3600001);
      Date after1 = new Date(after);
      return criteriaBuilder.between(root.get("birthday"), after1, before1);
    };
  }

  public static Specification<Player> order(PlayerOrder order) {
    return (root, query, criteriaBuilder) -> {
      if (order != null) {
        query.orderBy(criteriaBuilder.asc(root.get(order.getFieldName())));
      } else {
        query.orderBy(criteriaBuilder.asc(root.get(PlayerOrder.ID.getFieldName())));
      }
      return criteriaBuilder.equal(criteriaBuilder.literal(1), 1);
    };
  }
}
