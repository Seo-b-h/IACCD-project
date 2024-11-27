/**
 * 정점 정보를 DB에 저장하는 함수이다.
 */
async function saveVertexInfo(lat, lng) {
    let url;
    if (window.location.href.indexOf("road") !== -1) url = window.location.protocol + "//" + window.location.host + "/api/road/vertex";
    else url = window.location.protocol + "//" + window.location.host + "/api/sidewalk/vertex";
    let VertexVCRequest = {
        "latitude": lat,
        "longitude": lng
    };
    let response = await fetch(url, {
        method: "POST",
        headers: {
            'Content-Type': 'application/json;charset=utf-8'
        },
        body: JSON.stringify(VertexVCRequest)
    });
}

/**
 * 정점 정보를 DB에서 삭제하는 함수이다.
 */
async function deleteVertex(id) {
    id++;
    let url;
    if (window.location.href.indexOf("road") !== -1) url = window.location.protocol + "//" + window.location.host + "/api/road/vertex/" + id.toString();
    else url = window.location.protocol + "//" + window.location.host + "/api/sidewalk/vertex/" + id.toString();
    let response = await fetch(url, {method: "DELETE"});
}

/**
 * DB에서 가져온 정점 정보 리스트를 이용해 카카오 지도 위에 정점 마커를 추가하는 함수이다.
 */
function makeMarker(vertices) {
    for (let v of vertices) {
        let id = v.id;
        let lat = v.latitude;
        let lng = v.longitude;
        let obj = v.objectDto.id === null ? null : v.objectDto;
        let position = new kakao.maps.LatLng(lat, lng);
        drawMarker(position, obj, id);
    }
    //console.log(markers);
}

let vertexMouseDownBegin, vertexMouseDownEnd, vertexMouseOver;

/**
 * 정점 마커에 이벤트 리스너들을 추가하는 함수이다.
 */
function vertexAddEventListener(marker, isNormalVertex) {
    kakao.maps.event.addListener(marker, 'mouseover', function () {
        //console.log("mouse over!");
        /*for (let i = 0; i < markers.length; i++) // 정점 아이디 찾을 때 사용.
            if (marker === markers[i]) {
                console.log("marker id: " + (i + 1).toString());
                break;
            }*/
        vertexMouseOver = 1;
        if (isNormalVertex) marker.setRadius(3);
    });
    kakao.maps.event.addListener(marker, 'mouseout', function () {
        vertexMouseOver = 0;
        if (isNormalVertex) marker.setRadius(1);
    });
    kakao.maps.event.addListener(marker, 'mousedown', function () {
        vertexMouseDownBegin = new Date();
        setTimeout(() => {
            /*
            * 원래 오른쪽 클릭은 click eventListener에 있었는데, 2024.11.27. 확인해보니
            * kakao map api의 event listener에서 더 이상 오른쪽 클릭이 click event로 감지되지 않음을 확인함.
            * 따라서 mousedown event listener에서 오른쪽 클릭에 관련된 로직을 실행하도록 바꾸었음.
            * 추가로 예전에는 javascript의 event listener가 kakao map api의 event listener 보다 감지 속도가 빨라 setTimeOut()을 사용하지 않았지만,
            * 2024.11.27. 확인해보니 kakao map api의 감지속도가 javascript의 event 감지속도보다 빠른 경우가 있어 setTimeOut()을 사용해 10ms 기다렸다가 로직을 실행하도록 수정함.
            */
            if (isRightClick) {
                //console.log("mousedown: right click");
                edgeControl(marker);
            }
        }, 10);
    });
    /*kakao.maps.event.addListener(marker, 'rightclick', function () {
        console.log("1234124124124124");
    });*/
    kakao.maps.event.addListener(marker, 'click', function () {
        if (polyLineMouseOver) return;
        //console.log("circle click!");
        vertexMouseDownEnd = new Date();
        //console.log(mouseDownBegin, mouseDownEnd, mouseDownEnd - mouseDownBegin);
        if (vertexMouseDownEnd - vertexMouseDownBegin < 300) {
            /*마커를 지도에서 지운다.*/
            marker.setMap(null);

            /*마커 배열을 순회하며 지워야 할 마커를 찾아 지운다.*/
            for (let i = 0; i < markers.length; i++) {
                if (markers[i] !== marker) continue;
                //console.log(i);
                /*마커와 관련있는 간선과 관련된 정보들을 지운다.*/
                for (let j = 0; j < edges.length; j++) {
                    if (edges[j][0] !== i && edges[j][1] !== i) continue;
                    lines[j].setMap(null);
                    lines.splice(j, 1);
                    edges.splice(j, 1);
                    edgeLength.splice(j, 1);
                    j--;
                }

                /*마커가 지워졌을 때 마커의 아이디가 1씩 당겨지므로 간선을 이루는 아이디도 1씩 당긴다.*/
                for (let j = 0; j < edges.length; j++) {
                    if (edges[j][0] > i) edges[j][0]--;
                    if (edges[j][1] > i) edges[j][1]--;
                }

                positions.splice(i, 1);
                markers.splice(i, 1);
                if (objects[i] !== null) vertexMouseOver = 0;
                objects.splice(i, 1);
                deleteVertex(i);
                break;
            }
        } else {
            let obj = null;
            for (let i = 0; i < markers.length; i++) {
                if (marker === markers[i]) {
                    obj = objects[i];
                    if (obj === null) {
                        if (window.location.href.indexOf("road") !== -1) {
                            RId = i + 1;
                            SWId = null;
                        } else {
                            RId = null;
                            SWId = i + 1;
                        }
                        Lat = marker.getPosition().getLat();
                        Lng = marker.getPosition().getLng();
                    } else {
                        getObjectsInfo(obj.id).then(info => {
                            //console.log(info);
                            RId = info.roadVertexId;
                            SWId = info.sidewalkVertexId;
                            Lat = info.latitude;
                            Lng = info.longitude;
                        });
                    }
                    //console.log(RId, SWId, Lat, Lng);
                    break;
                }
            }
            let id = document.getElementById("id");
            let name = document.getElementById("name");
            let description = document.getElementById("description");
            let objectType = document.getElementById("objectType");
            let existingAddress = document.getElementById("existingAddress");
            let newAddress = document.getElementById("newAddress");
            if (obj !== null) {
                id.value = obj.id;
                name.value = obj.name;
                description.value = obj.description;
                objectType.value = obj.objectType;
                let option = Array.from(existingAddress.options).map(option => {
                    if (option.text === obj.address) option.selected = true;
                });
                newAddress.value = null;
            } else {
                id.value = null;
                name.value = null;
                description.value = null;
                let option = Array.from(existingAddress.options).map(option => {
                    if (option.text === "없음") option.selected = true;
                });
                newAddress.value = null;
            }

            //console.log(obj);
            $("#objectFormModal").modal("show");
        }
    });
    /*if (!isNormalVertex) kakao.maps.event.addListener(marker, 'rightclick', function () {
        edgeControl(marker);
    });*/
}

