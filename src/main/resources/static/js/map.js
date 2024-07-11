var container = document.getElementById('map');
var options = {
    center: new kakao.maps.LatLng(35.8330177, 128.7532086),
    level: 4 //지도 레벨 설정 (현재 100m)
};

var map = new kakao.maps.Map(container, options);

let markers = [];
let positions = [];
let lines = [];
let edges = [];
let edgeLength = [];
let vertexForEdge = [-1, -1];
let objects = [];

/**
 * 카카오 지도 클릭 이벤트 리스너를 추가한다.
 * 지도 클릭 시 일반 정점(Circle)이 그려지고 해당 정보가 DB에 저장된다.
 */
kakao.maps.event.addListener(map, 'click', function (mouseEvent) {
    //console.log("map click!");
    console.log("mouseOver: " + vertexMouseOver);
    if (vertexMouseOver) {
        vertexMouseOver = 0;
        return;
    }
    if (polyLineMouseOver) {
        return;
    }
    //console.log("map click!");
    saveVertexInfo(mouseEvent.latLng.getLat(), mouseEvent.latLng.getLng());
    drawMarker(mouseEvent.latLng, null, null);
});

// 오른쪽 클릭 여부를 저장하는 변수이다.
let isRightClick;

/**
 * 마우스 오른쪽 버튼이 눌렸는지 감지하는 이벤트 리스너를 추가한다.
 * event.button === 2 이거나 event.which === 3 이면 오른쪽 클릭이므로 isRightClick을 1로 변경한다.
 */
window.addEventListener('mousedown', function (event) {
    if (event.button === 2 || event.which === 3) {
        //console.log("right click!");
        isRightClick = 1;
    }
});

// getAllVertex(DB에서 정점을 받아옴) -> makeMarker(받아온 정점들을 지도에 마커로 추가) -> getAllEdge(DB에서 간선을 받아옴) -> makePolyLine(받아온 간선들을 지도에 폴리라인으로 추가)
getAllVertex()
    .then(vertices => makeMarker(vertices))
    .then(() => getAllEdge()
        .then(edges => makePolyLine(edges))
    );

// 주소 리스트를 받아와서 오브젝트 정보를 입력할 수 있는 모달 창의 기존 주소 목록에 주소들을 차례대로 추가한다.
getAllAddress().then(addressList => {
    let existingAddress = document.getElementById("existingAddress");
    for (let i = 0; i < addressList.length; i++) {
        let newOption = new Option(addressList[i], (i + 1).toString());
        existingAddress.append(newOption);
    }
});

let RId, SWId, Lat, Lng;

/**
 * 오브젝트 정보를 입력하는 모달 창의 저장 버튼이 눌리면 해당 정보를 DB에 저장 혹은 변경한다.
 */
document.getElementById("submitObjectsForm").onclick = function () {
    saveObjectsInfo(RId, SWId, Lat, Lng);
};

/**
 * 오브젝트 정보를 입력하는 모달 창에서 새로운 주소 입력이 감지되면 기존 주소를 "없음" 으로 변경한다.
 */
document.getElementById("newAddress").onkeydown = function () {
    let existingAddress = document.getElementById("existingAddress");
    existingAddress.options[0].selected = true;
};

/**
 * 오브젝트 정보를 입력하는 모달 창에서 기존 주소 선택이 감지되면 새로운 주소 입력창을 "" 으로 변경한다.
 */
document.getElementById("existingAddress").addEventListener("input", function () {
    document.getElementById("newAddress").value = "";
});