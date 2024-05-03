/*
 * 클래스 기능 : 도로 정점 정보 엔티티
 * 최근 수정 일자 : 2024.05.01(수)
 */
package Design.Idea.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NamedStoredProcedureQuery(
        name = "RVTX_DEL_PROCEDURE",
        procedureName = "RVTX_DEL_PROCEDURE",
        parameters = {@StoredProcedureParameter(mode = ParameterMode.IN, type = Long.class, name = "ID")}
)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RoadVertex {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "road_vertex_id")
    private Long id; // RoadVertex 테이블 아이디

    @Column(nullable = false)
    private double latitude; // 정점의 위도

    @Column(nullable = false)
    private double longitude; // 정점의 경도

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "is_destination")
    private Objects object; // 일대일 연관관계 매핑

    //==정적 팩토리 메서드==//
    public static RoadVertex createRoadVertex(double latitude, double longitude) {
        RoadVertex roadVertex = new RoadVertex();
        roadVertex.changeLatitude(latitude);
        roadVertex.changeLongitude(longitude);
        return roadVertex;
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
        objects.changeRoadVertex(this);
    }

    /*@OneToMany(mappedBy = "roadVertex1", cascade = CascadeType.ALL)
    private List<RoadEdge> roadEdges = new ArrayList<>();*/
}
