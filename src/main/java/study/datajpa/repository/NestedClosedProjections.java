package study.datajpa.repository;

/**
 * left join 실행됨.
 * 기준 엔티티에 대한 것은 최적화가 가능한데
 * Team 같은 경우 최적화가 안됨(전체 들고옴)
 */
public interface NestedClosedProjections {
    String getUsername();
    TeamInfo getTeam();

    interface TeamInfo {
        String getName();
    }
}
