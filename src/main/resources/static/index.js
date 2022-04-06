const HEADERS = {
    "Content-Type": "application/json",
    "Accept": "application/json",
    "Authentication": window.sessionStorage.getItem("auth")
};


//show events
window.addEventListener("DOMContentLoaded", (event) => {
    let div = document.getElementById('container');
    div.className = "row";
    event.preventDefault();

    fetch("http://localhost:8080/events/events", {
        method: "GET",
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
        for (let i = 1; i <  Object.keys(item).length; i++) {
            let td = document.createElement('td');
                image.innerHTML =  `<img src="img/img.png" alt="meow" style="width:100%">`;
            if (Object.keys(item)[i] === "link"){
                let cellText = document.createElement("a");
                cellText.innerHTML=
                    `Link: <a class="external" href=${Object.values(item)[i]} target=_blank> ${Object.values(item)[i]}</a>`;
                td.appendChild(cellText);
            }else if(Object.keys(item)[i] === "organizer" ){
                td.innerHTML = "organizer name: "+ Object.values(Object.values(item)[i])[2] +`<br><br> organizer email: <a class="external" href=${Object.values(Object.values(item)[i])[1]} target=_blank> ${Object.values(Object.values(item)[i])[1]}</a>`  ;
            }
            else{
                td.innerText = Object.keys(item)[i] +": "+ Object.values(item)[i]
            }

            tr.appendChild(td);
        }
        tbd.appendChild(image);
        tbd.appendChild(tr);
        table.appendChild(tbd);
        div.appendChild(table);
    }
}

function openLoginForm() {
    document.getElementById("loginFormId").style.display = "block";

}

function closeForm() {
    document.getElementById("loginFormId").style.display = "none";
}


function openRegisterForm() {
    document.getElementById("registrationFormId").style.display = "block";

}

function closeRegisterForm() {
    document.getElementById("registrationFormId").style.display = "none";
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
