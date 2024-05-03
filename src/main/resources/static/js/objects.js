/**
 * DB에서 모든 주소 정보들을 가져오는 함수이다.
 */
async function getAllAddress() {
    url = window.location.protocol + "//" + window.location.host + "/api/address";
    let response = await fetch(url);
    let content = await response.json();
    //console.log(content.vertex);
    return content.address;
}

/**
 * DB에 오브젝트 정보를 저장하는 함수이다.
 */
async function saveObjectsInfo(RId, SWId, lat, lng) {
    let url = window.location.protocol + "//" + window.location.host + "/api/object";
    let id = document.getElementById("id");
    let name = document.getElementById("name");
    let description = document.getElementById("description");
    let objectType = document.getElementById("objectType");
    let existingAddress = document.getElementById("existingAddress");
    let newAddress = document.getElementById("newAddress");
    if(name.value === "") {
        alert("오브젝트 이름은 필수입니다!");
        return;
    }
    let ObjectVCRequest = {
        "id": id.value,
        "name": name.value,
        "description": description.value,
        "objType": objectType.value,
        "existingAddressId": existingAddress.value,
        "newAddress": newAddress.value,
        "roadVertexId": RId,
        "sidewalkVertexId": SWId,
        "latitude": lat,
        "longitude": lng
    };
    let response = await fetch(url, {
        method: "POST",
        headers: {
            'Content-Type': 'application/json;charset=utf-8'
        },
        body: JSON.stringify(ObjectVCRequest)
    });
    if (newAddress.value !== "") {
        let newOption = new Option(newAddress.value, (existingAddress.length).toString());
        existingAddress.append(newOption);
    }
    // console.log("RId: " + RId, ", SWId: " + SWId);
    // if (RId !== null && SWId !== null) return;
    let objectIdx = await response.json();
    let idx;
    if (window.location.href.indexOf("road") !== -1) idx = RId;
    else idx = SWId;
    idx--;
    //console.log(newAddress.value, existingAddress.options[existingAddress.selectedIndex].text);
    let address = newAddress.value === "" ? existingAddress.options[existingAddress.selectedIndex].text : newAddress.value;
    changeToInfoMarker(idx, {
        "id": objectIdx,
        "name": name.value,
        "description": description.value,
        "objectType": objectType.value,
        "address": address
    })
    //console.log("index", objectIdx);
}

/**
 * DB에서 id에 해당하는 오브젝트 정보를 가져오는 함수이다.
 */
async function getObjectsInfo(id) {
    let url = window.location.protocol + "//" + window.location.host + "/api/object/" + id.toString();
    let response = await fetch(url);
    let content = response.json();
    //console.log(content);
    return content;
}