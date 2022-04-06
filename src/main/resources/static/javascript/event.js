const HEADERS = {
    "Content-Type": "application/json",
    "Accept": "application/json",
    "Authentication": window.sessionStorage.getItem("jwt")
};

const eventForm = document.getElementById("event-form");
eventForm.addEventListener("submit", onEventFormSubmit);


function onEventFormSubmit(event) {
    event.preventDefault();

    const formData = new FormData(event.target);
    const data = Object.fromEntries(formData.entries());
    data["email"] = window.sessionStorage.getItem('email');
    fetch("http://localhost:8080/events/event", {
        method: "POST",
        headers: HEADERS,
        body: JSON.stringify(data),
    }).then((response) => {
        console.log(response);
    })
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
