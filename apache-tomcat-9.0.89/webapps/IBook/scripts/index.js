document.addEventListener("DOMContentLoaded", () => {
  fetch("/IBook/api/routes")
    .then((response) => response.json())
    .then((data) => {
      const routesList = document.getElementById("routes");
      data.routes.forEach((route) => {
        const li = document.createElement("li");
        li.textContent = route;
        routesList.appendChild(li);
      });
    })
    .catch((error) => console.error("Error fetching routes:", error));
});
