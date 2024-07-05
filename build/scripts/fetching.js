async function loadEnv() {
  try {
    const response = await fetch("scripts/env.json");
    const env = await response.json();
    return env;
  } catch (error) {
    console.error("Error loading environment variables:", error);
    return null;
  }
}

console.log("Fetching data...");
// Uso de las variables de entorno cargadas

  async function fetchRequest(params) {
    let books = [];
    const ENV = await loadEnv();
    if (ENV) {
      try {
        const response = await fetch(`${ENV.BASE_URL}/${params}`, {
          method: "GET",
          headers: {
            "Content-Type": "application/json charset=utf-8",
          },
        });
        books = await response.json();
      } catch (error) {
        console.error("Error:", error);
      }
    }
    return books;
  }




