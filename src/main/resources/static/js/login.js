
 async function iniciarSesion() {
 
let datos = {};

  datos.username = document.getElementById("floatingEmail").value;
  datos.password = document.getElementById("floatingPassword").value;

  const request = await fetch("api/login/post", {
    method: "POST",
    headers: {
      Accept: "application/json",
      "Content-Type": "application/json",
    },
    body: JSON.stringify(datos),
  });

  const response = await request.text();

  if (response != "FAIL") {
    const [token,nombre,apellido,id]=response.split("~");
    localStorage.token = token;
    localStorage.email = datos.username;
    localStorage.setItem("id",id);
    localStorage.setItem("nombre",nombre);
    localStorage.setItem("apellido",apellido);
    window.location.href = "sala";
  } else {
    alert("Credenciales incorrectas. Intente nuevamente");
  }

}
