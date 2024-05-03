/*
 * 클래스 기능 : 도로 간선 정보 엔티티
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
public class RoadEdge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "road_edge_id")
    private Long id; // RoadEdge 테이블 아이디

    //@ManyToOne(fetch = FetchType.LAZY)
    @Column(name = "road_vertex_id1", nullable = false)
    private Long roadVertex1; // 도로 정점1(시작)

    //@ManyToOne(fetch = FetchType.LAZY)
    @Column(name = "road_vertex_id2", nullable = false)
    private Long roadVertex2; // 도로 정점2(끝)

    @Column(nullable = false)
    private double length; // 간선 길이

    //==정적 팩토리 메서드==//
    public static RoadEdge createRoadEdge(Long roadVertex1, Long roadVertex2, double length) {
        RoadEdge roadEdge = new RoadEdge();
        roadEdge.changeRoadVertex1(roadVertex1);
        roadEdge.changeRoadVertex2(roadVertex2);
        roadEdge.changeLength(length);
        return roadEdge;
    }

    //==비즈니스 로직==//
    public void changeRoadVertex1(Long roadVertex1) {
        this.roadVertex1 = roadVertex1;
    }

    public void changeRoadVertex2(Long roadVertex2) {
        this.roadVertex2 = roadVertex2;
    }

    public void changeLength(double length) {
        this.length = length;
    }
}
