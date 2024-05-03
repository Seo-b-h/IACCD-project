/*
 * 클래스 기능 : 도보 정점 정보 엔티티
 * 최근 수정 일자 : 2024.05.01(수)
 */
package Design.Idea.model;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
@NamedStoredProcedureQuery(
        name = "SWVTX_DEL_PROCEDURE",
        procedureName = "SWVTX_DEL_PROCEDURE",
        parameters = {@StoredProcedureParameter(mode = ParameterMode.IN, type = Long.class, name = "ID")}
)
public class SidewalkVertex {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sidewalk_vertex_id")
    private Long id; // SidewalkVertex 테이블 아이디

    @Column(nullable = false)
    private double latitude; // 정점의 위도

    @Column(nullable = false)
    private double longitude; // 정점의 경도

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "is_destination")
    private Objects object; // 일대일 연관관계 매핑

    //==정적 팩토리 메서드==//
    public static SidewalkVertex createSidewalkVertex(double latitude, double longitude) {
        SidewalkVertex sidewalkVertex = new SidewalkVertex();
        sidewalkVertex.changeLatitude(latitude);
        sidewalkVertex.changeLongitude(longitude);
        return sidewalkVertex;
    }

    //==비즈니스 로직==//
    public void changeLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void changeLongitude(double longitude) {
        this.longitude = longitude;
    }

    //==연관관계 메소드==//
    public void changeObjects(Objects objects) {
        this.object = objects;
        objects.changeSidewalkVertex(this);
    }
    /*@OneToMany(mappedBy = "sidewalkVertex1", cascade = CascadeType.ALL)
    private List<SidewalkEdge> sidewalkEdges = new ArrayList<>();*/
}
