package spaland.api.naviMenu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spaland.api.naviMenu.model.NaviMenu;

public interface INaviMenuRepository extends JpaRepository<NaviMenu, Long> {
}
