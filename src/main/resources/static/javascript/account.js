const HEADERS = {
    "Content-Type": "application/json",
    "Accept": "application/json",
    "Authentication": window.sessionStorage.getItem("jwt")
};

// -----------------------------account info------------------------------------
const userName = window.sessionStorage.getItem("email");
window.addEventListener("DOMContentLoaded", (event) => {
    let container = document.getElementById('container');
    event.preventDefault();

    fetch("http://localhost:8080/events/organizer/"+userName , {
        method: "POST",
        headers: HEADERS,
    }).then(response => response.json())
        .then(function (response) {
                for (let i = 1; i < Object.keys(response).length; i++) {
                    let newLabel = document.createElement('label');
                    newLabel.innerHTML = Object.keys(response)[i]+ ": " + Object.values(response)[i] +`<br><br>`
                container.appendChild(newLabel);
                }
        });
});



////////////////////////////////////account updating////////////////////////////////////////
window.addEventListener("submit", (e) => {
    e.preventDefault();

    const formData = new FormData(e.target);
    let data = Object.fromEntries(formData.entries());
    data["email"] = window.sessionStorage.getItem('email');
    data["role"] = "ROLE_ORGANIZER";
    data["username"] = window.sessionStorage.getItem('email');
    console.log(data);
    fetch("/register" , {
        method: "PUT",
        headers: HEADERS,
        body: JSON.stringify(data),
}).then((response) => {
    console.log(response);
})

});


function logout(){
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
