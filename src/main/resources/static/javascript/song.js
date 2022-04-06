// Add song
const HEADERS = {
    "Content-Type": "application/json",
    "Accept": "application/json",
    "Authentication": window.sessionStorage.getItem("auth")
};

const songForm = document.getElementById("song-form");
songForm.addEventListener("submit", onSongFormSubmit);


function onSongFormSubmit(event) {
    event.preventDefault();

    const formData = new FormData(event.target);
    const data = Object.fromEntries(formData.entries());

    fetch("http://localhost:8080/events/song", {
        method: "POST",
        headers: HEADERS,
        body: JSON.stringify(data),
    }).then((response) => {
        console.log(response);
    })
}
