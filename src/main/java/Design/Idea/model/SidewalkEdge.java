/*
 * 클래스 기능 : 도보 간선 정보 엔티티
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
public class SidewalkEdge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sidewalk_edge_id")
    private Long id; // SidewalkEdge 테이블 아이디

    //@ManyToOne(fetch = FetchType.LAZY)
    @Column(name = "sidewalk_vertex_id1", nullable = false)
    private Long sidewalkVertex1; // 보도 정점1(시작)

    //@ManyToOne(fetch = FetchType.LAZY)
    @Column(name = "sidewalk_vertex_id2", nullable = false)
    private Long sidewalkVertex2; // 도보 정점2(끝)

    @Column(nullable = false)
    private double length; // 간선 길이

    //==정적 팩토리 메서드==//
    public static SidewalkEdge createSidewalkEdge(Long sidewalkVertex1, Long sidewalkVertex2, double length) {
        SidewalkEdge roadEdge = new SidewalkEdge();
        roadEdge.changeSidewalkVertex1(sidewalkVertex1);
        roadEdge.changeSidewalkVertex2(sidewalkVertex2);
        roadEdge.changeLength(length);
        return roadEdge;
    }

    //==비즈니스 로직==//
    public void changeSidewalkVertex1(Long sidewalkVertex1) {
        this.sidewalkVertex1 = sidewalkVertex1;
    }

    public void changeSidewalkVertex2(Long sidewalkVertex2) {
        this.sidewalkVertex2 = sidewalkVertex2;
    }

    public void changeLength(double length) {
        this.length = length;
    }
}