/**
 * 정점을 이용해 간선을 추가하거나 삭제하는데 사용되는 함수이다.
 */
function edgeControl(marker) {
    console.log(vertexForEdge[0], vertexForEdge[1]);
    if (vertexForEdge[0] === -1) {
        for (let i = 0; i < markers.length; i++) {
            if (marker === markers[i]) {
                vertexForEdge[0] = i;
                break;
            }
        }
    } else {
        for (let i = 0; i < markers.length; i++) {
            if (marker === markers[i]) {
                let isDelete = 0;
                vertexForEdge[1] = i;
                console.log(vertexForEdge[0], vertexForEdge[1]);
                if (vertexForEdge[0] === vertexForEdge[1]) {
                    vertexForEdge = [-1, -1];
                    return;
                }
                for (let j = 0; j < edges.length; j++) {
                    if ((edges[j][0] === vertexForEdge[0] && edges[j][1] === vertexForEdge[1]) || (edges[j][0] === vertexForEdge[1] && edges[j][1] === vertexForEdge[0])) {
                        deleteEdge(edges[j][0], edges[j][1]);
                        lines[j].setMap(null);
                        lines.splice(j, 1);
                        edges.splice(j, 1);
                        edgeLength.splice(j, 1);
                        j--;
                        isDelete++;
                        if (isDelete === 2) break;
                    }
                }
                if (isDelete === 0) {
                    let length = drawPolyLine(vertexForEdge[0], vertexForEdge[1]);
                    saveEdgeInfo(vertexForEdge[0], vertexForEdge[1], length);
                    drawPolyLine(vertexForEdge[1], vertexForEdge[0]);
                    saveEdgeInfo(vertexForEdge[1], vertexForEdge[0], length);
                }
                break;
            }
        }
        vertexForEdge = [-1, -1];
    }

    isRightClick = 0;
}

/**
 * 카카오 지도 위에 정점 마커를 그리는 함수이다.
 * obj, 즉 오브젝트 정보가 없으면 일반 정점(Circle)로 그린다.
 * 오브젝트 정보가 있으면 오브젝트 정점(Marker)로 그린다.
 */
function drawMarker(position, obj, id) {
    var marker;
    //console.log(obj);
    if (obj === null) {
        marker = new kakao.maps.Circle({
            center: position,  // 원의 중심좌표 입니다
            radius: 1, // 미터 단위의 원의 반지름입니다
            strokeWeight: 1, // 선의 두께입니다
            strokeColor: '#5600ff', // 선의 색깔입니다
            strokeOpacity: 1, // 선의 불투명도 입니다 1에서 0 사이의 값이며 0에 가까울수록 투명합니다
            strokeStyle: 'solid', // 선의 스타일 입니다
            fillColor: '#7941ff', // 채우기 색깔입니다
            fillOpacity: 1, // 채우기 불투명도 입니다
            zIndex: 10
        });
        vertexAddEventListener(marker, 1);
    } else {
        marker = new kakao.maps.Marker({
            position: position,
            clickable: true
        });
        vertexAddEventListener(marker, 0);
    }
    markers.push(marker);
    objects.push(obj);
    positions.push(position);
    marker.setMap(map);
}

/**
 * DB로부터 모든 정점 정보들을 가져오는 함수이다.
 */
async function getAllVertex() {
    let url;
    if (window.location.href.indexOf("road") !== -1) url = window.location.protocol + "//" + window.location.host + "/api/road/vertices";
    else url = window.location.protocol + "//" + window.location.host + "/api/sidewalk/vertices";
    //console.log(url);
    let response = await fetch(url);
    let content = await response.json();
    //console.log(content);
    return content.vertex;
}

/**
 * 카카오 지도에 그려져 있는 일반 정점(Circle)을 오브젝트 정점(Marker)로 변경하는 함수이다.
 */
function changeToInfoMarker(idx, obj) {
    let marker;
    //console.log("idx: " + idx);
    markers[idx].setMap(null);
    marker = new kakao.maps.Marker({
        position: positions[idx],
        clickable: true
    })
    vertexAddEventListener(marker, 0);
    markers[idx] = marker;
    objects[idx] = obj;
    markers[idx].setMap(map);
}