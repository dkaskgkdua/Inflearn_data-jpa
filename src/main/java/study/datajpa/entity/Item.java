package study.datajpa.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.domain.Persistable;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalTime;

@Entity
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Item implements Persistable<String> {

    @Id
    private String id;

    @CreatedDate
    private LocalTime createdDated;

    public Item(String id) {
        this.id = id;
    }

    /**
     * 채번과 같은 방식으로 id를 하기 위해서
     * jpa가 새로운 객체인지 판별하는게 필요한데
     * Persistable을 받아서
     * isNew를 override 해서 생성일자를 기준으로 판별해줌
     */

    @Override
    public boolean isNew() {
        return createdDated == null;
    }

    @Override
    public String getId() {
        return id;
    }
}
