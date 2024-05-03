/*
 * 클래스 기능 : 건물, 랜드마크 등의 정보를 담는 오브젝트 엔티티
 * 최근 수정 일자 : 2024.05.01(수)
 */
package Design.Idea.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Objects {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "object_id")
    private Long id; // Object 테이블 아이디

    @Column(length = 45, nullable = false)
    private String name; // 이름

    @Column(length = 1000)
    private String description; //  대상의 설명

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ObjType objectType; // 대상의 종류(건물, 랜드마크, 벤치 등등)

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id")
    private ObjectAddress objectAddress;

    @JsonIgnore
    @OneToOne(mappedBy = "object", fetch = FetchType.LAZY)
    private RoadVertex roadVertex; //RoadVertex와 Objects 양방향 연관관계

    @JsonIgnore
    @OneToOne(mappedBy = "object", fetch = FetchType.LAZY)
    private SidewalkVertex sidewalkVertex; //SidewalkVertex와 Objects 양방향 연관관계

    //==정적 팩토리 메서드==//
    public static Objects createObjects(String name, String description, ObjType objectType, ObjectAddress objectAddress) {
        Objects objects = new Objects();
        objects.changeName(name);
        objects.changeDescription(description);
        objects.changeObjectType(objectType);
        objects.changeObjectAddress(objectAddress);
        return objects;
    }

    //==비즈니스 로직==//
    public void changeName(String name) {
        this.name = name;
    }

    public void changeDescription(String description) {
        this.description = description;
    }

    public void changeObjectType(ObjType objectType) {
        this.objectType = objectType;
    }

    public void changeObjectAddress(ObjectAddress objectAddress) {
        this.objectAddress = objectAddress;
    }

    //==연관관계 메소드를 위한 setter==//
    public void changeRoadVertex(RoadVertex roadVertex) {
        this.roadVertex = roadVertex;
    }

    public void changeSidewalkVertex(SidewalkVertex sidewalkVertex) {
        this.sidewalkVertex = sidewalkVertex;
    }

}
