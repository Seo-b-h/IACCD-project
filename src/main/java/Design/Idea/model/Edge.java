package Design.Idea.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
/*간선에 대한 정보를 저장하는 클래스이다.*/
public class Edge {
    int V1;
    int V2;
    String edgeLength;
    String isOneWay;

    /*도보의 간선 정보를 저장하는데 쓰인다.*/
    public Edge (int V1, int V2, String edgeLength) {
        this.V1 = V1;
        this.V2 = V2;
        this.edgeLength = edgeLength;
    }

    /*도로의 간선 정보를 저장하는데 쓰이다.*/
    public Edge (int V1, int V2, String edgeLength, String isOneWay) {
        this.V1 = V1;
        this.V2 = V2;
        this.edgeLength = edgeLength;
        this.isOneWay = isOneWay;
    }
}
