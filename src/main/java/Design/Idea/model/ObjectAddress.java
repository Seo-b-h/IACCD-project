/*
 * 클래스 기능 : 건물주소정보 엔티티
 * 최근 수정 일자 : 2024.05.01(수)
 */
package Design.Idea.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ObjectAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private Long id; // ObjectAddress 테이블 아이디

    @Column(nullable = false)
    private String address; // 주소

    //==정적 팩토리 메서드==//
    public static ObjectAddress createObjectAddress(String address) {
        ObjectAddress objectAddress = new ObjectAddress();
        objectAddress.changeAddress(address);
        return objectAddress;
    }

    //==비즈니스 로직==//
    public void changeAddress(String address) {
        this.address = address;
    }

/*    @OneToMany(mappedBy = "objectAddress")
    //@JoinColumn(name = "object_id", nullable = false)
    private List<Objects> objects = new ArrayList<>(); // 일대다 연관관계 매핑*/
}
