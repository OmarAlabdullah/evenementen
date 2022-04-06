const HEADERS = {
    "Content-Type": "application/json",
    "Accept": "application/json",
    "Authentication": window.sessionStorage.getItem("jwt")
};

//-------------------------------------show my events-------------------------------------------------
const userName = window.sessionStorage.getItem("email");
window.addEventListener("DOMContentLoaded", (event) => {
    let div = document.getElementById('container');
    div.className = "row";

    event.preventDefault();

    fetch("http://localhost:8080/events/event/" + userName, {
        method: "POST",
        headers: HEADERS,
    }).then(response => response.json())
        .then(function (response) {
            console.log(response);
            showData(response);

        });
});


function showData(events) {
    let div = document.getElementById('container');
    for (let item of events) {
        let table = document.createElement('table');
        let tbd = document.createElement('tbody');
        let tr = document.createElement('tr');
        let image = document.createElement('tr');
        let button = document.createElement("button");
        button.onclick = deleteEvent;
        button.type = "submit";button.className = "deleteButton"; button.id = Object.values(item)[0]; button.innerText ="Delete";
        for (let i = 1; i <  Object.keys(item).length-1; i++) {

            let td = document.createElement('td');
            image.innerHTML =  `<img src="../img/img.png" alt="meow" style="width:100%">`;
            if (Object.keys(item)[i] === "link") {
                let cellText = document.createElement("a");
                cellText.innerHTML = "Link: " +
                    `<a class="external" href=${Object.values(item)[i]} target=_blank> ${Object.values(item)[i]}</a>`;
                td.appendChild(cellText);
            }
            else{
                td.innerText = Object.keys(item)[i] +": "+ Object.values(item)[i]
            }
            tr.appendChild(td);
        }
        tbd.appendChild(image);
        tbd.appendChild(tr);
        tbd.appendChild(button);
        table.appendChild(tbd);
        div.appendChild(table);
    }
}

// ---------------------------------delete event----------------------------------
function deleteEvent() {
const id = document.querySelector("button[id]").id;
fetch("http://musiqay.herokuapp.com/events/event/" + id, {
    method: "DELETE",
    headers: HEADERS,
}).then(function (response) {
        console.log(response);
        (window.location.href= "../html/organizer.html")})
}


function account(){
    window.location.href = "account.html"

}

function logout(){
    window.sessionStorage.setItem("jwt", "");
    window.sessionStorage.setItem( "email","");
    window.location.href = "../index.html"

}


///////////////////////////navbar///////////////////////////////////
function myFunction() {
    var x = document.getElementById("myTopnav");
    if (x.className === "topnav") {
        x.className += " responsive";
    } else {
        x.className = "topnav";
    }
}
