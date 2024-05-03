/**
 * DB에 간선 정보를 저장하는 함수이다.
 */
async function saveEdgeInfo(v1, v2, length) {
    v1++;
    v2++;
    let url;
    if (window.location.href.indexOf("road") !== -1) url = window.location.protocol + "//" + window.location.host + "/api/road/edge";
    else url = window.location.protocol + "//" + window.location.host + "/api/sidewalk/edge";
    let EdgeVCRequest = {
        "vertex1": v1,
        "vertex2": v2,
        "length": length
    }
    let response = await fetch(url, {
        method: "POST",
        headers: {
            'Content-Type': 'application/json;charset=utf-8'
        },
        body: JSON.stringify(EdgeVCRequest)
    });
}

/**
 * DB에서 간선 정보를 삭제하는 함수이다.
 */
async function deleteEdge(v1, v2) {
    v1++;
    v2++;
    let url;
    if (window.location.href.indexOf("road") !== -1) url = window.location.protocol + "//" + window.location.host + "/api/road/edge";
    else url = window.location.protocol + "//" + window.location.host + "/api/sidewalk/edge";
    let EdgeVCRequest = {
        "vertex1": v1,
        "vertex2": v2
    }
    let response = await fetch(url, {
        method: "DELETE",
        headers: {
            'Content-Type': 'application/json;charset=utf-8'
        },
        body: JSON.stringify(EdgeVCRequest)
    });
}

/**
 * DB에서 가져온 간선 정보 리스트를 이용해 카카오 지도 위에 간선을 추가하는 함수이다.
 */
function makePolyLine(edges) {
    for (let e of edges) {
        let id = e.id;
        let v1 = e.vertex1 - 1;
        let v2 = e.vertex2 - 1;
        let length = e.length;
        drawPolyLine(v1, v2, length);
    }
}

let polyLineMouseOver;

/**
 * 간선에 이벤트 리스너들을 추가하는 함수이다.
 */
function edgeAddEventListener(polyline) {
    kakao.maps.event.addListener(polyline, 'mouseover', function () {
        //console.log("mouse over!");
        polyLineMouseOver = 1;
        polyline.setOptions({
            strokeWeight: 10
        });
    });
    kakao.maps.event.addListener(polyline, 'mouseout', function () {
        polyLineMouseOver = 0;
        polyline.setOptions({
            strokeWeight: 5
        });
    });
    if (window.location.href.indexOf("road") !== -1) {
        kakao.maps.event.addListener(polyline, 'click', function () {
            for (let i = 0; i < lines.length; i++) {
                //let draw = 1;
                if (polyline !== lines[i]) continue;
                for (let j = 0; j < edges.length; j++) {
                    if (edges[i][0] !== edges[j][1] || edges[i][1] !== edges[j][0]) continue;
                    deleteEdge(edges[i][0], edges[i][1]);
                    lines[i].setMap(null);
                    lines.splice(i, 1);
                    edges.splice(i, 1);
                    edgeLength.splice(i, 1);
                    return;
                }
                drawPolyLine(edges[i][1], edges[i][0], edgeLength[i]);
                saveEdgeInfo(edges[i][1], edges[i][0], edgeLength[i]);
                return;
            }
        });
    }
}

/**
 * 카카오 지도 위에 간선을 그리는 함수이다.
 */
function drawPolyLine(v1, v2, length) {
    // 선을 구성하는 좌표 배열입니다. 이 좌표들을 이어서 선을 표시합니다
    let linePath = [
        new kakao.maps.LatLng(positions[v1].getLat(), positions[v1].getLng()),
        new kakao.maps.LatLng(positions[v2].getLat(), positions[v2].getLng()),
    ]
    // 지도에 표시할 선을 생성합니다
    let polyline = new kakao.maps.Polyline({
        path: linePath, // 선을 구성하는 좌표배열 입니다
        strokeWeight: 5, // 선의 두께 입니다
        strokeColor: '#db4040', // 선의 색깔입니다
        strokeOpacity: 0.7, // 선의 불투명도 입니다 1에서 0 사이의 값이며 0에 가까울수록 투명합니다
        strokeStyle: 'solid', // 선의 스타일입니다
        clickable: true
    });

    edgeAddEventListener(polyline);

    //console.log("edge length: " + length);
    if (length === undefined) length = polyline.getLength();
    lines.push(polyline);
    edges.push([v1, v2]);
    edgeLength.push(length);
    polyline.setMap(map);
    return length;
}

/**
 * DB에서 모든 간선 정보들을 가져오는 함수이다.
 */
async function getAllEdge() {
    let url;
    if (window.location.href.indexOf("road") !== -1) url = window.location.protocol + "//" + window.location.host + "/api/road/edges";
    else url = window.location.protocol + "//" + window.location.host + "/api/sidewalk/edges";
    //console.log(url);
    let response = await fetch(url);
    let content = await response.json();
    //console.log(content.edge);
    return content.edge;
}